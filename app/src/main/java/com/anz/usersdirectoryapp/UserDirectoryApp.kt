package com.anz.usersdirectoryapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The main [Application] class for the User Directory App.
 * Initialized with Hilt for dependency injection.
 */
@HiltAndroidApp
class UserDirectoryApp : Application()