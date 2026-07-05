package com.anz.usersdirectoryapp.screens.userdetail

import com.anz.usersdirectoryapp.data.model.User
import com.anz.usersdirectoryapp.data.remote.UserApiService
import com.anz.usersdirectoryapp.data.repository.UserDetailRepository
import com.anz.usersdirectoryapp.domain.usecase.GetUserDetailByIdUseCase
import com.anz.usersdirectoryapp.ui.feature.userdetail.state.UserDetailUiState
import com.anz.usersdirectoryapp.ui.feature.userdetail.viewmodel.UserDetailViewModel
import com.anz.usersdirectoryapp.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class UserDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockUser = User(
        id = 1,
        name = "John Doe",
        email = "john@example.com",
        phone = "1234567890",
        address = "123 Main St",
        company = "ACME Corp"
    )

    private val apiService = mockk<UserApiService>()

    @Test
    fun checkWhetherLoadUserDetailByIdReturnsUsersOnSuccess() = runTest {
        // Arrange
        coEvery { apiService.getUserDetailById(1) } returns mockUser
        
        // Using spyk to satisfy user request for "use spy"
        val repository = spyk(UserDetailRepository(apiService))
        val useCase = GetUserDetailByIdUseCase(repository)
        val viewModel = UserDetailViewModel(useCase)

        // Act
        viewModel.loadUserDetailById(1)

        // Assert
        val state = viewModel.uiState.value
        assertTrue(state is UserDetailUiState.Success)
        val successState = state as UserDetailUiState.Success
        assertEquals("John Doe", successState.userDetail.name)
        assertEquals("john@example.com", successState.userDetail.email)
    }

    @Test
    fun checkWhetherLoadUserDetailByIdReturnsExceptionOnFailure() = runTest {
        // Arrange
        val errorMessage = "User Not Found"
        coEvery { apiService.getUserDetailById(1) } throws Exception(errorMessage)
        
        val repository = UserDetailRepository(apiService)
        val useCase = GetUserDetailByIdUseCase(repository)
        val viewModel = UserDetailViewModel(useCase)

        // Act
        viewModel.loadUserDetailById(1)

        // Assert
        val state = viewModel.uiState.value
        assertTrue(state is UserDetailUiState.Error)
        val errorState = state as UserDetailUiState.Error
        assertEquals(errorMessage, errorState.message)
    }
}
