package com.sdv.kit.omspace.domain.model

data class FirebaseIndexes(
    var nextConnectionId: Long = 0,
    var nextSupportedStorageId: Long = 0,
    var nextUserStorageId: Long = 0
)