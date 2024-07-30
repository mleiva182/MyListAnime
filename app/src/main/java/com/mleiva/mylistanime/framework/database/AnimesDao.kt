package com.mleiva.mylistanime.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.datasource.database
 * Creted by: Marcelo Leiva on 23-07-2024 at 16:12
 ***/
@Dao
interface AnimesDao {

    @Query("SELECT * FROM DbAnime")
    fun getAllAnimes(): Flow<List<DbAnime>>

    @Query("SELECT * FROM DbAnime WHERE id = :id")
    fun getAnimeById(id: Int): Flow<DbAnime>

    @Query("SELECT COUNT(*) FROM DbAnime")
    suspend fun countAnimes(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: List<DbAnime>)
}