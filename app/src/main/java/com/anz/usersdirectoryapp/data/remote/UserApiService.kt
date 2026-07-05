package com.anz.usersdirectoryapp.data.remote

import com.anz.usersdirectoryapp.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit service interface for users related API endpoints.
 */
interface UserApiService {

   /**
    * Fetches all users from the remote directory.
    *
    * @return A list of [User] objects.
    */
   @GET("users")
   suspend fun getUsers(): List<User>

   /**
    * Fetches detailed information for a specific user by their ID.
    *
    * @param id The unique identifier of the user.
    * @return A [User] object containing the detailed information.
    */
   @GET("users/{id}")
   suspend fun getUserDetailById(@Path("id") id: Int): User
}