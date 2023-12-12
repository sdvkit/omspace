package com.sdv.kit.omspace.presentation.auth.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.presentation.auth.component.SignInButton
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun SignInButtonsSection(
    modifier: Modifier = Modifier,
    onGoogleSignInButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInButton(
            modifier = Modifier
                .widthIn(max = Dimens.MAX_SIGN_IN_BUTTON_WIDTH)
                .fillMaxWidth(),
            text = R.string.sign_in_google,
            icon = R.drawable.ic_google_512,
            onClick = onGoogleSignInButtonClicked
        )
    }
}

@LightAndDarkDefaultPreview
@Composable
fun AuthButtonsSectionPreview() {
    AppTheme {
        SignInButtonsSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dimens.PADDING_MEDIUM),
            onGoogleSignInButtonClicked = {}
        )
    }
}