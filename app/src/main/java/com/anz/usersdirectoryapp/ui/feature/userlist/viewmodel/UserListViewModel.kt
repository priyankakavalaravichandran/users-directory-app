package com.anz.usersdirectoryapp.ui.feature.userlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anz.usersdirectoryapp.domain.mapper.toUiModelList
import com.anz.usersdirectoryapp.domain.usecase.GetUserListUseCase
import com.anz.usersdirectoryapp.ui.feature.userlist.state.UserListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the User List screen.
 * Responsible for fetching and managing the state of the list of users.
 *
 * @property getUserListUseCase The use case to fetch the list of users.
 */
@HiltViewModel
class UserListViewModel @Inject constructor(
    val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserListUiState>(UserListUiState.Loading)
    val uiState: StateFlow<UserListUiState> = _uiState

    init {
        loadUsers()
    }

    /**
     * Triggers the loading of users from the repository.
     */
    fun loadUsers() {
        viewModelScope.launch {
            _uiState.update { UserListUiState.Loading }
            getUserListUseCase.invoke().collect { result ->
                _uiState.update {
                    result.fold(
                        onSuccess = { user -> UserListUiState.Success(user.toUiModelList()) },
                        onFailure = { error ->
                            UserListUiState.Error(
                                error.message ?: NO_DATA_FOUND
                            )
                        }
                    )
                }
            }
        }
    }

    /**
     * Retries the loading of users if it previously failed.
     */
    fun retry() = loadUsers()

    companion object {
        private const val NO_DATA_FOUND = "No Data Found"
    }
}