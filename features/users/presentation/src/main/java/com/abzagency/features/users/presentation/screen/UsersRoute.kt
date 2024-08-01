package com.abzagency.features.users.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun UsersRoute() {
    UsersScreen()
}

@Composable
internal fun UsersScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Users")
    }
}

@Composable
private fun UsersScreenPreview() {
    UsersScreen()
}