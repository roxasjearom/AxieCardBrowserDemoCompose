package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import com.google.common.truth.Truth.assertThat
import com.roxasjearom.axiecardbrowserdemo.compose.MainDispatcherRule
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.CardClass
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.CardClassFilter
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.PartType
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.PartTypeFilter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CardViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val dispatcherProvider = TestDispatcherProvider(mainDispatcherRule.testDispatcher)

    private lateinit var cardViewModel: CardViewModel

    @Before
    fun setup() {
        cardViewModel = CardViewModel(FakeCardRepository(), dispatcherProvider)
    }

    @Test
    fun `Should filter correctly with a filter`() = runTest {
        cardViewModel.filterCards(CardClassFilter(CardClass.BEAST.name, CardClass.BEAST))

        val actualState = cardViewModel.cardsUiState.first()
        assertThat(actualState.chipItems.filter { it.isSelected }.size).isEqualTo(1)
        //FakeCardData has 1 Beast card
        assertThat(actualState.cards.size).isEqualTo(1)
        assertThat(actualState.hasFilter).isTrue()
    }

    @Test
    fun `Should filter correctly with multiple filter`() = runTest {
        cardViewModel.filterCards(CardClassFilter(CardClass.BIRD.name, CardClass.BIRD))
        cardViewModel.filterCards(PartTypeFilter(PartType.BACK.name, PartType.BACK))

        val actualState = cardViewModel.cardsUiState.first()
        assertThat(actualState.chipItems.filter { it.isSelected }.size).isEqualTo(2)
        //FakeCardData has 1 Bird card with back part
        assertThat(actualState.cards.size).isEqualTo(1)
        assertThat(actualState.hasFilter).isTrue()
    }

    @Test
    fun `Should clear message upon calling messageShown`() = runTest {
        cardViewModel.messageShown()

        val actualState = cardViewModel.cardsUiState.first()
        assertThat(actualState.message).isNull()
    }

    @Test
    fun `Should clear filters upon calling clearFilters`() = runTest {
        cardViewModel.clearFilters()

        val actualState = cardViewModel.cardsUiState.first()
        assertThat(actualState.hasFilter).isFalse()
    }
}
