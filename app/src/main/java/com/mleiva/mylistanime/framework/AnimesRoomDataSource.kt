package com.mleiva.mylistanime.framework

import com.mleiva.mylistanime.data.datasource.AnimesLocalDataSource
import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.framework.database.AnimesDao
import com.mleiva.mylistanime.framework.database.DbAnime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimesRoomDataSource @Inject constructor(
    private val animesDao: AnimesDao) : AnimesLocalDataSource {

    override val animes: Flow<List<Anime>> = animesDao.getAllAnimes().map { animes -> animes.map { it.toDomainModel() } }

    override fun findAnimeById(id: Int): Flow<Anime?> = animesDao.getAnimeById(id).map { it?.toDomainModel() }

    override suspend fun isEmpty(): Boolean = animesDao.countAnimes() == 0

    override suspend fun save(animes: List<Anime>) = animesDao.insertAnime(animes.map { it.toDbAnime() })

}

private fun DbAnime.toDomainModel() = Anime(
    id = id,
    images = images,
    name = name,
    episodes = episodes,
    status = status,
    rating = rating,
    synopsis = synopsis,
    broadcast = broadcast,
    genres = genres,
    studios = studios,
    favorite = favorite
)

private fun Anime.toDbAnime() = DbAnime(
    id = id,
    images = images,
    name = name,
    episodes = episodes,
    status = status,
    rating = rating,
    synopsis = synopsis,
    broadcast = broadcast,
    genres = genres,
    studios = studios,
    favorite = favorite
)