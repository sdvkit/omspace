package com.sdv.kit.omspace.presentation.common.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun SquareRoundedIconButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Button(
        modifier= modifier,
        shape = AppTheme.shapes.large,
        contentPadding = PaddingValues(0.dp),
        colors = colors,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.padding(all = Dimens.PADDING_MEDIUM),
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}

@LightAndDarkDefaultPreview
@Composable
fun SquareRoundedIconButtonPreview() {
    AppTheme {
        SquareRoundedIconButton(
            modifier = Modifier.size(Dimens.DEFAULT_OPTION_SIZE),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colors.surface,
                contentColor = AppTheme.colors.onSurface
            ),
            icon = R.drawable.ic_connect_512,
            onClick = {}
        )
    }
}