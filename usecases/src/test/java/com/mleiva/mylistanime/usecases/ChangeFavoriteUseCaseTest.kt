package com.mleiva.mylistanime.usecases

import com.mleiva.myanimelist.sampleAnime
import com.mleiva.mylistanime.data.repository.AnimesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.usecases
 * Creted by: Marcelo Leiva on 02-08-2024 at 15:16
 */
class ChangeFavoriteUseCaseTest{

    @Test
    fun `invoke calls repository to change favorite`() = runBlocking{
        val anime = sampleAnime(1)
        val repository = mock<AnimesRepository>()
        val useCase = ChangeFavoriteUseCase(repository)

        useCase(anime)

        verify(repository).changeFavorite(anime)

    }

}