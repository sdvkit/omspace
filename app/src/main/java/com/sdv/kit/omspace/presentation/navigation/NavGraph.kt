package com.sdv.kit.omspace.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sdv.kit.omspace.presentation.navigation.graph.homeGraph
import com.sdv.kit.omspace.presentation.navigation.graph.signInGraph

@Composable
fun NavGraph(startDestination: Route) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        signInGraph(navController)
        homeGraph(navController)
    }
}