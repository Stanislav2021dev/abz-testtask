package com.abzagency.features.users.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.backgroundPrimary
import com.abzagency.core.designsystem.ui.common.Header
import com.abzagency.features.users.presentation.components.UsersContainer
import com.abzagency.features.users.presentation.viewmodel.UsersViewModel
import com.abzagency.features.users.presentation.viewmodel.UsersViewModelState
import kotlinx.coroutines.flow.flowOf
import com.abzagency.features.users.presentation.R

@Composable
internal fun UsersRoute(
    viewModel: UsersViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    UsersScreen(
        uiState = uiState
    )
}

@Composable
internal fun UsersScreen(
    uiState: UsersViewModelState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Colors.backgroundPrimary())
    ) {
        Header(title = stringResource(id = R.string.header_title))

        UsersContainer(users = uiState.users.collectAsLazyPagingItems())
    }
}

@Composable
@Preview
private fun UsersScreenPreview() {
    UsersScreen(
        uiState = UsersViewModelState(
            users = flowOf()
        )
    )
}