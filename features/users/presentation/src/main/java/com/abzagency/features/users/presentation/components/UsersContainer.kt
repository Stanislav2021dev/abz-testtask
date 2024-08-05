package com.abzagency.features.users.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.abzagency.core.designsystem.resources.backgroundPrimary
import com.abzagency.core.designsystem.resources.secondary
import com.abzagency.core.designsystem.ui.paging.isScrolledToEnd
import com.abzagency.features.users.models.presentation.UserPresentationModel

@Composable
fun UsersContainer(
    modifier: Modifier = Modifier,
    users: LazyPagingItems<UserPresentationModel>,
) {
    val isLoading by remember(users) {
        derivedStateOf { users.loadState.refresh is LoadState.Loading }
    }
    val isAppendLoading by remember(users) {
        derivedStateOf { users.loadState.append is LoadState.Loading }
    }
    val isAppendError by remember(users) {
        derivedStateOf { users.loadState.append is LoadState.Error }
    }

    val lazyListState = rememberLazyListState()
    val isScrolledToEnd by lazyListState.isScrolledToEnd()

    LazyColumn(
        modifier = modifier.padding(horizontal = Dimens.spacingNormal),
        verticalArrangement = Arrangement.spacedBy(Dimens.spacingBig),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            bottom = Dimens.bottomBarHeight + Dimens.spacingBig,
            top = Dimens.spacingBig
        ),
        state = if (isLoading) rememberLazyListState() else lazyListState
    ) {
        if (isLoading) {
            items(6) {
                UserItemShimmers(modifier = Modifier.fillMaxWidth())
            }
        } else {
            if (users.itemSnapshotList.items.isNotEmpty()) {
                items(
                    count = users.itemCount,
                    key = users.itemKey { user -> user.id }) { index ->
                    users[index]?.let { user ->
                        UserItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Colors.backgroundPrimary()),
                            user = user
                        )
                    }
                }

                if (isAppendLoading) {
                    item {
                        CircularProgressIndicator(color = Colors.secondary())
                    }
                }
            } else {
                item {
                    UsersPlaceholder()
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
        if (isScrolledToEnd && isAppendError && lazyListState.isScrollInProgress) {
            users.retry()
        }
    }
}