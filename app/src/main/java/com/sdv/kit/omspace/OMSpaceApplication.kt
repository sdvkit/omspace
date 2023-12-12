package com.sdv.kit.omspace

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OMSpaceApplication : Application()

@Composable
fun OMSpaceApplication(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    bottomSheet: @Composable () -> Unit = {},
    dialog: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    dialog()
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        content = { content(it) }
    )
    bottomSheet()
}