package com.mleiva.mylistanime.usecases

import com.mleiva.myanimelist.sampleAnime
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.usecases
 * Creted by: Marcelo Leiva on 02-08-2024 at 15:15
 */
class FindAnimeByIdUseCaseTest{

    @Test
    fun `invoke calls repository to find anime by id`() {
        val animeFlow = flowOf(sampleAnime(1))
        val useCase = FindAnimeByIdUseCase(mock {
            on { findInfoAnimeById(1) } doReturn animeFlow
        })

        val result = useCase.invoke(1)

        assertEquals(animeFlow, result)

    }

}