package com.anz.usersdirectoryapp.domain.mapper

import com.anz.usersdirectoryapp.data.model.User
import com.anz.usersdirectoryapp.domain.uimodel.UserUiModel

/**
 * Maps a [User] data model to a [UserUiModel] for use in the UI layer.
 *
 * @return A [UserUiModel] containing formatted user data.
 */
fun User.toUserUiModel(): UserUiModel {
    return UserUiModel(
       id = this.id ?: 0,
       name = this.name?.split(" ")?.take(2)?.joinToString(" ").orEmpty(),
       company = this.company.orEmpty(),
       userName = this.username.orEmpty(),
       email = this.email.orEmpty(),
       phone = this.phone.orEmpty(),
       photo = this.photo.orEmpty(),
       address = this.address.orEmpty()
    )
}

/**
 * Maps a list of [User] data models to a list of [UserUiModel]s.
 *
 * @return A list of [UserUiModel]s.
 */
fun List<User>.toUiModelList() : List<UserUiModel> = map { it.toUserUiModel() }