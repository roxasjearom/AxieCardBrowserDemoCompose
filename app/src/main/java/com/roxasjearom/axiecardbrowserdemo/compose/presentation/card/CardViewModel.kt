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

    private val cardFilters = mutableListOf<CardFilter>()

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
            updateFilters(newFilter, cardFilters)

            val filteredCards =
                if (cardFilters.isEmpty()) {
                    completeCards
                } else {
                    val filteredList = mutableListOf<OriginCard>()
                    completeCards.filter { card ->
                        isCardValid(card, cardFilters)
                    }.forEach { validCard ->
                        filteredList.add(validCard)
                    }
                    filteredList
                }
            _cardsUiState.update { currentUiState ->
                currentUiState.copy(
                    cards = filteredCards,
                    hasFilter = cardFilters.isNotEmpty(),
                )
            }
        }
    }

    private fun updateFilters(
        newFilter: CardFilter,
        filterList: MutableList<CardFilter>
    ) {
        val filter = when (newFilter) {
            is CardClassFilter -> CardClassFilter(newFilter.id, newFilter.cardClass)
            is PartTypeFilter -> PartTypeFilter(newFilter.id, newFilter.partType)
        }
        val item = filterList.find { it.id == filter.id }
        if (filterList.contains(item)) {
            filterList.remove(item)
        } else {
            filterList.add(filter)
        }
    }

    private fun isCardValid(card: OriginCard, filterList: MutableList<CardFilter>): Boolean {
        val booleanList = mutableListOf<Boolean>()

        for (filter in filterList) {
            when (filter) {
                is CardClassFilter -> {
                    booleanList.add(
                        filterList.filterIsInstance<CardClassFilter>()
                            .any { it.cardClass == card.cardClass.toCardClass() }
                    )
                }
                is PartTypeFilter -> {
                    booleanList.add(
                        filterList.filterIsInstance<PartTypeFilter>()
                            .any { it.partType == card.partType.toPartType() }
                    )
                }
            }
        }
        return booleanList.all { it }
    }

    fun clearFilters() {
        cardFilters.clear()
        _cardsUiState.update { currentUiState ->
            currentUiState.copy(
                cards = completeCards,
                hasFilter = false,
            )
        }
    }

    fun messageShown() {
        _cardsUiState.update { uiState ->
            uiState.copy(message = null)
        }
    }
}

data class CardsUiState(
    val cards: List<OriginCard> = emptyList(),
    val isLoading: Boolean = false,
    val hasFilter: Boolean = false,
    val message: String? = null,
)
