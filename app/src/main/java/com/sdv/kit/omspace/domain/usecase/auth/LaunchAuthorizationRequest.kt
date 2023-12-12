package com.sdv.kit.omspace.domain.usecase.auth

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.sdv.kit.omspace.domain.auth.ConnectCodeManager
import com.sdv.kit.omspace.domain.model.StorageType
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import javax.inject.Inject

class LaunchAuthorizationRequest @Inject constructor(
    private val authorizationService: AuthorizationService,
    private val connectCodeManager: ConnectCodeManager
) {
    operator fun invoke(
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
        storageType: StorageType
    ): Result<Boolean> {
        val authorizationRequest = buildAuthorizationRequest(storageType)

        if (authorizationRequest.isFailure) {
            return Result.failure(authorizationRequest.exceptionOrNull()!!)
        }

        val intent = authorizationService.getAuthorizationRequestIntent(authorizationRequest.getOrThrow())
        launcher.launch(intent)

        return Result.success(true)
    }

    private fun buildAuthorizationRequest(storageType: StorageType): Result<AuthorizationRequest> {
        when (storageType) {
            is StorageType.Dropbox -> {
                val redirectUri = Uri.parse(storageType.redirectUri)
                val authorizeUri = Uri.parse(storageType.authorizeUri)
                val tokenUri = Uri.parse(storageType.tokenUri)

                val config = AuthorizationServiceConfiguration(authorizeUri, tokenUri)

                val codeVerifier = connectCodeManager.getCodeVerifier()
                val codeChallenge = connectCodeManager.getCodeChallenge(codeVerifier)

                val request = AuthorizationRequest
                    .Builder(config, storageType.clientId, storageType.responseType, redirectUri)
                    .setScopes(storageType.scopes)
                    .setCodeVerifier(codeVerifier, codeChallenge, "S256")
                    .build()

                return Result.success(request)
            }

            else -> return Result.failure(
                RuntimeException("Something goes wrong with StorageType $storageType")
            )
        }
    }
}