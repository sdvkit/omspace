package com.sdv.kit.omspace.presentation.navigation

sealed class Route(val route: String) {
    data object SignInScreen : Route(route = "auth")
    data object HomeScreen : Route(route = "home")
}