package com.abzagency.testtask.ui.route.bottomnavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.backgroundSecondary
import com.abzagency.testtask.com.abzagency.testtask.ui.route.bottomnavigation.BottomBarItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBarScaffold(
    bottomNavItems: List<BottomNavItem>,
    content: @Composable (
        navController: NavHostController,
        showBottomBar: (isShow: Boolean) -> Unit
    ) -> Unit
) {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    val showBottomBar = remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Colors.backgroundSecondary())
                    .height(Dimens.bottomBarHeight),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showBottomBar.value) {
                        bottomNavItems.forEach { item ->
                            BottomBarItem(
                                modifier = Modifier.weight(1f),
                                isSelected = backStackEntry.value?.destination?.route?.contains(item.route) ?: false,
                                icon =  { color ->
                                    item.icon(color)
                                },
                                text = stringResource(id = item.nameResourceId),
                                onClick = {
                                    navigateToTab(
                                        navController = navController,
                                        route = item.route
                                    )
                                }
                            )
                        }
                    }
                }
        }
    ) {
        content(
            navController
        ) { isShow ->
            showBottomBar.value = isShow
        }
    }
}

fun navigateToTab(
    navController: NavHostController,
    route: String,
    needToRestoreState: Boolean = true,
    needToSaveState: Boolean = true
) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = needToSaveState
        }

        launchSingleTop = true
        restoreState = needToRestoreState
    }
}