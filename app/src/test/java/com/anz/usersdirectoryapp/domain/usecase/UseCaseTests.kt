package com.anz.usersdirectoryapp.domain.usecase

import com.anz.usersdirectoryapp.data.model.User
import com.anz.usersdirectoryapp.data.remote.UserApiService
import com.anz.usersdirectoryapp.data.repository.UserDetailRepository
import com.anz.usersdirectoryapp.data.repository.UserListRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UseCaseTests {

    private val apiService = mockk<UserApiService>()

    @Test
    fun checkWhetherGetUserListUseCaseReturnsDataFromRepository() = runTest {
        val mockUsers = listOf(User(id = 1, name = "Test User"))
        coEvery { apiService.getUsers() } returns mockUsers
        
        val repository = UserListRepository(apiService)
        val useCase = GetUserListUseCase(repository)

        val result = useCase.invoke().first()

        assertTrue(result.isSuccess)
        assertEquals(mockUsers, result.getOrNull())
    }

    @Test
    fun checkWhetherGetUserDetailByIdUseCaseReturnsDataFromRepositor() = runTest {
        val mockUser = User(id = 1, name = "Test User")
        coEvery { apiService.getUserDetailById(1) } returns mockUser
        
        val repository = UserDetailRepository(apiService)
        val useCase = GetUserDetailByIdUseCase(repository)

        val result = useCase.invoke(1).first()

        assertTrue(result.isSuccess)
        assertEquals(mockUser, result.getOrNull())
    }
}
