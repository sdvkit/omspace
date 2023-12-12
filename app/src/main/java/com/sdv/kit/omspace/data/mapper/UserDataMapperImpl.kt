package com.sdv.kit.omspace.data.mapper

import com.sdv.kit.omspace.data.remote.dto.DropboxUserData
import com.sdv.kit.omspace.domain.mapper.UserDataMapper
import com.sdv.kit.omspace.domain.model.UserData

class UserDataMapperImpl : UserDataMapper {

    override fun toUserData(dropboxUserData: DropboxUserData): UserData {
        return UserData(
            userId = dropboxUserData.accountId,
            username = dropboxUserData.name.displayName,
            profilePictureUrl = null
        )
    }
}