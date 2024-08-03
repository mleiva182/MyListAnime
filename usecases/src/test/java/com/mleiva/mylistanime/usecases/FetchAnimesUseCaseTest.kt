package com.mleiva.mylistanime.usecases

import com.mleiva.myanimelist.sampleAnimes
import com.mleiva.mylistanime.domain.Anime
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.usecases
 * Creted by: Marcelo Leiva on 02-08-2024 at 10:24
 */
class FetchAnimesUseCaseTest {

    @Test
    fun `invoke calls repository to list all animes`() {
        val animeFlow = flowOf(sampleAnimes(1,3,6))
        val useCase = FetchAnimesUseCase(mock {
            on { animes } doReturn animeFlow
        })

        val result = useCase.invoke()

        assertEquals(animeFlow, result)
    }
}