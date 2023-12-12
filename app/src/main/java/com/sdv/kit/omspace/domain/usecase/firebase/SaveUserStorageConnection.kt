package com.sdv.kit.omspace.domain.usecase.firebase

import com.sdv.kit.omspace.data.repository.DropboxRepositoryImpl
import com.sdv.kit.omspace.domain.model.UserStorage
import javax.inject.Inject

class SaveUserStorageConnection @Inject constructor (
    private val dropboxRepositoryImpl: DropboxRepositoryImpl
) {

    suspend operator fun invoke(userStorage: UserStorage): Result<UserStorage> {
        return dropboxRepositoryImpl.saveUserStorageConnection(userStorage )
    }
}