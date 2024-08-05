package com.abzagency.testtask.com.abzagency.testtask.ui.navigation.home.signup

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abzagency.features.signup.presentation.navigation.signUpGraph
import com.abzagency.testtask.ui.navigation.HomeDestination
import com.abzagency.testtask.ui.navigation.navhost.BaseNavHost

fun NavGraphBuilder.signUpTabGraph(
    rootRoute: String,
    showBottomBar: (isShow: Boolean) -> Unit,
    goToUsers: () -> Unit
) {
    composable(rootRoute) {
        val navController = rememberNavController()
        val currentEntry by navController.currentBackStackEntryAsState()
        val startDestination = HomeDestination.SIGN_UP.route

        LaunchedEffect(currentEntry) {
            currentEntry?.destination?.route?.let { route ->
                showBottomBar(route == startDestination)
            }
        }

        BaseNavHost(
            navController,
            startDestination = startDestination
        ) {
            signUpGraph(
                rootRoute = HomeDestination.SIGN_UP.route,
                goToUsers = goToUsers
            )
        }
    }
}