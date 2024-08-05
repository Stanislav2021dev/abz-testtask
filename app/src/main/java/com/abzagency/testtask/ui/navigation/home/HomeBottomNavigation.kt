package com.abzagency.testtask.com.abzagency.testtask.ui.navigation.home

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.testtask.R as StringResources
import com.abzagency.testtask.com.abzagency.testtask.ui.navigation.home.signup.signUpTabGraph
import com.abzagency.testtask.com.abzagency.testtask.ui.navigation.home.users.usersTabGraph
import com.abzagency.testtask.ui.navigation.HomeDestination
import com.abzagency.testtask.ui.navigation.navhost.BaseNavHost
import com.abzagency.testtask.ui.route.bottomnavigation.BottomBarScaffold
import com.abzagency.testtask.ui.route.bottomnavigation.BottomNavItem
import com.abzagency.core.designsystem.R
import com.abzagency.testtask.ui.route.bottomnavigation.navigateToTab

fun NavGraphBuilder.homeGraph(rootRoute: String) {
    composable(rootRoute) {
        BottomBarScaffold(
            bottomNavItems = bottomNavItems
        ) { navController, showBottomBar ->
            BottomNavigationNavHost(
                navController = navController,
                showBottomBar = showBottomBar
            )
        }
    }
}

@Composable
fun BottomNavigationNavHost(
    navController: NavHostController,
    showBottomBar: (isShow: Boolean) -> Unit
) {
    BaseNavHost(
        navController = navController,
        startDestination = HomeDestination.SIGN_UP.route
    ) {
        signUpTabGraph(
            rootRoute = HomeDestination.SIGN_UP.route,
            showBottomBar = showBottomBar,
            goToUsers = {
                navigateToTab(
                    navController = navController,
                    route = HomeDestination.USERS.route
                )
            }
        )

        usersTabGraph(
            rootRoute = HomeDestination.USERS.route,
            showBottomBar = showBottomBar
        )
    }
}

val bottomNavItems = listOf(
    BottomNavItem(
        nameResourceId = StringResources.string.users_tab_title,
        route = HomeDestination.USERS.route,
        icon = { tint ->
            Icon(
                modifier = Modifier.size(Dimens.iconSizeNormal),
                painter = painterResource(id = R.drawable.ic_people),
                tint = tint,
                contentDescription = null
            )
        }
    ),
    BottomNavItem(
        nameResourceId = StringResources.string.sign_up_tab_title,
        route = HomeDestination.SIGN_UP.route,
        icon = { tint ->
            Icon(
                modifier = Modifier.size(Dimens.iconSizeNormal),
                painter = painterResource(id = R.drawable.ic_person_add),
                tint = tint,
                contentDescription = null
            )
        }
    )
)