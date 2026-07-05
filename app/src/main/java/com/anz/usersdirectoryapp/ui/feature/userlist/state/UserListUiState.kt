package com.anz.usersdirectoryapp.ui.feature.userlist.state

import com.anz.usersdirectoryapp.domain.uimodel.UserUiModel

/**
 * Represents the various states of the User List screen.
 */
sealed class UserListUiState {

    /**
     * Represents Loading State of the Screen
     */
    data object Loading : UserListUiState()

    /**
     * Represents the error state of the user list.
     *
     * @property message The error message to display.
     */
    data class Error(val message: String): UserListUiState()

    /**
     * Represents the success state of the user list.
     *
     * @property user The list of user UI models to display.
     */
    data class Success(val user: List<UserUiModel>): UserListUiState()
}