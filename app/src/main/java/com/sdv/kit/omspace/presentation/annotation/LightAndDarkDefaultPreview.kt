package com.sdv.kit.omspace.presentation.annotation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Default light mode preview",
    showBackground = true
)
@Preview(
    name = "Default dark mode preview",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class LightAndDarkDefaultPreview