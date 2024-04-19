package com.mleiva.mylistanime.data.model

import com.mleiva.mylistanime.data.repository.Broadcast
import com.mleiva.mylistanime.data.repository.Genre
import com.mleiva.mylistanime.data.repository.Images

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.model
 * Creted by: Marcelo Leiva on 18-04-2024 at 10:44
 ***/
data class Anime(
    val id: Int,
    val images: Images,
    val name: String,
    val episodes: Int,
    val broadcast: Broadcast,
    val genres: List<Genre>
)
