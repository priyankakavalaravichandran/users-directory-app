package com.anz.usersdirectoryapp.navigation

/**
 * Destination Sealed Class represents navigations screens in the app
 *
 * @param route Represents Destination Path
 */
sealed class Destination(val route: String) {

    data object UserList : Destination("user_list")

    data object UserDetail : Destination("user_detail/{userId}") {
        const val ARG_USER_ID = "userId"
        fun createRoute(userId: Int) = "user_detail/$userId"
    }
}



