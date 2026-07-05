package com.anz.usersdirectoryapp.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.anz.usersdirectoryapp.ui.theme.UsersDirectoryAppTheme
import org.junit.Rule
import org.junit.Test

class UserInfoRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun userInfoRow_displaysText() {
        val testText = "Test User Name"

        composeTestRule.setContent {
            UsersDirectoryAppTheme {
                UserInfoRow(
                    icon = Icons.Default.Person,
                    text = testText
                )
            }
        }

        composeTestRule.onNodeWithText(testText).assertIsDisplayed()
    }
}
