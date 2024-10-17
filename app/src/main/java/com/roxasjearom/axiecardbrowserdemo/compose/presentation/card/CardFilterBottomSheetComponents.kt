package com.roxasjearom.axiecardbrowserdemo.compose.presentation.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roxasjearom.axiecardbrowserdemo.compose.R
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.CardClass
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.CardClassFilter
import com.roxasjearom.axiecardbrowserdemo.compose.domain.model.CardFilter
import com.roxasjearom.axiecardbrowserdemo.compose.ui.theme.AxieCardBrowserDemoTheme

@Composable
fun FilterChipGroup(
    chipItems: List<ChipItem>,
    getIcon: (CardFilter) -> Int,
    onChipClicked: (clickedChip: ChipItem) -> Unit,
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        for (chipItem in chipItems) {
            StatelessCardFilterChip(
                chipItem = chipItem,
                icon = getIcon(chipItem.cardFilter),
                onChipClicked = { selectedChip ->
                    onChipClicked(selectedChip)
                }
            )
        }
    }
}

@Composable
fun StatefulCardFilterChip(
    chipItem: ChipItem,
    @DrawableRes icon: Int,
    onChipClicked: (clickedChip: ChipItem) -> Unit,
) {
    var isSelected by rememberSaveable { mutableStateOf(false) }

    StatelessCardFilterChip(
        chipItem = chipItem,
        icon = icon,
        onChipClicked = {
            onChipClicked(chipItem)
            isSelected = !isSelected
        }
    )
}

@Composable
fun StatelessCardFilterChip(
    chipItem: ChipItem,
    @DrawableRes icon: Int,
    onChipClicked: (clickedChip: ChipItem) -> Unit,
) {
    FilterChip(
        selected = chipItem.isSelected,
        onClick = {
            onChipClicked(chipItem)
        },
        border = FilterChipDefaults.filterChipBorder(enabled = true, selected = chipItem.isSelected),
        leadingIcon = {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(16.dp)
            )
        },
        label = {
            Text(text = chipItem.cardFilter.id.lowercase().replaceFirstChar { it.uppercase() })
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CardFilterChipPreview() {
    AxieCardBrowserDemoTheme {
        StatefulCardFilterChip(
            chipItem = ChipItem(CardClassFilter("AQUATIC", CardClass.AQUATIC), false),
            icon = R.drawable.ic_class_aquatic,
            onChipClicked = {}
        )
    }
}
