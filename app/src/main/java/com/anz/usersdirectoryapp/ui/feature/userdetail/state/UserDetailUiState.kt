package com.anz.usersdirectoryapp.ui.feature.userdetail.state

import com.anz.usersdirectoryapp.domain.uimodel.UserUiModel

/**
 * Represents the various states of the User Detail screen.
 */
sealed class UserDetailUiState {

    /**
     * Represents Loading State of the User Detail Screen
     */
    data object Loading : UserDetailUiState()

    /**
     * Represents the error state of the User Detail Screen.
     *
     * @property message The error message to display.
     */
    data class Error(val message: String) : UserDetailUiState()

    /**
     * Represents the success state of the User Detail Screen.
     *
     * @property userDetail The UI model containing the user's detailed information.
     */
    data class Success(val userDetail: UserUiModel) : UserDetailUiState()
}