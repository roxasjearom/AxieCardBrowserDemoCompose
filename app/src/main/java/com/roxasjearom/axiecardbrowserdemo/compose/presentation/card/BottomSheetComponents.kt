package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roxasjearom.axiecardbrowserdemo.compose.R
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.CardClass
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.PartType
import com.roxasjearom.axiecardbrowserdemo.compose.ui.theme.AxieCardBrowserDemoTheme

@Composable
fun CardFilterBottomSheet(
    onCardClassClicked: (CardClass) -> Unit,
    onPartTypeClicked: (PartType) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.label_class),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(8.dp)
        )

        FilterChipGroup(
            filters = CardClass.values(),
            getIcon = { cardClass ->
                getCardClassIcon(cardClass)
            },
            onChipClicked = { clickedClass ->
                onCardClassClicked(clickedClass)
            }
        )

        Text(
            text = stringResource(id = R.string.label_body_part),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(8.dp)
        )

        FilterChipGroup(
            filters = PartType.values(),
            getIcon = { partType ->
                getPartTypeIcon(partType)
            },
            onChipClicked = { clickedPartType ->
                onPartTypeClicked(clickedPartType)
            }
        )
    }
}

@Composable
fun <T> FilterChipGroup(
    filters: Array<T>,
    getIcon: (T) -> Int,
    onChipClicked: (clickedChip: T) -> Unit
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        for (filter in filters) {
            CardFilterChip(
                filter = filter,
                icon = getIcon(filter),
                onChipClicked = { selectedFilter ->
                    onChipClicked(selectedFilter)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> CardFilterChip(
    filter: T,
    @DrawableRes icon: Int,
    onChipClicked: (clickedChip: T) -> Unit
) {
    var isSelected by remember { mutableStateOf(false) }

    FilterChip(
        selected = isSelected,
        onClick = {
            onChipClicked(filter)
            isSelected = !isSelected
        },
        border = FilterChipDefaults.filterChipBorder(),
        leadingIcon = {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(16.dp)
            )
        },
        selectedIcon = {
            Icon(Icons.Rounded.Check, contentDescription = null)
        },
        label = {
            val filterEnum = filter as Enum<*>
            Text(text = filterEnum.name.lowercase().replaceFirstChar { it.uppercase() })
        }
    )
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
    AxieCardBrowserDemoTheme {
        CardFilterBottomSheet(onCardClassClicked = {}, onPartTypeClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ChipPreview() {
    AxieCardBrowserDemoTheme {
        CardFilterChip(
            filter = CardClass.AQUATIC,
            icon = R.drawable.ic_class_aquatic,
            onChipClicked = {}
        )
    }
}
