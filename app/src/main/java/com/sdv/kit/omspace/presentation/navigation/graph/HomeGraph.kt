package com.sdv.kit.omspace.presentation.navigation.graph

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import coil.compose.rememberAsyncImagePainter
import com.sdv.kit.omspace.OMSpaceApplication
import com.sdv.kit.omspace.R
import com.sdv.kit.omspace.presentation.Dimens
import com.sdv.kit.omspace.presentation.common.Divider
import com.sdv.kit.omspace.presentation.common.DividerOrientation
import com.sdv.kit.omspace.presentation.common.MessageDialog
import com.sdv.kit.omspace.presentation.common.bar.BottomNavigationBar
import com.sdv.kit.omspace.presentation.common.bar.TopBar
import com.sdv.kit.omspace.presentation.home.HomeEvent
import com.sdv.kit.omspace.presentation.home.HomeScreen
import com.sdv.kit.omspace.presentation.home.HomeViewModel
import com.sdv.kit.omspace.presentation.home.component.ConnectStorageModal
import com.sdv.kit.omspace.presentation.navigation.Route
import com.sdv.kit.omspace.ui.theme.AppTheme
import com.sdv.kit.omspace.util.RESULT_OK
import net.openid.appauth.AuthorizationResponse

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(route = Route.HomeScreen.route) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        val homeState by homeViewModel.state.collectAsState()

        val isContentLoading = homeState.isContentLoading
        val username = homeState.user?.username ?: ""
        val storages = homeState.userStorages
        val lastConnectedStorageType = homeState.lastStorageConnection
        val lastSupportedStorage = homeState.lastSupportedStorage
        val isStorageConnectedMessageVisible = homeState.isStorageConnectedMessageVisible

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val response = AuthorizationResponse.fromIntent(result.data!!)
                val authCode = response!!.authorizationCode!!
                val codeVerifier = response.request.codeVerifier!!

                homeViewModel.onEvent(HomeEvent.GetAccessToken(
                    storageType = lastConnectedStorageType,
                    authCode = authCode,
                    codeVerifier = codeVerifier
                ))
            }
        }

        OMSpaceApplication(
            topBar = {
                TopBar(
                    modifier = Modifier.fillMaxWidth(),
                    header = R.string.app_name
                )
            },
            bottomBar = {
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    orientation = DividerOrientation.HORIZONTAL,
                    color = AppTheme.colors.inverseSurface
                )
                BottomNavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.BOTTOM_NAVIGATION_BAR_HEIGHT),
                    navController = navController
                )
            },
            bottomSheet = {
                if (homeState.isConnectModalVisible) {
                    ConnectStorageModal(
                        modifier = Modifier
                            .widthIn(max = Dimens.BOTTOM_SHEET_MAX_WIDTH)
                            .fillMaxWidth(),
                        supportedStorages = homeState.supportedStorages,
                        onDismissRequest = {
                            homeViewModel.onEvent(HomeEvent.ShowConnectStorageModal(false))
                        },
                        onClickHandler = { storageType ->
                            homeViewModel.onEvent(HomeEvent.ConnectStorage(
                                launcher = launcher,
                                storageType = storageType
                            ))
                        }
                    )
                }
            },
            dialog = {
                AnimatedVisibility(
                    visible = isStorageConnectedMessageVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    val painter = rememberAsyncImagePainter(model = lastSupportedStorage.storageIcon)

                    MessageDialog(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateEnterExit(
                                enter = slideInVertically(),
                                exit = slideOutVertically()
                            )
                            .padding(top = Dimens.PADDING_MEDIUM)
                            .height(Dimens.MESSAGE_DIALOG_HEIGHT),
                        containerColor = AppTheme.specialColors.successContainer,
                        contentColor = AppTheme.specialColors.onSuccessContainer,
                        imageContainerColor = AppTheme.specialColors.successContainerVariant,
                        onDismissRequest = {
                            homeViewModel.onEvent(HomeEvent.ShowStorageConnectedMessageDialog(false))
                            homeViewModel.onEvent(HomeEvent.ShowConnectStorageModal(false))
                        },
                        painter = painter,
                        message = "${lastSupportedStorage.storageName} has been successfully connected."
                    )
                }
            }
        ) { paddings ->
            HomeScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddings),
                username = username,
                storages = storages,
                isContentLoading = isContentLoading,
                onConnectButtonClicked = {
                    homeViewModel.onEvent(HomeEvent.ShowConnectStorageModal(true))
                }
            )
        }
    }
}