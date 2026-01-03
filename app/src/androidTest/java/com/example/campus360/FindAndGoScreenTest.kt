package com.example.campus360

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.campus360.ui.FindAndGoScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FindAndGoScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()  // Changed from createAndroidComposeRule

    @Test
    fun findAndGoScreen_displaysSearchField_andAcceptsInput() {
        // Get the application context
        val context = ApplicationProvider.getApplicationContext<Context>()

        lateinit var navController: TestNavHostController

        composeTestRule.setContent {
            // Initialize the nav controller inside setContent
            navController = TestNavHostController(context)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            FindAndGoScreen(
                nav = navController,
                context = context
            )
        }

        composeTestRule
            .onNodeWithText("Find & Go")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("searchField")
            .performTextInput("G210")

        // Verify typed text is shown
        composeTestRule
            .onNodeWithTag("searchField")
            .assertTextContains("G210")
    }
}