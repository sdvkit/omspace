package com.sdv.kit.omspace.presentation.home.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.model.SupportedStorage
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.SampleData
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkLandscapePreview
import com.sdv.kit.omspace.presentation.common.button.SquareRoundedIcon
import com.sdv.kit.omspace.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectStorageModal(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    supportedStorages: List<SupportedStorage>,
    onClickHandler: (StorageType) -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        shape = AppTheme.shapes.medium,
        containerColor = AppTheme.colors.surface
    ) {
        ConnectStorageModalContent(
            modifier = Modifier.padding(horizontal = Dimens.PADDING_MEDIUM),
            supportedStorages = supportedStorages,
            onClickHandler = onClickHandler
        )
    }
}

@Composable
private fun ConnectStorageModalContent(
    modifier: Modifier = Modifier,
    supportedStorages: List<SupportedStorage>,
    onClickHandler: (StorageType) -> Unit
) {
    Column(modifier = modifier) {
        ConnectStorageModalContentHeader(
            headerText = R.string.connect_storage,
            descriptionText = R.string.connect_storage_description
        )
        Spacer(modifier = Modifier.height(Dimens.PADDING_MEDIUM))
        ConnectStorageModalButtons(
            modifier = Modifier.fillMaxWidth(),
            supportedStorages = supportedStorages,
            onClickHandler = onClickHandler
        )
        Spacer(modifier = Modifier.height(Dimens.PADDING_BIG))
    }
}

@Composable
private fun ConnectStorageModalButtons(
    modifier: Modifier = Modifier,
    supportedStorages: List<SupportedStorage>,
    onClickHandler: (StorageType) -> Unit
) {
    Column(modifier = modifier) {
        for (supportedStorage in supportedStorages) {
            ConnectStorageButton(
                modifier = Modifier.fillMaxWidth(),
                storageName = supportedStorage.storageName,
                icon = supportedStorage.storageIcon,
                onClick = {
                    onClickHandler(supportedStorage.storageType)
                }
            )
            Spacer(modifier = Modifier.height(Dimens.PADDING_EXTRA_SMALL))
        }
    }
}

@Composable
private fun ConnectStorageModalContentHeader(
    modifier: Modifier = Modifier,
    @StringRes headerText: Int,
    @StringRes descriptionText: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SquareRoundedIcon(
            modifier = Modifier.size(Dimens.DEFAULT_OPTION_SIZE),
            iconTint = AppTheme.colors.onSurfaceVariant,
            backgroundColor = AppTheme.colors.surfaceVariant,
            icon = R.drawable.ic_connect_512
        )
        Spacer(modifier = Modifier.width(Dimens.PADDING_MEDIUM))
        Column {
            Text(
                text = stringResource(headerText),
                style = AppTheme.typography.titleLarge,
                color = AppTheme.colors.onSurface,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(descriptionText),
                style = AppTheme.typography.bodySmall,
                color = AppTheme.colors.onSurfaceVariant,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@LightAndDarkLandscapePreview
@LightAndDarkDefaultPreview
@Composable
fun ConnectStorageModalPreview() {
    AppTheme {
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberStandardBottomSheetState(
                initialValue = SheetValue.Expanded
            )
        )

        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            containerColor = AppTheme.colors.surface,
            sheetContent = {
                ConnectStorageModalContent(
                    modifier = Modifier.padding(all = Dimens.PADDING_MEDIUM),
                    supportedStorages = SampleData.supportedStorages,
                    onClickHandler = {}
                )
            },
            content = {}
        )
    }
}