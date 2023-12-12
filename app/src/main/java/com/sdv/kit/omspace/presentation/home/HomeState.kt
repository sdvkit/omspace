package com.sdv.kit.omspace.presentation.home

import com.sdv.kit.omspace.domain.model.FirebaseIndexes
import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.model.SupportedStorage
import com.sdv.kit.omspace.domain.model.UserData
import com.sdv.kit.omspace.domain.model.UserStorage

data class HomeState(
    val user: UserData? = null,
    val isContentLoading: Boolean = true,
    val supportedStorages: List<SupportedStorage> = listOf(),
    val userStorages: List<UserStorage> = listOf(),
    val isConnectModalVisible: Boolean = false,
    val isStorageConnectedMessageVisible: Boolean = false,
    val lastStorageConnection: StorageType = StorageType.None,
    val lastSupportedStorage: SupportedStorage = SupportedStorage(),
    val firebaseIndexes: FirebaseIndexes = FirebaseIndexes()
)