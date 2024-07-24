package com.mleiva.mylistanime.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mleiva.mylistanime.data.datasource.remote.Broadcast
import com.mleiva.mylistanime.data.datasource.remote.Genre
import com.mleiva.mylistanime.data.datasource.remote.Images
import com.mleiva.mylistanime.data.datasource.remote.Studio

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.model
 * Creted by: Marcelo Leiva on 18-04-2024 at 10:44
 ***/
@Entity
data class Anime(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val images: Images,
    val name: String,
    val episodes: Int,
    val status: String,
    val rating: String,
    val synopsis: String,
    val broadcast: Broadcast,
    val genres: List<Genre>,
    val studios: List<Studio>,
    val favorite: Boolean
)
