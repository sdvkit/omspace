package com.sdv.kit.omspace.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkLandscapePreview
import com.sdv.kit.omspace.presentation.auth.section.SignInButtonsSection
import com.sdv.kit.omspace.presentation.auth.section.SignInContentSection
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onGoogleSignInButtonClicked: () -> Unit,
    errorMessage: String?
) {
    Column(
        modifier = modifier.background(color = AppTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInContentSection()
        Spacer(modifier = Modifier.height(Dimens.PADDING_BIG))
        SignInButtonsSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.PADDING_MEDIUM),
            onGoogleSignInButtonClicked = onGoogleSignInButtonClicked
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(Dimens.PADDING_BIG))
            Text(
                text = errorMessage,
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colors.error
            )
        }
    }
}

@LightAndDarkLandscapePreview
@LightAndDarkDefaultPreview
@Composable
fun AuthScreenPreview() {
    AppTheme {
        SignInScreen(
            modifier = Modifier.fillMaxSize(),
            onGoogleSignInButtonClicked = {},
            errorMessage = null
        )
    }
}

@LightAndDarkLandscapePreview
@LightAndDarkDefaultPreview
@Composable
fun AuthScreenErrorPreview() {
    AppTheme {
        SignInScreen(
            modifier = Modifier.fillMaxSize(),
            onGoogleSignInButtonClicked = {},
            errorMessage = "Something goes wrong..."
        )
    }
}
