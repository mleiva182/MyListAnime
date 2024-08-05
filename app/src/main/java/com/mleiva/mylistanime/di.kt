package com.mleiva.mylistanime

import android.app.Application
import androidx.room.PrimaryKey
import androidx.room.Room
import com.mleiva.mylistanime.data.datasource.AnimesLocalDataSource
import com.mleiva.mylistanime.data.datasource.AnimesRemoteDataSource
import com.mleiva.mylistanime.framework.AnimesRoomDataSource
import com.mleiva.mylistanime.framework.AnimesServerDataSource
import com.mleiva.mylistanime.framework.database.AnimesDataBase
import com.mleiva.mylistanime.framework.remote.AnimesClient
import com.mleiva.mylistanime.framework.remote.AnimesService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime
 * Creted by: Marcelo Leiva on 31-07-2024 at 11:56
 ***/
@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    /*@Provides
    @Singleton
    fun providesDatabase(app: Application) = Room.databaseBuilder(app, AnimesDataBase::class.java, "animes-db").build()
*/
    @Provides
    fun providesAnimesDao(db: AnimesDataBase) = db.animesDao()

    @Provides
    @Singleton
    fun providesAnimeClient(
        @Named("apiUrl") apiUrl: String
    ): AnimesService = AnimesClient(apiUrl).instance
}

@Module
@InstallIn(SingletonComponent::class)
object AppExtrasModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        AnimesDataBase::class.java,
        "animes-db"
    ).build()

    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl(): String = "https://api.jikan.moe/v4/"
}


@Module
@InstallIn(SingletonComponent::class)
internal abstract class AnimeModule {
    @Binds
    abstract fun bindAnimesLocalDataSource(animeLocalDataSource: AnimesRoomDataSource): AnimesLocalDataSource
    @Binds
    abstract fun bindAnimesRemoteDataSource(animesRemoteDataSource: AnimesServerDataSource): AnimesRemoteDataSource

}