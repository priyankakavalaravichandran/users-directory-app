package com.anz.usersdirectoryapp.domain.uimodel

/**
 * Represents a user in the directory system
 *
 * @property id The unique identifier for the user.
 * @property name The name of the user.
 * @property company The company the user associated with.
 * @property email The email address of the user
 * @property address The address of the user
 * @property phone The phone number of the user
 * @property photo The photo of the user
 */
data class UserUiModel(
    val id: Int,
    val name: String,
    val company: String,
    val userName: String,
    val email: String,
    val address: String,
    val phone: String,
    val photo: String
)