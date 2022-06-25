package com.roxasjearom.axiecardbrowserdemo.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.roxasjearom.axiecardbrowserdemo.compose.presentation.card.OriginCardScreenWithBottomSheet
import com.roxasjearom.axiecardbrowserdemo.compose.ui.theme.AxieCardBrowserDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AxieCardBrowserDemoTheme {
                OriginCardScreenWithBottomSheet()
            }
        }
    }
}
