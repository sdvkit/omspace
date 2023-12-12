package com.sdv.kit.omspace.presentation.home.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.SampleData
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun ConnectStorageButton(
    modifier: Modifier = Modifier,
    storageName: String,
    icon: String,
    onClick: () -> Unit
) {
    val painter = rememberAsyncImagePainter(model = icon)

    Button(
        modifier = modifier,
        shape = AppTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.surfaceContainer,
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(all = Dimens.PADDING_EXTRA_SMALL),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(0.55f))
            Image(
                modifier = Modifier.size(Dimens.SIGN_IN_CLIENT_ICON_SIZE),
                painter = painter,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(Dimens.PADDING_EXTRA_SMALL))
            Text(
                modifier = Modifier.weight(1f),
                text = storageName,
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colors.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(0.55f))
        }
    }
}

@LightAndDarkDefaultPreview
@Composable
fun ConnectStorageButtonPreview() {
    AppTheme {
        val storage = SampleData.supportedStorages[0]

        ConnectStorageButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dimens.PADDING_MEDIUM),
            storageName = storage.storageName,
            icon = storage.storageIcon,
            onClick = {}
        )
    }
}