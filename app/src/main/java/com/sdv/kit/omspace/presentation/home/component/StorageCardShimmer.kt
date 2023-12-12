package com.sdv.kit.omspace.presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.presentation.common.shimmerEffect
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun StorageCardShimmer(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surfaceVariant
        ),
        shape = AppTheme.shapes.extraLarge
    ) {
        Column(modifier = Modifier.padding(all = Dimens.PADDING_MEDIUM)) {
            Box(
                modifier = Modifier
                    .size(Dimens.STORAGE_ICON_SIZE)
                    .shimmerEffect(shape = CircleShape)
            )
            Spacer(modifier = Modifier.height(Dimens.PADDING_MEDIUM))
            Row {
                Box(
                    modifier = Modifier
                        .size(Dimens.DEFAULT_OPTION_SIZE)
                        .shimmerEffect(shape = AppTheme.shapes.large)
                )
                Spacer(modifier = Modifier.width(Dimens.PADDING_SMALL))
                Box(
                    modifier = Modifier
                        .size(Dimens.DEFAULT_OPTION_SIZE)
                        .shimmerEffect(shape = AppTheme.shapes.large)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .shimmerEffect(shape = AppTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(Dimens.PADDING_MEDIUM))
            Row {
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .width(35.dp)
                        .shimmerEffect(shape = AppTheme.shapes.small)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .width(35.dp)
                        .shimmerEffect(shape = AppTheme.shapes.small)
                )
            }
            Spacer(modifier = Modifier.height(Dimens.PADDING_EXTRA_SMALL))
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .shimmerEffect(shape = AppTheme.shapes.small)
            )
        }
    }
}

@LightAndDarkDefaultPreview
@Composable
fun StorageCardShimmerPreview() {
    AppTheme {
        StorageCardShimmer(modifier = Modifier.size(Dimens.STORAGE_CARD_SIZE))
    }
}