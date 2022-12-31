package com.liam.android.moviekbz.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.liam.android.moviekbz.model.VideoModel

open class VideoListConverter {
  @TypeConverter
  fun fromString(value: String): List<VideoModel>? {
    val listType = object : TypeToken<List<VideoModel>>() {}.type
    return Gson().fromJson<List<VideoModel>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<VideoModel>?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
