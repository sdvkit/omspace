package com.sdv.kit.omspace.domain.usecase.auth.google

import com.sdv.kit.omspace.domain.model.UserData
import com.sdv.kit.omspace.domain.repository.UserRepository
import javax.inject.Inject

class GetSignedInGoogleUser @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Result<UserData> {
        return try {
            val userData = userRepository.getGoogleSignedInUser()!!
            Result.success(userData)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}