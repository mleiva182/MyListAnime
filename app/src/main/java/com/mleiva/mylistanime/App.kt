package com.mleiva.mylistanime

import android.app.Application
import androidx.room.Room
import com.mleiva.mylistanime.framework.database.AnimesDataBase

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime
 * Creted by: Marcelo Leiva on 23-07-2024 at 16:19
 ***/
class App : Application() {

    lateinit var dataBase: AnimesDataBase
        private set

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(this,
            AnimesDataBase::class.java,
            "animes-db")
            .build()
    }

}