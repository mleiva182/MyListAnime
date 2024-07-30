package com.mleiva.mylistanime.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mleiva.mylistanime.framework.remote.Broadcast
import com.mleiva.mylistanime.framework.remote.Genre
import com.mleiva.mylistanime.framework.remote.Images
import com.mleiva.mylistanime.framework.remote.Studio

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data
 * Creted by: Marcelo Leiva on 23-07-2024 at 16:45
 ***/
class Converters {

     @TypeConverter
     fun fromImages(images: Images): String {
      return Gson().toJson(images)
     }

     @TypeConverter
     fun toImages(json: String): Images {
      return Gson().fromJson(json, Images::class.java)
     }

     @TypeConverter
     fun fromBroadcast(broadcast: Broadcast): String {
      return Gson().toJson(broadcast)
     }

     @TypeConverter
     fun toBroadcast(json: String): Broadcast {
      return Gson().fromJson(json, Broadcast::class.java)
     }

     @TypeConverter
     fun fromGenreList(genres: List<Genre>): String {
      return Gson().toJson(genres)
     }

     @TypeConverter
     fun toGenreList(json: String): List<Genre> {
      val listType = object : TypeToken<List<Genre>>() {}.type
      return Gson().fromJson(json, listType)
     }

     @TypeConverter
     fun fromStudioList(studios: List<Studio>): String {
      return Gson().toJson(studios)
     }

     @TypeConverter
     fun toStudioList(json: String): List<Studio> {
      val listType = object : TypeToken<List<Studio>>() {}.type
      return Gson().fromJson(json, listType)
     }
}
