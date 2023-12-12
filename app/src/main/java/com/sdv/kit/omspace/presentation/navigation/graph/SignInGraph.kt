package com.sdv.kit.omspace.presentation.navigation.graph

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sdv.kit.omspace.OMSpaceApplication
import com.sdv.kit.omspace.presentation.auth.SignInEvent
import com.sdv.kit.omspace.presentation.auth.SignInScreen
import com.sdv.kit.omspace.presentation.auth.SignInViewModel
import com.sdv.kit.omspace.presentation.navigation.Route

fun NavGraphBuilder.signInGraph(navController: NavHostController) {
    composable(route = Route.SignInScreen.route) {
        val signInViewModel: SignInViewModel = hiltViewModel()
        val signInState by signInViewModel.state.collectAsState()

        val googleSignInLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartIntentSenderForResult(),
            onResult = { result ->
                if(result.resultCode == ComponentActivity.RESULT_OK) {
                    signInViewModel.onEvent(SignInEvent.SignIn(result.data))
                }
            }
        )

        OMSpaceApplication { paddings ->
            SignInScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddings),
                onGoogleSignInButtonClicked = {
                    val event = SignInEvent.LaunchIntentSender(googleSignInLauncher)
                    signInViewModel.onEvent(event)
                },
                errorMessage = signInState.authErrorMessage
            )
        }

        if (signInState.isSignInSuccessful) {
            navController.navigate(Route.HomeScreen.route)
        }
    }
}