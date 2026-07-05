package com.anz.usersdirectoryapp.data.repository

import com.anz.usersdirectoryapp.data.model.User
import com.anz.usersdirectoryapp.data.remote.UserApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository responsible for fetching user directory list
 *
 * @param apiService The Service responsible for making an api call.
 */
class UserListRepository @Inject constructor(
     private val apiService: UserApiService
) {

    /**
     * Fetches the list of users from the remote data source.
     *
     * @return A [Flow] emitting a [Result] containing a list of [User]s or an error.
     */
    fun getUsers() : Flow<Result<List<User>>> = flow {
       val result = try {
           val users = apiService.getUsers()
            Result.success(users)
       } catch (ex: Exception) {
            Result.failure(ex)
       }
       emit(result)
    }
}