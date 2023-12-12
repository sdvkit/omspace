package com.sdv.kit.omspace.presentation.auth.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
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
fun SignInContentSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(Dimens.SIGN_IN_IMAGE_SIZE),
            painter = painterResource(R.drawable.img_astronaut_512),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.app_name_full),
            style = AppTheme.typography.displayLarge,
            color = AppTheme.colors.onBackground
        )
        Text(
            text = stringResource(R.string.app_description),
            style = AppTheme.typography.bodyLarge,
            color = AppTheme.colors.onBackground
        )
    }
}

@LightAndDarkDefaultPreview
@Composable
fun AuthContentSectionPreview() {
    AppTheme {
        Surface {
            SignInContentSection(modifier = Modifier.fillMaxWidth())
        }
    }
}