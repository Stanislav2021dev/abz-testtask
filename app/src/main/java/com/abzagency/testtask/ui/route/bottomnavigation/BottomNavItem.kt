package com.abzagency.testtask.ui.route.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class BottomNavItem(
    val nameResourceId: Int,
    val route: String,
    val icon: @Composable (tint: Color) -> Unit
)