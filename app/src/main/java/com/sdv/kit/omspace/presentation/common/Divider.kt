package com.sdv.kit.omspace.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun Divider(
    modifier: Modifier = Modifier,
    orientation: DividerOrientation,
    color: Color,
    strokeWidth: Float = 1f
) {
    when (orientation) {
        DividerOrientation.HORIZONTAL -> {
            HorizontalDivider(
                modifier = modifier,
                color = color,
                strokeWidth = strokeWidth
            )
        }
        DividerOrientation.VERTICAL -> {
            VerticalDivider(
                modifier = modifier,
                color = color,
                strokeWidth = strokeWidth
            )
        }
    }
}

enum class DividerOrientation {
    HORIZONTAL, VERTICAL
}

@Composable
private fun HorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Float
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxWidth()) {
            drawLine(
                color = color,
                start = Offset(0f, size.height / 2),
                end = Offset(size.width, size.height / 2),
                strokeWidth = strokeWidth
            )
        }
    }
}

@Composable
private fun VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Float
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxHeight()) {
            drawLine(
                color = color,
                start = Offset(size.width / 2, 0f),
                end = Offset(size.width / 2, size.height),
                strokeWidth = strokeWidth
            )
        }
    }
}

@LightAndDarkDefaultPreview
@Composable
fun HorizontalDividerPreview() {
    AppTheme {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dimens.PADDING_MEDIUM),
            orientation = DividerOrientation.HORIZONTAL,
            color = AppTheme.colors.inverseSurface
        )
    }
}

@LightAndDarkDefaultPreview
@Composable
fun VerticalDividerPreview() {
    AppTheme {
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .padding(all = Dimens.PADDING_MEDIUM),
            orientation = DividerOrientation.VERTICAL,
            color = AppTheme.colors.inverseSurface
        )
    }
}