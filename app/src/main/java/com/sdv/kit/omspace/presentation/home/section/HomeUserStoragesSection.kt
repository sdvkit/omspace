package com.sdv.kit.omspace.presentation.home.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.domain.model.UserStorage
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.SampleData
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkLandscapePreview
import com.sdv.kit.omspace.presentation.home.component.StorageCard
import com.sdv.kit.omspace.presentation.home.component.StorageCardShimmer
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun UserStoragesSection(
    modifier: Modifier = Modifier,
    storages: List<UserStorage>,
    isContentLoading: Boolean,
    onConnectButtonClicked: () -> Unit
) {
    when (isContentLoading) {
        true -> UserStoragesLoading(
            modifier = modifier
        )
        else -> UserStoragesLoaded(
            modifier = modifier,
            storages = storages,
            onConnectButtonClicked = onConnectButtonClicked
        )
    }
}

@Composable
private fun UserStoragesLoading(
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(10) {
            StorageCardShimmer(
                modifier = Modifier
                    .size(Dimens.STORAGE_CARD_SIZE)
                    .padding(start = Dimens.PADDING_MEDIUM)
            )
        }
    }
}

@Composable
private fun UserStoragesLoaded(
    modifier: Modifier = Modifier,
    storages: List<UserStorage>,
    onConnectButtonClicked: () -> Unit
) {
    LazyRow(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        items(storages) { storage ->
            StorageCard(
                modifier = Modifier
                    .size(Dimens.STORAGE_CARD_SIZE)
                    .padding(start = Dimens.PADDING_MEDIUM)
                    .shadow(
                        elevation = 5.dp,
                        AppTheme.shapes.extraLarge
                    ),
                storage = storage
            )
        }
        item {
            Spacer(modifier = Modifier.width(Dimens.PADDING_MEDIUM))
            ConnectButton(
                modifier = Modifier.size(Dimens.CONNECT_BUTTON_SIZE),
                onClick = onConnectButtonClicked
            )
        }
    }
}

@Composable
private fun ConnectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor =  AppTheme.colors.onPrimaryContainer,
            containerColor = AppTheme.colors.primaryContainer
        )
    ) {
        Icon(
            modifier = Modifier.size(Dimens.CONNECT_BUTTON_ICON_SIZE),
            painter = painterResource(R.drawable.ic_plus_512),
            contentDescription = null
        )
    }
}

@LightAndDarkLandscapePreview
@LightAndDarkDefaultPreview
@Composable
fun UserStoragesSectionLoadingPreview() {
    AppTheme {
        UserStoragesSection(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = AppTheme.colors.surface),
            storages = SampleData.userStorages,
            isContentLoading = true,
            onConnectButtonClicked = {}
        )
    }
}

@LightAndDarkLandscapePreview
@LightAndDarkDefaultPreview
@Composable
fun UserStoragesSectionLoadedPreview() {
    AppTheme {
        UserStoragesSection(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = AppTheme.colors.surface),
            storages = SampleData.userStorages,
            isContentLoading = false,
            onConnectButtonClicked = {}
        )
    }
}