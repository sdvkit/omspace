package com.sdv.kit.omspace.presentation.home.section

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun HomeWelcomeLabelSection(
    modifier: Modifier = Modifier,
    username: String
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.welcome_label),
            style = AppTheme.typography.displayMedium,
            color = AppTheme.colors.onSurfaceVariant
        )
        Text(
            text = username,
            style = AppTheme.typography.displayMedium,
            color = AppTheme.colors.onSurface,
            fontWeight = FontWeight.Bold
        )
    }
}

@LightAndDarkDefaultPreview
@Composable
fun HomeWelcomeLabelSectionPreview() {
    AppTheme {
        HomeWelcomeLabelSection(username = "Mikola Delopata")
    }
}