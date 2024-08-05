package com.mleiva.mylistanime.di

import android.app.Application
import androidx.room.Room
import com.mleiva.mylistanime.AnimeModule
import com.mleiva.mylistanime.AppExtrasModule
import com.mleiva.mylistanime.AppModule
import com.mleiva.mylistanime.framework.database.AnimesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Named
import javax.inject.Singleton

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.di
 * Creted by: Marcelo Leiva on 05-08-2024 at 16:27
 ***/
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppExtrasModule::class]
)

object TestAppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AnimesDataBase {
        val db = Room.inMemoryDatabaseBuilder(
            app,
            AnimesDataBase::class.java
        )
            .setTransactionExecutor(Dispatchers.Main.asExecutor())
            .allowMainThreadQueries()
            .build()
        return db
    }

    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl(): String = "http://localhost:8080"
}
