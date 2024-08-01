package com.abzagency.core.designsystem.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Composable
fun LazyListState.isScrolledToEnd() = remember {
    derivedStateOf {
        layoutInfo.visibleItemsInfo.lastOrNull()?.let {
            // Check that this is last item of list
            it.index == layoutInfo.totalItemsCount - 1 &&
                // Check that end of item is higher than end of viewport
                it.size + it.offset + layoutInfo.beforeContentPadding <= layoutInfo.viewportEndOffset
        } == true
    }
}