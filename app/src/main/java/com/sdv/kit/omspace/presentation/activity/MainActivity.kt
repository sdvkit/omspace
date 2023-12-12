package com.sdv.kit.omspace.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sdv.kit.omspace.presentation.navigation.NavGraph
import com.sdv.kit.omspace.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = {
                mainActivityViewModel.isLoading.value
            })
        }

        setContent {
            AppTheme {
                NavGraph(startDestination = mainActivityViewModel.startDestination.value)
            }
        }
    }
}