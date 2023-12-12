package com.sdv.kit.omspace.domain.usecase.auth.google

import com.sdv.kit.omspace.domain.model.UserData
import com.sdv.kit.omspace.domain.repository.UserRepository
import javax.inject.Inject

class GetSingedInGoogleUser @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): UserData? =
        userRepository.getGoogleSignedInUser()
}