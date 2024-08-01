package com.abzagency.features.users.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.abzagency.core.designsystem.resources.Colors
import com.abzagency.core.designsystem.resources.Dimens
import com.abzagency.core.designsystem.resources.secondary
import com.abzagency.core.designsystem.ui.isScrolledToEnd

@Composable
fun UsersContainer(
    modifier: Modifier = Modifier,
    users: LazyPagingItems<String>,
) {
    val isLoadingError by remember(users) {
        derivedStateOf { users.loadState.refresh is LoadState.Error }
    }
    val isLoading by remember(users) {
        derivedStateOf { users.loadState.refresh is LoadState.Loading }
    }
    val isAppendLoading by remember(users) {
        derivedStateOf { users.loadState.append is LoadState.Loading }
    }
    val isAppendError by remember(users) {
        derivedStateOf { users.loadState.append is LoadState.Error }
    }

    when {
        isLoadingError -> {
           // TODO add error container
        }

        else -> {
            val lazyListState = rememberLazyListState()
            val isScrolledToEnd by lazyListState.isScrolledToEnd()

            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(Dimens.spacingNormal),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(
                    bottom = Dimens.spacingNormal
                ),
                state = if (isLoading) rememberLazyListState() else lazyListState
            ) {
                if (isLoading) {
                    items(4) {
                        // TODO add shimmers
                    }
                } else {
                    if (users.itemSnapshotList.items.isNotEmpty()) {
                        items(
                            count = users.itemCount,
                            key = users.itemKey { user -> user }) { index ->
                            users[index]?.let { habit ->

                            }
                        }

                        if (isAppendLoading) {
                            item {
                                CircularProgressIndicator(color = Colors.secondary())
                            }
                        }
                    } else {
                        item {

                        }
                    }
                }
            }

            // Retry on error, if user scroll to end
            LaunchedEffect(
                isScrolledToEnd,
                isAppendError,
                lazyListState.isScrollInProgress
            ) {
                if (isScrolledToEnd && isAppendError && lazyListState.isScrollInProgress) users.retry()
            }
        }
    }
}