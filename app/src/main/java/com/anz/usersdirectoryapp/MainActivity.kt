package com.anz.usersdirectoryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.anz.usersdirectoryapp.navigation.AppNavHost
import com.anz.usersdirectoryapp.ui.theme.UsersDirectoryAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity of the application, serves as the entry point for the user interface.
 * This activity uses Jetpack Compose for rendering the UI and is injected with Hilt.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsersDirectoryAppTheme {
                AppNavHost()
            }
        }
    }
}