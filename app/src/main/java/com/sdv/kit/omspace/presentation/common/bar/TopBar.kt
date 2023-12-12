package com.sdv.kit.omspace.presentation.common.bar

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    @StringRes header: Int
) {
    Row(modifier = modifier
        .background(color = AppTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dimens.PADDING_SMALL),
            textAlign = TextAlign.Center,
            text = stringResource(header),
            style = AppTheme.typography.titleMedium,
            color = AppTheme.colors.onPrimary,
            fontWeight = FontWeight.Bold
        )
    }
}

@LightAndDarkDefaultPreview
@Composable
fun TopBarPreview() {
    AppTheme {
        TopBar(
            modifier = Modifier.fillMaxWidth(),
            header = R.string.app_name
        )
    }
}