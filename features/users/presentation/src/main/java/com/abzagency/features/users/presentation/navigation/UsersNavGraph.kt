package com.abzagency.features.users.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abzagency.features.users.presentation.screen.UsersRoute

fun NavGraphBuilder.usersGraph(rootRoute: String) {
    composable(route = rootRoute) {
        UsersRoute()
    }
}