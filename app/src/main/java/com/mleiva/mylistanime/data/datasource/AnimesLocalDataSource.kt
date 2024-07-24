package com.mleiva.mylistanime.data.datasource

import com.mleiva.mylistanime.data.datasource.database.AnimesDao
import com.mleiva.mylistanime.data.model.Anime
import kotlinx.coroutines.flow.Flow

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.datasource
 * Creted by: Marcelo Leiva on 23-07-2024 at 16:23
 ***/
class AnimesLocalDataSource(private val animesDao: AnimesDao) {

    val animes: Flow<List<Anime>> = animesDao.getAllAnimes()

    fun findAnimeById(id: Int): Flow<Anime?> = animesDao.getAnimeById(id)

    suspend fun isEmpty(): Boolean = animesDao.countAnimes() == 0

    suspend fun save(animes: List<Anime>) = animesDao.insertAnime(animes)

}