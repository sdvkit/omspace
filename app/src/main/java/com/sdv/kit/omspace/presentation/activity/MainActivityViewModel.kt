package com.sdv.kit.omspace.presentation.activity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdv.kit.omspace.domain.usecase.auth.google.GetSignedInGoogleUser
import com.sdv.kit.omspace.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getSignedInGoogleUser: GetSignedInGoogleUser
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<Route> = mutableStateOf(Route.SignInScreen)
    val startDestination: State<Route> = _startDestination

    init {
        viewModelScope.launch {
            _isLoading.value = true
            _startDestination.value = getStartDestination()
            _isLoading.value = false
        }
    }

    private suspend fun getStartDestination(): Route {
        return when (getSignedInGoogleUser() == null) {
            true -> Route.SignInScreen
            else -> Route.HomeScreen
        }
    }
}