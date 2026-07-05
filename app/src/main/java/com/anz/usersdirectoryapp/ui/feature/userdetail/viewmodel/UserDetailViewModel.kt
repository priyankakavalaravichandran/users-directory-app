package com.anz.usersdirectoryapp.ui.feature.userdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anz.usersdirectoryapp.domain.mapper.toUserUiModel
import com.anz.usersdirectoryapp.domain.usecase.GetUserDetailByIdUseCase
import com.anz.usersdirectoryapp.ui.feature.userdetail.state.UserDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the User Detail screen.
 * Handles fetching and exposing the state of a single user's details.
 *
 * @property getUserDetailByIdUseCase The use case to fetch user details by ID.
 */
@HiltViewModel
class UserDetailViewModel @Inject constructor(
    val getUserDetailByIdUseCase: GetUserDetailByIdUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UserDetailUiState> =
        MutableStateFlow(UserDetailUiState.Loading)
    val uiState: StateFlow<UserDetailUiState> = _uiState

    /**
     * Triggers the loading of user details for the specified user ID.
     *
     * @param id The unique identifier of the user to load.
     */
    fun loadUserDetailById(id: Int) {
        viewModelScope.launch {
            _uiState.update { UserDetailUiState.Loading }
            getUserDetailByIdUseCase.invoke(id).collect { result ->
                result.fold(
                    onSuccess = { user ->
                        _uiState.update { UserDetailUiState.Success(user.toUserUiModel()) }
                    },
                    onFailure = { error ->
                        _uiState.update { UserDetailUiState.Error(error.message ?: NO_DATA_FOUND) }
                    }
                )
            }
        }
    }

    companion object {
        private const val NO_DATA_FOUND = "No Data Found"
    }
}