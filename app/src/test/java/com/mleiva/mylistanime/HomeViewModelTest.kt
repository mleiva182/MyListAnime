package com.mleiva.mylistanime

import app.cash.turbine.test
import com.mleiva.myanimelist.sampleAnimes
import com.mleiva.myanimelist.testrules.CoroutinesTestRule
import com.mleiva.mylistanime.ui.screens.home.HomeViewModel
import com.mleiva.mylistanime.usecases.FetchAnimesUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import com.mleiva.mylistanime.ui.common.Result

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime
 * Creted by: Marcelo Leiva on 02-08-2024 at 18:53
 ***/
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var fetchAnimesUseCase: FetchAnimesUseCase

    private lateinit var vm: HomeViewModel

    @Before
    fun setUp() {
        vm = HomeViewModel(fetchAnimesUseCase)
    }

    @Test
    fun `animes are not requested if UI is not ready`() = runTest {
        vm.state.first()
        runCurrent()

        verify(fetchAnimesUseCase, times(0)).invoke()
    }

    @Test
    fun `animes are requested if UI is ready`() = runTest {
        val animes = sampleAnimes(1,2,3)
        whenever(fetchAnimesUseCase()).thenReturn(flowOf(animes))

        vm.onUiReady()
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(animes), awaitItem())
        }
    }

    @Test
    fun `Error is display when request fail`() = runTest{
        val error = RuntimeException("KBoom!")
        whenever(fetchAnimesUseCase()).thenThrow(error)

        vm.onUiReady()
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            val exceptionMessage = (awaitItem() as Result.Error).exception.message
            assertEquals("KBoom!", exceptionMessage)
        }
    }
}