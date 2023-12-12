package com.sdv.kit.omspace.presentation.home.section

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkLandscapePreview
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun HomeLastFilesSection(
    modifier: Modifier = Modifier,
    @StringRes headerText: Int
) {
    Column(modifier = modifier.padding(all = Dimens.PADDING_MEDIUM)) {
        HomeLastFilesSectionHeader(headerText = headerText)
    }
}

@Composable
private fun HomeLastFilesSectionHeader(
    modifier: Modifier = Modifier,
    @StringRes headerText: Int
) {
    Text(
        modifier = modifier,
        text = stringResource(headerText),
        style = AppTheme.typography.titleMedium,
        color = AppTheme.colors.onSurfaceVariant
    )
}

@LightAndDarkLandscapePreview
@LightAndDarkDefaultPreview
@Composable
fun HomeLastFilesSectionPreview() {
    AppTheme {
        Surface(color = AppTheme.colors.surface) {
            HomeLastFilesSection(
                modifier = Modifier.fillMaxSize(),
                headerText = R.string.last_files_header
            )
        }
    }
}