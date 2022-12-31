package com.liam.android.moviekbz.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.liam.android.moviekbz.model.KeywordModel

open class KeywordListConverter {
  @TypeConverter
  fun fromString(value: String): List<KeywordModel>? {
    val listType = object : TypeToken<List<KeywordModel>>() {}.type
    return Gson().fromJson<List<KeywordModel>>(value, listType)
  }

  @TypeConverter
  fun fromList(list: List<KeywordModel>?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
