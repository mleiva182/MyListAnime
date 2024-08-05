package com.mleiva.mylistanime

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.mleiva.myanimelist.sampleAnimes
import com.mleiva.mylistanime.ui.common.LOADING_PROGRESS_INDICATOR_TAG
import org.junit.Rule
import org.junit.Test
import com.mleiva.mylistanime.ui.common.Result
import com.mleiva.mylistanime.ui.screens.home.HomeScreen
import org.junit.Assert.assertEquals

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.home
 * Creted by: Marcelo Leiva on 05-08-2024 at 11:42
 ***/
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                state = Result.Loading,
                onAnimeClick = {}
            )
        }

        onNodeWithTag(LOADING_PROGRESS_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                state = Result.Error(RuntimeException("An error occurred")),
                onAnimeClick = {}
            )
        }

        onNodeWithText("An error occurred").assertExists()
    }

    @Test
    fun whenSuccessState_animesAreShown(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                state = Result.Success(sampleAnimes(1, 2, 3)),
                onAnimeClick = {}
            )
        }

        onNodeWithText("Anime 2").assertExists()
    }

    @Test
    fun whenAnimeClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clickedAnimeId = -1
        val animes = sampleAnimes(1, 2, 3)
        setContent {
            HomeScreen(
                state = Result.Success(animes),
                onAnimeClick = { clickedAnimeId = it.id }
            )
        }

        onNodeWithText("Anime 2").performClick()

        assertEquals(2, clickedAnimeId)
    }
}