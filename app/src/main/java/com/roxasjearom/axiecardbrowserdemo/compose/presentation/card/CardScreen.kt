package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.CardClassFilter
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.PartTypeFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OriginCardScreenWithBottomSheet(cardViewModel: CardViewModel) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    val cardClassFilters = cardViewModel.cardsUiState.collectAsState().value.chipItems.filter { it.cardFilter is CardClassFilter }
    val partTypeFilters = cardViewModel.cardsUiState.collectAsState().value.chipItems.filter { it.cardFilter is PartTypeFilter }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            CardFilterBottomSheet(
                hasFilter = cardViewModel.cardsUiState.collectAsState().value.hasFilter,
                cardClasses = cardClassFilters,
                partTypes = partTypeFilters,
                onCardClassClicked = { clickedCardClass ->
                    cardViewModel.filterCards(CardClassFilter(clickedCardClass.name, clickedCardClass))
                },
                onPartTypeClicked = { clickedPartType ->
                    cardViewModel.filterCards(PartTypeFilter(clickedPartType.name, clickedPartType))
                },
                onClearClicked = {
                    cardViewModel.clearFilters()
                }
            )
        }, sheetPeekHeight = 132.dp
    ) {
        OriginCardList(
            cards = cardViewModel.cardsUiState.collectAsState().value.cards,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
        )
    }
}
