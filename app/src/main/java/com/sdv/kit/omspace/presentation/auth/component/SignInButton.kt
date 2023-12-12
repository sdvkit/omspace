package com.sdv.kit.omspace.presentation.auth.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun SignInButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = AppTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(all = Dimens.PADDING_EXTRA_SMALL),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(Dimens.SIGN_IN_CLIENT_ICON_SIZE),
                painter = painterResource(icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(Dimens.PADDING_EXTRA_SMALL))
            Text(
                text = stringResource(text),
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colors.onPrimary
            )
        }
    }
}

@LightAndDarkDefaultPreview
@Composable
fun SignInButtonPreview() {
    AppTheme {
        SignInButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dimens.PADDING_MEDIUM),
            text = R.string.sign_in_google,
            icon = R.drawable.ic_google_512,
            onClick = {}
        )
    }
}