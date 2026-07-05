package com.anz.usersdirectoryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anz.usersdirectoryapp.ui.feature.userdetail.UserDetailScreen
import com.anz.usersdirectoryapp.ui.feature.userlist.UserListScreen

/**
 * Navigation host for the application, includes the NavHost and destinations Screen.
 *
 */
@Composable
fun AppNavHost(
    startDestination: String = Destination.UserList.route
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destination.UserList.route) {
            UserListScreen(
                onUserClick = { userId ->
                    navController.navigate(Destination.UserDetail.createRoute(userId))
                }
            )
        }
        composable(
            route = Destination.UserDetail.route,
            arguments = listOf(
                navArgument(Destination.UserDetail.ARG_USER_ID) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt(Destination.UserDetail.ARG_USER_ID) ?: 0
            UserDetailScreen(
                userId = userId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
