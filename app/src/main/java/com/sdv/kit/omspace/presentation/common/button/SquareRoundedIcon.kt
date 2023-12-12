package com.sdv.kit.omspace.presentation.common.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun SquareRoundedIcon(
    modifier: Modifier = Modifier,
    iconTint: Color,
    backgroundColor: Color,
    @DrawableRes icon: Int,
) {
    Box(
        modifier = modifier.background(
            color = backgroundColor,
            shape = AppTheme.shapes.large
        )
    ) {
        Icon(
            modifier = Modifier.padding(all = Dimens.PADDING_MEDIUM),
            painter = painterResource(icon),
            contentDescription = null,
            tint = iconTint
        )
    }
}

@LightAndDarkDefaultPreview
@Composable
fun SquareRoundedIconPreview() {
    AppTheme {
        SquareRoundedIcon(
            modifier = Modifier.size(Dimens.DEFAULT_OPTION_SIZE),
            iconTint = AppTheme.colors.onSurface,
            backgroundColor = AppTheme.colors.surface,
            icon = R.drawable.ic_connect_512
        )
    }
}