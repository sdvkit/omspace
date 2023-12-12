package com.sdv.kit.omspace.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.domain.model.UserStorage
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.SampleData
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkLandscapePreview
import com.sdv.kit.omspace.presentation.home.section.HomeLastFilesSection
import com.sdv.kit.omspace.presentation.home.section.HomeWelcomeLabelSection
import com.sdv.kit.omspace.presentation.home.section.UserStoragesSection
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    username: String,
    storages: List<UserStorage>,
    isContentLoading: Boolean,
    onConnectButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = AppTheme.colors.surface)
            .verticalScroll(state = rememberScrollState())
    ) {
        HomeWelcomeLabelSection(
            modifier = Modifier.padding(all = Dimens.PADDING_MEDIUM),
            username = username
        )
        Spacer(modifier = Modifier.height(Dimens.PADDING_MEDIUM))
        UserStoragesSection(
            modifier = Modifier.fillMaxWidth(),
            storages = storages,
            isContentLoading = isContentLoading,
            onConnectButtonClicked = onConnectButtonClicked
        )
        HomeLastFilesSection(
            modifier = Modifier.fillMaxWidth(),
            headerText = R.string.last_files_header
        )
    }
}

@LightAndDarkLandscapePreview
@LightAndDarkDefaultPreview
@Composable
fun HomeScreenLoadingPreview() {
    AppTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            username = "Mikola Delopata",
            storages = SampleData.userStorages,
            isContentLoading = true,
            onConnectButtonClicked = {}
        )
    }
}

@LightAndDarkLandscapePreview
@LightAndDarkDefaultPreview
@Composable
fun HomeScreenLoadedPreview() {
    AppTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            username = "Mikola Delopata",
            storages = SampleData.userStorages,
            isContentLoading = false,
            onConnectButtonClicked = {}
        )
    }
}