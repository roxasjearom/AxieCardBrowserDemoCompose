package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.*
import com.roxasjearom.axiecardbrowserdemo.compose.domain.repository.CardRepository
import com.roxasjearom.axiecardbrowserdemo.compose.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private var completeCards = emptyList<OriginCard>()

    private val _cardsUiState = MutableStateFlow(CardsUiState())
    val cardsUiState: StateFlow<CardsUiState> = _cardsUiState.stateIn(
        initialValue = CardsUiState(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )

    init {
        fetchOriginData()
        getCards()
    }

    private fun fetchOriginData() {
        viewModelScope.launch {
            try {
                _cardsUiState.update { uiState ->
                    uiState.copy(isLoading = true)
                }
                cardRepository.fetchData()
                _cardsUiState.update { uiState ->
                    uiState.copy(isLoading = false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _cardsUiState.update { uiState ->
                    uiState.copy(
                        isLoading = false,
                        message = "Fetching data failed"
                    )
                }
            }
        }
    }

    private fun getCards() {
        viewModelScope.launch {
            cardRepository.getCards().collect { cards ->
                completeCards = cards
                _cardsUiState.update { uiState ->
                    uiState.copy(
                        cards = cards
                    )
                }
            }
        }
    }

    fun filterCards(newFilter: CardFilter) {
        viewModelScope.launch(dispatcherProvider.io) {
            val cardFilters = _cardsUiState.value.chipItems
            updateFilters(newFilter, cardFilters)

            val filteredCards =
                if (cardFilters.none { it.isSelected }) {
                    completeCards
                } else {
                    completeCards.filter { card ->
                        isCardValid(card, cardFilters.filter { it.isSelected })
                    }
                }
            _cardsUiState.update { currentUiState ->
                currentUiState.copy(
                    cards = filteredCards,
                    hasFilter = cardFilters.any { it.isSelected },
                )
            }
        }
    }

    private fun updateFilters(
        newFilter: CardFilter,
        chipItems: List<ChipItem>
    ) {
        val filter = when (newFilter) {
            is CardClassFilter -> CardClassFilter(
                newFilter.id,
                newFilter.cardClass
            )
            is PartTypeFilter -> PartTypeFilter(
                newFilter.id,
                newFilter.partType
            )
        }
        val item = chipItems.find { it.cardFilter.id == filter.id }
        item?.let {
            it.isSelected = !it.isSelected
        }
    }

    private fun isCardValid(card: OriginCard, chipItems: List<ChipItem>): Boolean {
        val booleanList = mutableListOf<Boolean>()

        for (menuItem in chipItems) {
            when (menuItem.cardFilter) {
                is CardClassFilter -> {
                    booleanList.add(
                        chipItems.filter { it.cardFilter is CardClassFilter }.any {
                            (it.cardFilter as CardClassFilter).cardClass == card.cardClass.toCardClass()
                        }
                    )
                }
                is PartTypeFilter -> {
                    booleanList.add(
                        chipItems.filter { it.cardFilter is PartTypeFilter }.any {
                            (it.cardFilter as PartTypeFilter).partType == card.partType.toPartType()
                        }
                    )
                }
            }
        }
        return booleanList.all { it }
    }

    fun clearFilters() {
        _cardsUiState.update { currentUiState ->
            currentUiState.copy(
                cards = completeCards,
                hasFilter = false,
                chipItems = getCardFilters()
            )
        }
    }

    fun messageShown() {
        _cardsUiState.update { uiState ->
            uiState.copy(message = null)
        }
    }
}

private fun getCardFilters(): List<ChipItem> {
    val chipItems = mutableListOf<ChipItem>()
    CardClass.values().forEach { cardClass ->
        chipItems.add(
            ChipItem(
                cardFilter = CardClassFilter(cardClass.name, cardClass),
                isSelected = false
            )
        )
    }
    PartType.values().forEach { partType ->
        chipItems.add(
            ChipItem(
                cardFilter = PartTypeFilter(partType.name, partType),
                isSelected = false
            )
        )
    }
    return chipItems
}

data class CardsUiState(
    val cards: List<OriginCard> = emptyList(),
    val isLoading: Boolean = false,
    val hasFilter: Boolean = false,
    val message: String? = null,
    val chipItems: List<ChipItem> = getCardFilters(),
)

data class ChipItem(
    val cardFilter: CardFilter,
    var isSelected: Boolean,
)
