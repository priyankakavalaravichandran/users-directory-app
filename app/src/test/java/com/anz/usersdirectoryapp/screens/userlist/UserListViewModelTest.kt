package com.anz.usersdirectoryapp.screens.userlist

import com.anz.usersdirectoryapp.data.model.User
import com.anz.usersdirectoryapp.data.remote.UserApiService
import com.anz.usersdirectoryapp.data.repository.UserListRepository
import com.anz.usersdirectoryapp.domain.usecase.GetUserListUseCase
import com.anz.usersdirectoryapp.ui.feature.userlist.state.UserListUiState
import com.anz.usersdirectoryapp.ui.feature.userlist.viewmodel.UserListViewModel
import com.anz.usersdirectoryapp.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class UserListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockUserList = listOf(
        User(id = 1, name = "John Doe"),
        User(id = 2, name = "Jane Doe")
    )

    private val apiService = mockk<UserApiService>()

    @Test
    fun checkWhetherLoadUsersReturnsUsersListOnSuccess() = runTest {
        // Arrange
        coEvery { apiService.getUsers() } returns mockUserList
        val repository = UserListRepository(apiService)
        val getUserListUseCase = GetUserListUseCase(repository)
        val viewModel = UserListViewModel(getUserListUseCase)

        // Act - loadUsers is called in init

        // Assert
        val state = viewModel.uiState.value
        assertTrue(state is UserListUiState.Success)
        val successState = state as UserListUiState.Success
        assertEquals(2, successState.user.size)
        assertEquals("John Doe", successState.user[0].name)
    }

    @Test
    fun checkWhetherLoadUsersReturnsExceptionOnFailure() = runTest {
        // Arrange
        val errorMessage = "Network Error"
        coEvery { apiService.getUsers() } throws Exception(errorMessage)
        val repository = UserListRepository(apiService)
        val getUserListUseCase = GetUserListUseCase(repository)
        val viewModel = UserListViewModel(getUserListUseCase)

        // Act - loadUsers is called in init

        // Assert
        val state = viewModel.uiState.value
        assertTrue(state is UserListUiState.Error)
        val errorState = state as UserListUiState.Error
        assertEquals(errorMessage, errorState.message)
    }

    @Test
    fun checkWhetherRetryTriggersApiCallAgainOnFailure() = runTest {
        coEvery { apiService.getUsers() } returns emptyList()
        val repository = UserListRepository(apiService)
        val getUserListUseCase = GetUserListUseCase(repository)
        val viewModel = UserListViewModel(getUserListUseCase)
        viewModel.retry()
        coVerify(exactly = 2) { apiService.getUsers() }
    }
}
