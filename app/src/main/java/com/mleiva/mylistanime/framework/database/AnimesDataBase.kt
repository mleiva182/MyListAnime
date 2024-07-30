package com.mleiva.mylistanime.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.datasource.database
 * Creted by: Marcelo Leiva on 23-07-2024 at 16:17
 ***/
@Database(entities = [DbAnime::class], version = 1, exportSchema = false )
@TypeConverters(Converters::class)
abstract class AnimesDataBase: RoomDatabase() {

    abstract fun animesDao(): AnimesDao

}