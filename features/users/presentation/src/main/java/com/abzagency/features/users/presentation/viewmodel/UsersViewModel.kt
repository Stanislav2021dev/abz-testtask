package com.abzagency.features.users.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.abzagency.features.users.domain.usecases.GetUsersFromRemoteUseCase
import com.abzagency.features.users.models.presentation.UserPresentationModel
import com.abzagency.features.users.models.presentation.toPresentationModel
import com.nanohabits.core.common.dispatchers.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersFromRemoteUseCase: GetUsersFromRemoteUseCase,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        UsersViewModelState(
            users = flowOf()
        )
    )
    val uiState: StateFlow<UsersViewModelState> = _uiState

    init {
        getUsersFromRemote()
    }

    private fun getUsersFromRemote() {
        viewModelScope.launch(coroutineDispatcher) {
            // Delay for shimmers
            delay(1000)

            _uiState.update { uiState ->
                uiState.copy(
                    users = getUsersFromRemoteUseCase().map { pagingData ->
                        pagingData.map { it.toPresentationModel() }
                    }
                )
            }
        }
    }
}

data class UsersViewModelState(
    val users: Flow<PagingData<UserPresentationModel>>
)


