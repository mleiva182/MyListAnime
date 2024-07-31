package com.mleiva.mylistanime.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import com.mleiva.mylistanime.ui.screens.navigation.NavArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.detail
 * Creted by: Marcelo Leiva on 31-07-2024 at 12:22
 ***/

@Module
@InstallIn(ViewModelComponent::class)
class InfoAnimeViewModelModule {

    @Provides
    @ViewModelScoped
    @Named("animeId")
    fun provideAnimeId(savedStateHandle: SavedStateHandle) : Int{
        return checkNotNull(savedStateHandle[NavArgs.AnimeId.key])
    }

}