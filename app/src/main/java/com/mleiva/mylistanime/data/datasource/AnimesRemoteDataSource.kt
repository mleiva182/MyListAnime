package com.mleiva.mylistanime.data.datasource

import com.mleiva.mylistanime.domain.Anime

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.datasource
 * Creted by: Marcelo Leiva on 23-07-2024 at 15:52
 ***/
interface AnimesRemoteDataSource {
    suspend fun fetchAnimes(): List<Anime>

    suspend fun findInfoAnimeById(id: Int): Anime
}