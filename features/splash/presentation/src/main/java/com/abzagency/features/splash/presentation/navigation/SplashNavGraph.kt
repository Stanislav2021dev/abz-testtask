package com.abzagency.features.splash.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abzagency.features.splash.presentation.screen.SplashRoute

fun NavGraphBuilder.splashGraph(rootRoute: String) {
    composable(route = rootRoute) {
        SplashRoute()
    }
}