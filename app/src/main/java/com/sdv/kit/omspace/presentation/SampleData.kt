package com.sdv.kit.omspace.presentation

import com.sdv.kit.omspace.domain.model.SupportedStorage
import com.sdv.kit.omspace.domain.model.UserStorage

object SampleData {
    val supportedStorages: List<SupportedStorage> by lazy {
        listOf(
            SupportedStorage(
                storageName = "Dropbox",
                storageIcon = "https://firebasestorage.googleapis.com/v0/b/omspace-d0957.appspot.com/o/icons%2Flogos%2Fic_dropbox_512.png?alt=media&token=49d0d191-9aba-43a8-a3ad-ec108fd0e47d"
            ),
            SupportedStorage(
                storageName = "Google Drive",
                storageIcon =
                "https://firebasestorage.googleapis.com/v0/b/omspace-d0957.appspot.com/o/icons%2Flogos%2Fic_google_drive_512.png?alt=media&token=29a0e692-f679-45e8-96c9-150bfb594baa"
            )
        )
    }

    val userStorages: List<UserStorage> by lazy {
        listOf(
            UserStorage(
                supportedStorage = supportedStorages[0],
                storageOwnerUsername = "sdvkit",
                totalCapacity = 2f,
                occupiedSpace = 0f
            ),
            UserStorage(
                supportedStorage = supportedStorages[1],
                storageOwnerUsername = "Goosdvkit",
                totalCapacity = 15f,
                occupiedSpace = 0f
            )
        )
    }
}