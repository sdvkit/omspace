package com.sdv.kit.omspace.presentation.home

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.sdv.kit.omspace.domain.model.StorageType

sealed class HomeEvent {

    class ShowConnectStorageModal(val isVisible: Boolean) : HomeEvent()

    class ConnectStorage(
        val launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
        val storageType: StorageType
    ) : HomeEvent()

    class GetAccessToken(
        val storageType: StorageType,
        val authCode: String,
        val codeVerifier: String
    ) : HomeEvent()

    class ShowStorageConnectedMessageDialog(val isVisible: Boolean) : HomeEvent()
}