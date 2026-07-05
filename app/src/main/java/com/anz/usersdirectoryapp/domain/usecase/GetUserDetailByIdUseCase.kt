package com.anz.usersdirectoryapp.domain.usecase

import com.anz.usersdirectoryapp.data.model.User
import com.anz.usersdirectoryapp.data.repository.UserDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to retrieve detailed information for a specific user.
 *
 * @param userDetailRepository The repository to fetch user detail data from.
 */
class GetUserDetailByIdUseCase @Inject constructor(
    val userDetailRepository: UserDetailRepository
) {
    /**
     * Executes the use case to fetch user details.
     *
     * @param id The unique identifier of the user to fetch.
     * @return A [Flow] emitting a [Result] containing the [User] details or an error.
     */
    fun invoke(id: Int): Flow<Result<User>> {
       return userDetailRepository.getUserDetailById(id)
    }
}