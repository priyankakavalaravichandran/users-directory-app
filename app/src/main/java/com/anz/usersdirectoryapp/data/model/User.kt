package com.anz.usersdirectoryapp.data.model

/**
 * Represents a user details in the user directory
 *
 * @property id The unique identifier for the user.
 * @property name The name of the user.
 * @property company The company the user associated with.
 * @property username The unique username for the user
 * @property email The email address of the user
 * @property address The address of the user
 * @property zip The zip code of the user
 * @property state The state of the user
 * @property country The country of the user
 * @property phone The phone number of the user
 * @property photo The photo of the user
 */
data class User(
    val id: Int? = null,
    val name: String? = null,
    val company: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: String? = null,
    val zip: String? = null,
    val state: String? = null,
    val country: String? = null,
    val phone: String? = null,
    val photo: String? = null
)