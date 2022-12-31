package com.liam.android.moviekbz.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.liam.android.moviekbz.model.ReviewModel

open class ReviewListConverter {
  @TypeConverter
  fun fromString(value: String): List<ReviewModel>? {
    val listType = object : TypeToken<List<ReviewModel>>() {}.type
    return Gson().fromJson<List<ReviewModel>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<ReviewModel>?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
