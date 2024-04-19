package com.mleiva.mylistanime.data.repository

import com.mleiva.mylistanime.data.model.Anime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.repository
 * Creted by: Marcelo Leiva on 17-04-2024 at 18:50
 ***/
class AnimesRepository {

    suspend fun fetchAnimes(): List<Anime> = withContext(Dispatchers.IO) {
        AnimesClient.instance.fetchAnimes()
            .results
            .map {
                it.toDomainModel()
            }
    }

    suspend fun findInfoAnimeById(id: Int): Anime = AnimesClient.instance.fetchInfoAnime(id).toDomainModel()


}

private fun Data.toDomainModel() = Anime(
    id = malId,
    images = images,
    name = title,
    episodes = episodes,
    broadcast = broadcast,
    genres = genres
)