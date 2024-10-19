package com.roxasjearom.axiecardbrowserdemo.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.roxasjearom.axiecardbrowserdemo.compose.presentation.card.CardViewModel
import com.roxasjearom.axiecardbrowserdemo.compose.presentation.card.OriginCardScreenWithBottomSheet
import com.roxasjearom.axiecardbrowserdemo.compose.ui.theme.AxieCardBrowserDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AxieCardBrowserDemoTheme {
                val viewModel: CardViewModel by viewModels()
                OriginCardScreenWithBottomSheet(viewModel)
            }
        }
    }
}
