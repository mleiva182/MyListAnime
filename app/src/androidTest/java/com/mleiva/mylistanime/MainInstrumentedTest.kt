package com.mleiva.mylistanime

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.mleiva.mylistanime.data.server.MockWebServerRule
import com.mleiva.mylistanime.data.server.fromJson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime
 * Creted by: Marcelo Leiva on 05-08-2024 at 18:34
 ***/
@HiltAndroidTest
class MainInstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val androidComposeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("list_animes.json")
        )

        hiltRule.inject()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun click_a_anime_navigates_to_detail(): Unit = with(androidComposeRule) {
        waitUntilAtLeastOneExists(hasParent(hasScrollToIndexAction()))
        onAllNodes(hasParent(hasScrollToIndexAction()))[2].performClick()

        onNodeWithText("Trigun").assertIsDisplayed()
    }
}