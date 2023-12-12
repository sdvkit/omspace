package com.sdv.kit.omspace.presentation.common

import android.view.Gravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun MessageDialog(
    modifier: Modifier = Modifier,
    containerColor: Color = AppTheme.colors.surfaceVariant,
    contentColor: Color = AppTheme.colors.onSurfaceVariant,
    imageContainerColor: Color = AppTheme.colors.surface,
    onDismissRequest: () -> Unit,
    painter: Painter,
    message: String,
    gravity: Int = Gravity.TOP
) {
    Dialog(onDismissRequest = onDismissRequest) {
        val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
        dialogWindowProvider.window.setGravity(gravity)

        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = containerColor
            ),
            shape = AppTheme.shapes.medium
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.PADDING_MEDIUM),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(Dimens.MESSAGE_DIALOG_ICON_SIZE)
                        .background(
                            color = imageContainerColor,
                            shape = CircleShape
                        )
                        .padding(all = Dimens.PADDING_EXTRA_SMALL),
                    painter = painter,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(Dimens.PADDING_SMALL))
                Text(
                    modifier = Modifier.weight(1f),
                    text = message,
                    style = AppTheme.typography.titleSmall,
                    color = contentColor
                )
                Spacer(modifier = Modifier.width(Dimens.PADDING_MEDIUM))
                IconButton(onClick = onDismissRequest) {
                    Icon(
                        modifier = Modifier.size(Dimens.CLOSE_MESSAGE_DIALOG_ICON_SIZE),
                        painter = painterResource(R.drawable.ic_cross_512),
                        contentDescription = null,
                        tint = contentColor
                    )
                }
            }
        }
    }
}

@LightAndDarkDefaultPreview
@Composable
fun MessageDialogPreview() {
    AppTheme {
        MessageDialog(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.MESSAGE_DIALOG_HEIGHT),
            onDismissRequest = {},
            painter = painterResource(R.drawable.ic_dropbox_512),
            message = "Dropbox storage message"
        )
    }
}

@LightAndDarkDefaultPreview
@Composable
fun MessageDialogSuccessPreview() {
    AppTheme {
        MessageDialog(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.MESSAGE_DIALOG_HEIGHT),
            containerColor = AppTheme.specialColors.successContainer,
            contentColor = AppTheme.specialColors.onSuccessContainer,
            imageContainerColor = AppTheme.specialColors.successContainerVariant,
            onDismissRequest = {},
            painter = painterResource(R.drawable.ic_dropbox_512),
            message = "Dropbox storage has been connected"
        )
    }
}

@LightAndDarkDefaultPreview
@Composable
fun MessageDialogErrorPreview() {
    AppTheme {
        MessageDialog(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.MESSAGE_DIALOG_HEIGHT),
            containerColor = AppTheme.colors.errorContainer,
            contentColor = AppTheme.colors.onErrorContainer,
            onDismissRequest = {},
            painter = painterResource(R.drawable.ic_dropbox_512),
            message = "Dropbox storage has been disconnected"
        )
    }
}