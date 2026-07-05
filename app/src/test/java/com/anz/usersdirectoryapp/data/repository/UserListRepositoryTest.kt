package com.anz.usersdirectoryapp.data.repository

import com.anz.usersdirectoryapp.data.model.User
import com.anz.usersdirectoryapp.data.remote.UserApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UserListRepositoryTest {

    private val apiService = mockk<UserApiService>()
    private val repository = UserListRepository(apiService)

    @Test
    fun checkWhetherGetUserReturnsSuccess() = runTest {
        val mockUsers = listOf(User(id = 1, name = "User 1"))
        coEvery { apiService.getUsers() } returns mockUsers

        val result = repository.getUsers().first()

        assertTrue(result.isSuccess)
        assertEquals(mockUsers, result.getOrNull())
    }

    @Test
    fun checkWhetherGetUserReturnsError() = runTest {
        val exception = Exception("Network Error")
        coEvery { apiService.getUsers() } throws exception

        val result = repository.getUsers().first()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
