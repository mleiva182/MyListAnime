package com.mleiva.mylistanime.data.repository


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    @SerialName("data") val results: List<Data>
)

@Serializable
data class Data(
    @SerialName("mal_id")
    val malId: Int = 0,
    val images: Images = Images(Jpg()),
    @SerialName("title")
    val title: String = "",
    @SerialName("episodes")
    val episodes: Int = 0,
    val broadcast: Broadcast = Broadcast(),
    val genres: List<Genre> = emptyList(),
    )

@Serializable
data class Broadcast(
    @SerialName("day")
    val day: String = "",
    @SerialName("time")
    val time: String = "",
    @SerialName("timezone")
    val timeZone: String = "",
    @SerialName("string")
    val string: String = ""
)

@Serializable
data class Genre(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class Images(
    @SerialName("jpg")
    val jpg: Jpg
)

@Serializable
data class Jpg(
    @SerialName("image_url")
    val imageUrl: String = "",
    @SerialName("large_image_url")
    val largeImageUrl: String = "",
    @SerialName("small_image_url")
    val smallImageUrl: String = ""
)