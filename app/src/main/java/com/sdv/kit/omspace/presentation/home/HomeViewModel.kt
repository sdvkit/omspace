package com.sdv.kit.omspace.presentation.home

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdv.kit.omspace.data.remote.dto.AccessToken
import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.model.SupportedStorage
import com.sdv.kit.omspace.domain.usecase.auth.GetAccessToken
import com.sdv.kit.omspace.domain.usecase.auth.LaunchAuthorizationRequest
import com.sdv.kit.omspace.domain.usecase.auth.google.GetSingedInGoogleUser
import com.sdv.kit.omspace.domain.usecase.firebase.GetFirebaseIndexes
import com.sdv.kit.omspace.domain.usecase.firebase.GetFirebaseSupportedStorageList
import com.sdv.kit.omspace.domain.usecase.firebase.GetFirebaseUserStorageList
import com.sdv.kit.omspace.domain.usecase.firebase.SaveFirebaseStorageConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSingedInGoogleUser: GetSingedInGoogleUser,
    private val getFirebaseSupportedStorageList: GetFirebaseSupportedStorageList,
    private val getFirebaseUserStorageList: GetFirebaseUserStorageList,
    private val launchAuthorizationRequest: LaunchAuthorizationRequest,
    private val getAccessToken: GetAccessToken,
    private val getFirebaseIndexes: GetFirebaseIndexes,
    private val saveFirebaseStorageConnection: SaveFirebaseStorageConnection
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        getSignedInUser()
        getUserStorages()
        getIndexes()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ShowConnectStorageModal -> {
                showConnectStorageModal(event.isVisible)
            }

            is HomeEvent.ConnectStorage -> {
                connectStorage(
                    launcher = event.launcher,
                    storageType = event.storageType
                )
            }

            is HomeEvent.GetAccessToken -> {
                getAccessTokenFromAuthCode(
                    storageType = event.storageType,
                    authCode = event.authCode,
                    codeVerifier = event.codeVerifier
                )
            }

            is HomeEvent.ShowStorageConnectedMessageDialog -> {
                showStorageConnectedMessageDialog(event.isVisible)
            }
        }
    }

    private fun saveStorageConnection(
        storageType: StorageType,
        accessToken: AccessToken
    ) {
        val indexes = _state.value.firebaseIndexes

        viewModelScope.launch {
            saveFirebaseStorageConnection(storageType, accessToken, indexes)
        }
    }

    private fun getIndexes() {
        viewModelScope.launch {
            getFirebaseIndexes { firebaseIndexes ->
                _state.update { currentState ->
                    currentState.copy(firebaseIndexes = firebaseIndexes)
                }
            }
        }
    }

    private fun showStorageConnectedMessageDialog(isVisible: Boolean) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    isStorageConnectedMessageVisible = isVisible,
                    isConnectModalVisible = false
                )
            }
        }
    }

    private fun getAccessTokenFromAuthCode(
        storageType: StorageType,
        authCode: String,
        codeVerifier: String
    ) {
        viewModelScope.launch {
            val tokenResult = getAccessToken(storageType, authCode, codeVerifier)

            if (tokenResult.isSuccess) {
                saveStorageConnection(storageType, tokenResult.getOrThrow())
                showStorageConnectedMessageDialog(true)
            }
        }
    }

    private fun showConnectStorageModal(isVisible: Boolean) {
        _state.update { currentState ->
            currentState.copy(isConnectModalVisible = isVisible)
        }
    }

    private fun connectStorage(
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
        storageType: StorageType
    ) {
        viewModelScope.launch {
            val request = launchAuthorizationRequest(launcher, storageType)
            val supportedStorage = findSupportedStorageByClass(storageType) ?: return@launch

            if (request.isSuccess) {
                _state.update { currentState ->
                    currentState.copy(
                        lastStorageConnection = storageType,
                        lastSupportedStorage = supportedStorage
                    )
                }
            }
        }
    }

    private fun getUserStorages() {
        viewModelScope.launch {
            val signedInUser = getSingedInGoogleUser()

            getSupportedStorages {
                getFirebaseUserStorageList(
                    uid = signedInUser!!.userId,
                    transform = { storage ->
                        storage.supportedStorage = findSupportedStorageById(storage.supportedStorage.id)
                            ?: return@getFirebaseUserStorageList
                    }
                ) { storages ->
                    _state.update { currentState ->
                        currentState.copy(
                            userStorages = storages,
                            isContentLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun getSupportedStorages(onSuccess: () -> Unit) {
        getFirebaseSupportedStorageList { storages ->
            _state.update { currentState ->
                currentState.copy(supportedStorages = storages)
            }

            onSuccess()
        }
    }

    private fun getSignedInUser() {
        viewModelScope.launch {
            val signedInUser = getSingedInGoogleUser()

            _state.update { currentState ->
                currentState.copy(user = signedInUser)
            }
        }
    }

    private fun findSupportedStorageById(id: Long): SupportedStorage? {
        return _state.value.supportedStorages.find { it.id == id }
    }

    private fun findSupportedStorageByClass(storageType: StorageType): SupportedStorage? {
        return _state.value.supportedStorages.find { supportedStorage ->
            supportedStorage.storageType.javaClass == storageType.javaClass
        }
    }
}