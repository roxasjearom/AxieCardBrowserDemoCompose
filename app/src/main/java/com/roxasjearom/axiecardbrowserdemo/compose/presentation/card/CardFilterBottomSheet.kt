package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roxasjearom.axiecardbrowserdemo.compose.R
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.CardClass
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.CardClassFilter
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.PartType
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.PartTypeFilter
import com.roxasjearom.axiecardbrowserdemo.compose.ui.theme.AxieCardBrowserDemoTheme

@Composable
fun CardFilterBottomSheet(
    hasFilter: Boolean,
    cardClasses: List<ChipItem>,
    partTypes: List<ChipItem>,
    onCardClassClicked: (CardClass) -> Unit,
    onPartTypeClicked: (PartType) -> Unit,
    onClearClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(bottom = 16.dp)
    ) {
        if (hasFilter) {
            TextButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 8.dp),
                onClick = {
                    onClearClicked()
                },
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = stringResource(id = R.string.label_clear),
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Text(text = stringResource(id = R.string.label_clear))
            }
        }

        Column {
            Text(
                text = stringResource(id = R.string.label_class),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )

            FilterChipGroup(
                chipItems = cardClasses,
                getIcon = { cardFilter ->
                    getCardClassIcon((cardFilter as CardClassFilter).cardClass)
                },
                onChipClicked = { clickedChip ->
                    onCardClassClicked((clickedChip.cardFilter as CardClassFilter).cardClass)
                },
            )

            Text(
                text = stringResource(id = R.string.label_body_part),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )

            FilterChipGroup(
                chipItems = partTypes,
                getIcon = { cardFilter ->
                    getPartTypeIcon((cardFilter as PartTypeFilter).partType)
                },
                onChipClicked = { clickedChip ->
                    onPartTypeClicked((clickedChip.cardFilter as PartTypeFilter).partType)
                }
            )
        }
    }
}

@DrawableRes
private fun getCardClassIcon(cardClass: CardClass): Int {
    return when (cardClass) {
        CardClass.AQUATIC -> R.drawable.ic_class_aquatic
        CardClass.BIRD -> R.drawable.ic_class_bird
        CardClass.BEAST -> R.drawable.ic_class_beast
        CardClass.BUG -> R.drawable.ic_class_bug
        CardClass.PLANT -> R.drawable.ic_class_plant
        CardClass.REPTILE -> R.drawable.ic_class_reptile
    }
}

@DrawableRes
private fun getPartTypeIcon(partType: PartType): Int {
    return when (partType) {
        PartType.EYES -> R.drawable.part_eyes
        PartType.EARS -> R.drawable.part_ears
        PartType.BACK -> R.drawable.part_back
        PartType.MOUTH -> R.drawable.part_mouth
        PartType.HORN -> R.drawable.part_horn
        PartType.TAIL -> R.drawable.part_tail
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    val cardClasses = listOf(
        ChipItem(cardFilter = CardClassFilter(CardClass.AQUATIC.name, CardClass.AQUATIC), isSelected = false),
        ChipItem(cardFilter = CardClassFilter(CardClass.BEAST.name, CardClass.BEAST), isSelected = false),
    )
    val partTypes = listOf(
        ChipItem(cardFilter = PartTypeFilter(PartType.MOUTH.name, PartType.MOUTH), isSelected = false),
        ChipItem(cardFilter = PartTypeFilter(PartType.HORN.name, PartType.HORN), isSelected = false),
    )
    AxieCardBrowserDemoTheme {
        CardFilterBottomSheet(
            hasFilter = true,
            cardClasses = cardClasses,
            partTypes = partTypes,
            onCardClassClicked = {},
            onPartTypeClicked = {},
            onClearClicked = {},
        )
    }
}
