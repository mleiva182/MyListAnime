package com.mleiva.mylistanime

import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.mleiva.myanimelist.sampleAnime
import com.mleiva.mylistanime.ui.common.LOADING_PROGRESS_INDICATOR_TAG
import com.mleiva.mylistanime.ui.screens.detail.InfoAnimeScreen
import com.mleiva.mylistanime.ui.common.Result
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime
 * Creted by: Marcelo Leiva on 05-08-2024 at 12:51
 ***/
class InfoAnimeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule) {
        setContent {
            InfoAnimeScreen(
                state = Result.Loading,
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithTag(LOADING_PROGRESS_INDICATOR_TAG).assertExists()
    }

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {
            InfoAnimeScreen(
                state = Result.Error(RuntimeException("An error occurred")),
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithText("An error occurred").assertExists()
    }

    @Test
    fun whenSuccessState_animeIsShown(): Unit = with(composeTestRule) {
        setContent {
            InfoAnimeScreen(
                state = Result.Success(sampleAnime(2)),
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithText("Anime 2").assertExists()
    }

    @Test
    fun whenFavoriteClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {
            InfoAnimeScreen(
                state = Result.Success(sampleAnime(2)),
                onBack = {},
                onFavoriteClicked = { clicked = true }
            )
        }

        onNodeWithContentDescription(getStringResource(R.string.favorite)).performClick()
        assertTrue(clicked)
    }

    @Test
    fun whenBackClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {
            InfoAnimeScreen(
                state = Result.Success(sampleAnime(2)),
                onBack = { clicked = true },
                onFavoriteClicked = {}
            )
        }

        onNodeWithContentDescription(getStringResource(R.string.back)).performClick()
        assertTrue(clicked)
    }

    private fun getStringResource(@StringRes id: Int): String {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext
        return ctx.getString(id)
    }
}