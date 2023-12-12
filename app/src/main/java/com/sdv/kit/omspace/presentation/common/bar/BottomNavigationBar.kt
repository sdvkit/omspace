package com.sdv.kit.omspace.presentation.common.bar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.annotation.LightAndDarkDefaultPreview
import com.sdv.kit.omspace.presentation.navigation.Route
import com.sdv.kit.omspace.ui.theme.AppTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavigationBar(
        modifier = modifier,
        containerColor = AppTheme.colors.surface
    ) {
        Spacer(modifier = Modifier.weight(1f))
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(Route.HomeScreen.route)
            },
            icon = {
                Icon(
                    modifier = Modifier.size(Dimens.NAVIGATION_ITEM_ICON_SIZE),
                    painter = painterResource(R.drawable.ic_home_512),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    modifier = Modifier.size(Dimens.NAVIGATION_ITEM_ICON_SIZE),
                    painter = painterResource(R.drawable.ic_search_512),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@LightAndDarkDefaultPreview
@Composable
fun BottomNavigationBarPreview() {
    AppTheme {
        BottomNavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.BOTTOM_NAVIGATION_BAR_HEIGHT),
            navController = rememberNavController()
        )
    }
}