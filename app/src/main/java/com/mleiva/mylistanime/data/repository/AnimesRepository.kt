package com.mleiva.mylistanime.data.repository

import androidx.compose.runtime.staticCompositionLocalOf
import com.mleiva.mylistanime.data.datasource.AnimesLocalDataSource
import com.mleiva.mylistanime.data.datasource.AnimesRemoteDataSource
import com.mleiva.mylistanime.data.model.Anime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.repository
 * Creted by: Marcelo Leiva on 17-04-2024 at 18:50
 ***/
class AnimesRepository(
    private val animesLocalDataSource: AnimesLocalDataSource,
    private val animesRemoteDataSource: AnimesRemoteDataSource
) {

    val animes: Flow<List<Anime>> = animesLocalDataSource.animes.transform { localAnimes ->
        val movies = localAnimes.takeIf { it.isNotEmpty() }
            ?: animesRemoteDataSource.fetchAnimes().also {
                animesLocalDataSource.save(it)
            }
        emit(movies)
    }

    /*suspend fun fetchAnimes(): List<Anime> {
        if (animesLocalDataSource.isEmpty()) {
            val animes = animesRemoteDataSource.fetchAnimes()
            animesLocalDataSource.save(animes)
        }
        return animesLocalDataSource.fetchAnimes()
    }*/

    fun findInfoAnimeById(id: Int): Flow<Anime> =
        animesLocalDataSource.findAnimeById(id).transform { localAnime ->
            val movie = localAnime
                ?: animesRemoteDataSource.findInfoAnimeById(id).also { animesLocalDataSource.save(listOf(it)) }
            emit(movie)
        }

    /*suspend fun findInfoAnimeById(id: Int): Anime {
        if(animesLocalDataSource.findAnimeById(id) == null) {
            val movie = animesRemoteDataSource.findInfoAnimeById(id)
            animesLocalDataSource.save(listOf(movie))
        }
        return checkNotNull(animesLocalDataSource.findAnimeById(id))
    }*/

    suspend fun changeFavorite(anime: Anime){
        animesLocalDataSource.save(listOf(anime.copy(favorite = !anime.favorite)))
    }
}