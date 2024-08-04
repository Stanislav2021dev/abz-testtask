package com.abzagency.features.signup.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abzagency.features.signup.presentation.screen.SignUpRoute

fun NavGraphBuilder.signUpGraph(rootRoute: String) {
    composable(route = rootRoute) {
        SignUpRoute(
            viewModel = hiltViewModel()
        )
    }
}