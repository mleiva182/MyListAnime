package com.mleiva.mylistanime.data.datasource

import com.mleiva.mylistanime.domain.Anime
import kotlinx.coroutines.flow.Flow

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.datasource
 * Creted by: Marcelo Leiva on 23-07-2024 at 16:23
 ***/
interface AnimesLocalDataSource {
    val animes: Flow<List<Anime>>
    fun findAnimeById(id: Int): Flow<Anime?>

    suspend fun isEmpty(): Boolean

    suspend fun save(animes: List<Anime>)
}