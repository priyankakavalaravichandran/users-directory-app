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

class UserDetailRepositoryTest {

    private val apiService = mockk<UserApiService>()
    private val repository = UserDetailRepository(apiService)

    @Test
    fun checkWhetherGetUserDetailByIdReturnsSuccess() = runTest {
        val mockUser = User(id = 1, name = "User 1")
        coEvery { apiService.getUserDetailById(1) } returns mockUser

        val result = repository.getUserDetailById(1).first()

        assertTrue(result.isSuccess)
        assertEquals(mockUser, result.getOrNull())
    }

    @Test
    fun checkWhetherGetUserDetailByIdReturnsError() = runTest {
        val exception = Exception("Not Found")
        coEvery { apiService.getUserDetailById(1) } throws exception

        val result = repository.getUserDetailById(1).first()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
