package com.anz.usersdirectoryapp.data.repository

import com.anz.usersdirectoryapp.data.model.User
import com.anz.usersdirectoryapp.data.remote.UserApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository responsible for retrieving the detailed user information.
 *
 * @param apiService The service responsible for making API requests.
 */
class UserDetailRepository @Inject constructor(
    val apiService: UserApiService
) {

    /**
     * Retrieves the detailed user information by their ID.
     *
     * @param id The ID of the user.
     */
    fun getUserDetailById(id: Int): Flow<Result<User>> = flow {
        val result = try {
            val response = apiService.getUserDetailById(id)
            Result.success(response)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
        emit(result)
    }
}