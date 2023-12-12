package com.sdv.kit.omspace.domain.mapper

import com.sdv.kit.omspace.data.remote.dto.DropboxUserData
import com.sdv.kit.omspace.domain.model.UserData

interface UserDataMapper {
    fun toUserData(dropboxUserData: DropboxUserData): UserData
}