package com.mleiva.mylistanime.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mleiva.mylistanime.framework.remote.Broadcast
import com.mleiva.mylistanime.framework.remote.Genre
import com.mleiva.mylistanime.framework.remote.Images
import com.mleiva.mylistanime.framework.remote.Studio

@Entity
data class DbAnime(
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