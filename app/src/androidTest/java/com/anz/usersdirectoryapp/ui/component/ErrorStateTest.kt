package com.anz.usersdirectoryapp.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.anz.usersdirectoryapp.ui.theme.UsersDirectoryAppTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ErrorStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorState_displaysMessage() {
        val errorMessage = "Something went wrong"

        composeTestRule.setContent {
            UsersDirectoryAppTheme {
                ErrorContent (
                    message = errorMessage,
                    onRetry = {}
                )
            }
        }

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun errorState_retryButtonClick_triggersCallback() {
        var retryClicked = false

        composeTestRule.setContent {
            UsersDirectoryAppTheme {
                ErrorContent(
                    message = "Error",
                    onRetry = { retryClicked = true }
                )
            }
        }

        composeTestRule.onNodeWithText("Retry").performClick()

        assertTrue(retryClicked)
    }
}
