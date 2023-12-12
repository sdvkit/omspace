package com.sdv.kit.omspace.presentation.home

import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.model.UserData
import com.sdv.kit.omspace.domain.model.UserStorage

data class HomeState(
    val user: UserData? = null,
    val isContentLoading: Boolean = true,
    val userStorages: List<UserStorage> = listOf(),
    val isConnectModalVisible: Boolean = false,
    val isStorageConnectedMessageVisible: Boolean = false,
    val isStorageAlreadyConnectedMessageVisible: Boolean = false,
    val lastStorageConnection: StorageType = StorageType.NONE
)