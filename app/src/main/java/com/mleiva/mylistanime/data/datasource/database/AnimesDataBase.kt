package com.mleiva.mylistanime.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mleiva.mylistanime.data.Converters
import com.mleiva.mylistanime.data.model.Anime

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.datasource.database
 * Creted by: Marcelo Leiva on 23-07-2024 at 16:17
 ***/
@Database(entities = [Anime::class], version = 1, exportSchema = false )
@TypeConverters(Converters::class)
abstract class AnimesDataBase: RoomDatabase() {

    abstract fun animesDao(): AnimesDao

}