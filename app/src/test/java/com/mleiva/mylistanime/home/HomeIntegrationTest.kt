package com.mleiva.mylistanime.home

import app.cash.turbine.test
import com.mleiva.myanimelist.data.buildAnimesRepositoryWith
import com.mleiva.myanimelist.sampleAnimes
import com.mleiva.myanimelist.testrules.CoroutinesTestRule
import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.ui.screens.home.HomeViewModel
import com.mleiva.mylistanime.usecases.FetchAnimesUseCase
import com.mleiva.mylistanime.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime
 * Creted by: Marcelo Leiva on 04-08-2024 at 11:14
 ***/
class HomeIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Data is loaded from server when local data is empty`() = runTest {
        val remoteData = sampleAnimes(1, 2, 3)
        val vm = buildHomeViewModelWith(emptyList(), remoteData)

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<Anime>()), awaitItem())
            assertEquals(Result.Success(remoteData), awaitItem())
        }

    }

    @Test
    fun `data is loaded from local when available`() = runTest {

        val localData = sampleAnimes(1, 2, 3)
        val vm = buildHomeViewModelWith(localData)

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localData), awaitItem())
        }

    }

    private fun buildHomeViewModelWith(
        localData: List<Anime> = emptyList(),
        remoteData: List<Anime> = emptyList()
    ): HomeViewModel {
        val fetchAnimesUseCase = FetchAnimesUseCase(buildAnimesRepositoryWith(localData, remoteData))
        return HomeViewModel(fetchAnimesUseCase)
    }
}