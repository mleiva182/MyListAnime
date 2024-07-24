package com.mleiva.mylistanime.data.datasource

import com.mleiva.mylistanime.data.model.Anime
import com.mleiva.mylistanime.data.datasource.remote.AnimesClient
import com.mleiva.mylistanime.data.datasource.remote.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.datasource
 * Creted by: Marcelo Leiva on 23-07-2024 at 15:52
 ***/
class AnimesRemoteDataSource() {

    suspend fun fetchAnimes(): List<Anime> = withContext(Dispatchers.IO) {
        AnimesClient.instance.fetchAnimes()
            .results
            .map {
                it.toDomainModel()
            }
    }

    suspend fun findInfoAnimeById(id: Int): Anime =
        AnimesClient.instance.fetchInfoAnime(id).toDomainModel()

}

private fun Data.toDomainModel() = Anime(
    id = malId,
    images = images,
    name = title,
    episodes = episodes,
    status = status,
    rating = rating,
    synopsis = synopsis,
    broadcast = broadcast,
    genres = genres,
    studios = studios,
    favorite = false
)