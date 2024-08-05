package com.abzagency.testtask.com.abzagency.testtask.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.abzagency.features.splash.presentation.navigation.splashGraph
import com.abzagency.testtask.com.abzagency.testtask.ui.navigation.home.homeGraph
import com.abzagency.testtask.ui.navigation.RootDestination
import com.abzagency.testtask.ui.navigation.navhost.BaseNavHost
import kotlinx.coroutines.delay

@Composable
fun MainRoute() {
    val navController = rememberNavController()

    MainScreen(
        navController = navController
    )
}

@Composable
fun MainScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        if (navController.currentBackStackEntry?.destination?.route == RootDestination.SPLASH.route) {
            delay(splashScreenDelay)

            navController.navigate(RootDestination.HOME.route) { popUpTo(RootDestination.ROOT.route) }
        }
    }

    BaseNavHost(
        navController = navController,
        startDestination = RootDestination.ROOT.route
    ) {
        navigation(
            route = RootDestination.ROOT.route,
            startDestination = RootDestination.SPLASH.route
        ) {
            splashGraph(
                rootRoute = RootDestination.SPLASH.route
            )

            homeGraph(
                rootRoute = RootDestination.HOME.route
            )
        }
    }
}

private const val splashScreenDelay = 1500L