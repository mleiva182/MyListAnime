package com.mleiva.mylistanime.framework

import com.mleiva.mylistanime.data.datasource.AnimesRemoteDataSource
import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.framework.remote.AnimesService
import com.mleiva.mylistanime.framework.remote.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimesServerDataSource(
    private val animesService: AnimesService
) : AnimesRemoteDataSource {

    override suspend fun fetchAnimes(): List<Anime> = withContext(Dispatchers.IO) {
        animesService.fetchAnimes()
            .results
            .map {
                it.toDomainModel()
            }
    }

    override suspend fun findInfoAnimeById(id: Int): Anime =
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
    favorite = false
)