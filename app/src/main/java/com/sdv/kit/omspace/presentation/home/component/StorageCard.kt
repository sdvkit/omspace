package com.sdv.kit.omspace.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.domain.model.StorageType
import com.sdv.kit.omspace.domain.model.UserStorage
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.SampleData
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.presentation.common.button.SquareRoundedIconButton
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun StorageCard(
    modifier: Modifier = Modifier,
    storage: UserStorage
) {
    val storageType = StorageType.nameOf(storage.storageTypeName)
    val supportedStorageIcon = storageType.storageIcon
    val supportedStorageName = storageType.storageName
    val storageOwnerUsername = storage.storageOwner.storageOwnerUsername
    val occupiedSpace = storage.occupiedSpace
    val totalCapacity = storage.totalCapacity

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surfaceVariant
        ),
        shape = AppTheme.shapes.extraLarge
    ) {
        Column(modifier = Modifier.padding(all = Dimens.PADDING_MEDIUM)) {
            StorageIcon(icon = supportedStorageIcon)
            Spacer(modifier = Modifier.height(Dimens.PADDING_MEDIUM))
            StorageCardOptions()
            Spacer(modifier = Modifier.height(Dimens.PADDING_MEDIUM))
            StorageName(
                supportedStorageName = supportedStorageName,
                storageOwnerUsername = storageOwnerUsername
            )
            Spacer(modifier = Modifier.weight(1f))
            StorageCapacity(
                modifier = Modifier.fillMaxWidth(),
                occupiedSpace = occupiedSpace,
                totalCapacity = totalCapacity
            )
        }
    }
}

@Composable
private fun StorageIcon(
    modifier: Modifier = Modifier,
    icon: String
) {
    val painter = rememberAsyncImagePainter(model = icon)

    Image(
        modifier = modifier
            .size(Dimens.STORAGE_ICON_SIZE)
            .background(
                color = AppTheme.colors.surface,
                shape = CircleShape
            )
            .padding(all = Dimens.PADDING_MEDIUM),
        painter = painter,
        contentDescription = null
    )
}

@Composable
private fun StorageCardOptions(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        SquareRoundedIconButton(
            modifier = Modifier.size(Dimens.DEFAULT_OPTION_SIZE),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colors.surface,
                contentColor = AppTheme.colors.onSurface
            ),
            icon = R.drawable.ic_dsiconnect_512,
            onClick = {}
        )
        Spacer(modifier = Modifier.width(Dimens.PADDING_SMALL))
        SquareRoundedIconButton(
            modifier = Modifier.size(Dimens.DEFAULT_OPTION_SIZE),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colors.surface,
                contentColor = AppTheme.colors.onSurface
            ),
            icon = R.drawable.ic_edit_plan_512,
            onClick = {}
        )
    }
}

@Composable
private fun StorageName(
    modifier: Modifier = Modifier,
    supportedStorageName: String,
    storageOwnerUsername: String
) {
    Column(modifier = modifier) {
        Text(
            text = supportedStorageName,
            style = AppTheme.typography.titleLarge,
            color = AppTheme.colors.primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "($storageOwnerUsername)",
            style = AppTheme.typography.titleMedium,
            color = AppTheme.colors.onSurfaceVariant
        )
    }
}

@Composable
private fun StorageCapacity(
    modifier: Modifier = Modifier,
    occupiedSpace: Float,
    totalCapacity: Float
) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.weight(1f),
            text = "${occupiedSpace}Gb",
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colors.primary
        )
        Text(
            text = "${totalCapacity}Gb",
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colors.primary
        )
    }
    LinearProgressIndicator(
        progress = { occupiedSpace / 100 },
        modifier = Modifier.fillMaxWidth(),
        color = AppTheme.colors.primary,
        trackColor = AppTheme.colors.surface,
    )
}

@LightAndDarkDefaultPreview
@Composable
fun StorageCardPreview() {
    AppTheme {
        StorageCard(
            modifier = Modifier.size(Dimens.STORAGE_CARD_SIZE),
            storage = SampleData.userStorages[1]
        )
    }
}