package com.mleiva.mylistanime.infoanime

import app.cash.turbine.test
import com.mleiva.myanimelist.sampleAnime
import com.mleiva.myanimelist.testrules.CoroutinesTestRule
import com.mleiva.mylistanime.ui.common.Result
import com.mleiva.mylistanime.ui.screens.detail.InfoAnimeViewModel
import com.mleiva.mylistanime.usecases.ChangeFavoriteUseCase
import com.mleiva.mylistanime.usecases.FindAnimeByIdUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime
 * Creted by: Marcelo Leiva on 02-08-2024 at 19:21
 ***/
@RunWith(MockitoJUnitRunner::class)
class InfoAnimeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var findAnimeByIdUseCase: FindAnimeByIdUseCase

    @Mock
    lateinit var changeFavoriteUseCase: ChangeFavoriteUseCase

    private lateinit var vm: InfoAnimeViewModel

    private val anime = sampleAnime(1)

    @Before
    fun setUp() {
        whenever(findAnimeByIdUseCase(1)).thenReturn(flowOf(anime))
        vm = InfoAnimeViewModel(1, findAnimeByIdUseCase, changeFavoriteUseCase)
    }


    @Test
    fun `UI is updated with the anime on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(anime), awaitItem())
        }
    }

    @Test
    fun `Favorite action calls the corresponding use case`() = runTest(coroutinesTestRule.testDispatcher) {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(anime), awaitItem())

            vm.onFavoriteClicked()
            runCurrent()

            verify(changeFavoriteUseCase).invoke(any())
        }
    }


}