package com.sdv.kit.omspace.presentation.home

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdv.kit.omspace.domain.model.StorageConnection
import com.sdv.kit.omspace.domain.model.StorageOwner
import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.model.UserStorage
import com.sdv.kit.omspace.domain.usecase.auth.GetAccessToken
import com.sdv.kit.omspace.domain.usecase.auth.LaunchAuthorizationRequest
import com.sdv.kit.omspace.domain.usecase.auth.google.GetSignedInGoogleUser
import com.sdv.kit.omspace.domain.usecase.firebase.GetConnectedUserData
import com.sdv.kit.omspace.domain.usecase.firebase.ObserveFirebaseUserStorageList
import com.sdv.kit.omspace.domain.usecase.firebase.SaveUserStorageConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSignedInGoogleUser: GetSignedInGoogleUser,
    private val observeFirebaseUserStorageList: ObserveFirebaseUserStorageList,
    private val launchAuthorizationRequest: LaunchAuthorizationRequest,
    private val getAccessToken: GetAccessToken,
    private val saveUserStorageConnection: SaveUserStorageConnection,
    private val getConnectedUserData: GetConnectedUserData
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        getSignedInUser()
        getUserStorages()
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

            is HomeEvent.ShowStorageAlreadyConnectedMessageDialog -> {
                showStorageAlreadyConnectedMessageDialog(event.isVisible)
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

    private fun showStorageAlreadyConnectedMessageDialog(isVisible: Boolean) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    isStorageAlreadyConnectedMessageVisible = isVisible,
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
            val tokenResult = getAccessToken(
                storageType = storageType,
                authCode = authCode,
                codeVerifier = codeVerifier
            )

            if (tokenResult.isFailure) {
                return@launch
            }

            val connectedUserDataResult = getConnectedUserData(
                storageType = storageType,
                accessToken = tokenResult.getOrThrow()
            )

            if (connectedUserDataResult.isFailure) {
                return@launch
            }

            if (findUserStorageByOwnerIdAndType(
                    storageType = storageType,
                    storageOwnerId = connectedUserDataResult.getOrThrow().userId
            ) != null) {
                showStorageAlreadyConnectedMessageDialog(true)
                return@launch
            }

            val saveResult = saveUserStorageConnection(userStorage = UserStorage(
                storageTypeName = storageType.storageName,
                storageConnection = StorageConnection(
                    accessToken = tokenResult.getOrThrow()
                ),
                storageOwner = StorageOwner(
                    storageOwnerId = connectedUserDataResult.getOrThrow().userId,
                    storageOwnerUsername = connectedUserDataResult.getOrThrow().username ?: return@launch
                ),
                occupiedSpace = 0f,
                totalCapacity = 0f
            ))

            if (saveResult.isSuccess) {
                showStorageConnectedMessageDialog(true)
            }
        }
    }

    private fun findUserStorageByOwnerIdAndType(
        storageOwnerId: String,
        storageType: StorageType
    ): UserStorage? {
        return _state.value.userStorages.find { storage ->
            storage.storageOwner.storageOwnerId == storageOwnerId
                    && storage.storageTypeName == storageType.storageName
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
            val request = launchAuthorizationRequest(
                launcher = launcher,
                storageType = storageType
            )

            if (request.isSuccess) {
                _state.update { currentState ->
                    currentState.copy(lastStorageConnection = storageType)
                }
            }
        }
    }

    private fun getUserStorages() {
        viewModelScope.launch {
            val signedInUserResult = getSignedInGoogleUser()

            if (signedInUserResult.isFailure) {
                return@launch
            }

            observeFirebaseUserStorageList(
                uid = signedInUserResult.getOrThrow().userId
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

    private fun getSignedInUser() {
        viewModelScope.launch {
            val signedInUserResult = getSignedInGoogleUser()

            if (signedInUserResult.isFailure) {
                return@launch
            }

            _state.update { currentState ->
                currentState.copy(user = signedInUserResult.getOrThrow())
            }
        }
    }
}