package com.abzagency.features.users.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.backgroundPrimary
import com.abzagency.features.users.presentation.viewmodel.UsersViewModel

@Composable
internal fun UsersRoute(
    viewModel: UsersViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val users = uiState.users.collectAsLazyPagingItems().itemSnapshotList.items
    Log.d("TESTT", "UsersRoute: $users")
    UsersScreen()
}

@Composable
internal fun UsersScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.backgroundPrimary())
    ) {
        Text(text = "Users")
    }
}

@Composable
@Preview
private fun UsersScreenPreview() {
    UsersScreen()
}