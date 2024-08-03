package com.mleiva.mylistanime.data.repository

import com.mleiva.mylistanime.data.datasource.AnimesLocalDataSource
import com.mleiva.mylistanime.data.datasource.AnimesRemoteDataSource
import com.mleiva.mylistanime.domain.Anime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.repository
 * Creted by: Marcelo Leiva on 17-04-2024 at 18:50
 ***/
class AnimesRepository @Inject constructor(
    private val animesLocalDataSource: AnimesLocalDataSource,
    private val animesRemoteDataSource: AnimesRemoteDataSource
) {

    val animes: Flow<List<Anime>>
        get() = animesLocalDataSource.animes.onEach { localAnimes ->
        if (localAnimes.isEmpty()) {
            val remoteAnimes = animesRemoteDataSource.fetchAnimes()
            animesLocalDataSource.save(remoteAnimes)
        }
    }

    fun findInfoAnimeById(id: Int): Flow<Anime> = animesLocalDataSource.findAnimeById(id)
        .onEach { anime ->
            if (anime == null) {
                val remoteAnime = animesRemoteDataSource.findInfoAnimeById(id)
                animesLocalDataSource.save(listOf(remoteAnime))
            }
        }
        .filterNotNull()

    suspend fun changeFavorite(anime: Anime){
        animesLocalDataSource.save(listOf(anime.copy(favorite = !anime.favorite)))
    }
}