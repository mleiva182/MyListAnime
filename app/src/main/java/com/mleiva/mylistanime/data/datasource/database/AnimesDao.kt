package com.mleiva.mylistanime.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mleiva.mylistanime.data.model.Anime
import kotlinx.coroutines.flow.Flow

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.datasource.database
 * Creted by: Marcelo Leiva on 23-07-2024 at 16:12
 ***/
@Dao
interface AnimesDao {

    @Query("SELECT * FROM Anime")
    fun getAllAnimes(): Flow<List<Anime>>

    @Query("SELECT * FROM Anime WHERE id = :id")
    fun getAnimeById(id: Int): Flow<Anime>

    @Query("SELECT COUNT(*) FROM Anime")
    suspend fun countAnimes(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: List<Anime>)
}