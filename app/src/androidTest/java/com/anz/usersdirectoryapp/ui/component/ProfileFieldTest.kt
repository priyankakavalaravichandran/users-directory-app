package com.anz.usersdirectoryapp.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.anz.usersdirectoryapp.ui.theme.UsersDirectoryAppTheme
import org.junit.Rule
import org.junit.Test

class ProfileFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun profileField_displaysLabelAndValue() {
        val testLabel = "Email"
        val testValue = "test@example.com"

        composeTestRule.setContent {
            UsersDirectoryAppTheme {
                ProfileField(
                    label = testLabel,
                    value = testValue
                )
            }
        }

        composeTestRule.onNodeWithText(testLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText(testValue).assertIsDisplayed()
    }
}
