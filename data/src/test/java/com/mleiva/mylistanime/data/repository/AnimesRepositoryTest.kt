package com.mleiva.mylistanime.data.repository

import com.mleiva.myanimelist.sampleAnime
import com.mleiva.myanimelist.sampleAnimes
import com.mleiva.mylistanime.data.datasource.AnimesLocalDataSource
import com.mleiva.mylistanime.data.datasource.AnimesRemoteDataSource
import com.mleiva.mylistanime.domain.Anime
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.repository
 * Creted by: Marcelo Leiva on 02-08-2024 at 15:24
 */
@RunWith(MockitoJUnitRunner::class)
class AnimesRepositoryTest{

    @Mock
    lateinit var localDataSource: AnimesLocalDataSource

    @Mock
    lateinit var remoteDataSource: AnimesRemoteDataSource

    private lateinit var repository: AnimesRepository

    @Before
    fun setUp(){
        repository = AnimesRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `animes comes from local data source if available`() = runBlocking {
        val localAnimes = sampleAnimes(1,2,3)
        whenever(localDataSource.animes).thenReturn(flowOf(localAnimes))

        val result = repository.animes

        assertEquals(localAnimes, result.first())
    }

    @Test
    fun `animes are saved to local data source when its empty`() = runBlocking {
        val localAnimes = flowOf(emptyList<Anime>())
        val remoteAnimes = sampleAnimes(1,2,3)
        whenever(localDataSource.animes).thenReturn(localAnimes)
        whenever(remoteDataSource.fetchAnimes()).thenReturn(remoteAnimes)

        repository.animes.first()

        verify(localDataSource).save(remoteAnimes)
    }

    @Test
    fun `change favorite updates local data source`() = runBlocking {
        val anime = sampleAnime(1)
        repository.changeFavorite(anime)

        verify(localDataSource).save(argThat { get(0).id == 1 } )

    }

    @Test
    fun `Switch favorite mark as favorite and unfavorite anime`() = runBlocking {
        val anime = sampleAnime(1).copy(favorite = false)
        repository.changeFavorite(anime)

        verify(localDataSource).save(argThat { get(0).favorite })
    }

    @Test
    fun `Switch favorite mark as unfavorite a favorite anime`() = runBlocking{
        val anime = sampleAnime(1).copy(favorite = true)
        repository.changeFavorite(anime)

        verify(localDataSource).save(argThat { !get(0).favorite })
    }
}