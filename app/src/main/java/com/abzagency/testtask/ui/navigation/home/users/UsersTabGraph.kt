package com.abzagency.testtask.com.abzagency.testtask.ui.navigation.home.users

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abzagency.features.users.presentation.navigation.usersGraph
import com.abzagency.testtask.ui.navigation.HomeDestination
import com.abzagency.testtask.ui.navigation.navhost.BaseNavHost

fun NavGraphBuilder.usersTabGraph(
    rootRoute: String,
    showBottomBar: (isShow: Boolean) -> Unit
) {
    composable(rootRoute) {
        val navController = rememberNavController()
        val currentEntry by navController.currentBackStackEntryAsState()
        val startDestination = HomeDestination.USERS.route

        LaunchedEffect(currentEntry) {
            currentEntry?.destination?.route?.let { route ->
                showBottomBar(route == startDestination)
            }
        }

        BaseNavHost(
            navController,
            startDestination = startDestination
        ) {
            usersGraph(
                rootRoute = HomeDestination.USERS.route
            )
        }
    }
}