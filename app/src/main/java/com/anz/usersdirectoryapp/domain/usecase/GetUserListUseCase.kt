package com.anz.usersdirectoryapp.domain.usecase

import com.anz.usersdirectoryapp.data.model.User
import com.anz.usersdirectoryapp.data.repository.UserListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to retrieve the list of users from the repository.
 *
 * @property repository The repository used to fetch user data.
 */
class GetUserListUseCase @Inject constructor(
    val repository: UserListRepository
) {

    /**
     * Executes the use case to fetch the list of users.
     *
     * @return A [Flow] emitting a [Result] containing the list of [User]s or an error.
     */
     fun invoke(): Flow<Result<List<User>>> {
        return repository.getUsers()
    }
}
