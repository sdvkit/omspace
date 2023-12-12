package com.sdv.kit.omspace.presentation.annotation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    showBackground = true,
    name = "Landscape light mode preview",
    backgroundColor = 0xFF000000,
    widthDp = 915,
    heightDp = 412
)
@Preview(
    showBackground = true,
    name = "Landscape dark mode preview",
    uiMode = UI_MODE_NIGHT_YES,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 915,
    heightDp = 412
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class LightAndDarkLandscapePreview