package com.mleiva.mylistanime.infoanime

import app.cash.turbine.test
import com.mleiva.myanimelist.data.buildAnimesRepositoryWith
import com.mleiva.myanimelist.sampleAnime
import com.mleiva.myanimelist.sampleAnimes
import com.mleiva.myanimelist.testrules.CoroutinesTestRule
import com.mleiva.mylistanime.ui.screens.detail.InfoAnimeViewModel
import com.mleiva.mylistanime.usecases.ChangeFavoriteUseCase
import com.mleiva.mylistanime.usecases.FindAnimeByIdUseCase
import com.mleiva.mylistanime.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime
 * Creted by: Marcelo Leiva on 04-08-2024 at 11:47
 ***/
class InfoAnimeIntegrationTest{

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: InfoAnimeViewModel

    @Before
    fun setUp() {
        val animesRepository = buildAnimesRepositoryWith( localData = sampleAnimes(1,2))
        vm = InfoAnimeViewModel(1, FindAnimeByIdUseCase(animesRepository), ChangeFavoriteUseCase(animesRepository))
    }

    @Test
    fun `UI is updated with the anime on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleAnime(1)), awaitItem())
        }
    }

    @Test
    fun `Favorite is updated in local data`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleAnime(1)), awaitItem())

            vm.onFavoriteClicked()
            runCurrent()
            assertEquals(Result.Success(sampleAnime(1).copy(favorite = true)), awaitItem())
        }

    }


}