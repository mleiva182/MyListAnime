package com.mleiva.mylistanime.data.repository

import androidx.compose.runtime.staticCompositionLocalOf
import com.mleiva.mylistanime.data.model.Anime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.repository
 * Creted by: Marcelo Leiva on 17-04-2024 at 18:50
 ***/
class AnimesRepository(private val animesService: AnimesService) {

    suspend fun fetchAnimes(): List<Anime> = withContext(Dispatchers.IO) {
        animesService.fetchAnimes()
            .results
            .map {
                it.toDomainModel()
            }
    }

    suspend fun findInfoAnimeById(id: Int): Anime =
        animesService.fetchInfoAnime(id).toDomainModel()

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
)