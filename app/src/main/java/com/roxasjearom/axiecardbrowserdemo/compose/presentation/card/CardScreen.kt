package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.CardClassFilter
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.PartTypeFilter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OriginCardScreenWithBottomSheet(cardViewModel: CardViewModel = viewModel()) {
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
        }, sheetPeekHeight = 96.dp
    ) {
        OriginCardList(
            cards = cardViewModel.cardsUiState.collectAsState().value.cards,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
        )
    }
}
