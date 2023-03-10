package com.liam.android.moviekbz.room.converters

import com.liam.android.moviekbz.model.KeywordModel

object KeywordListMapper {
  fun mapToStringList(keywords: List<KeywordModel>): List<String> {
    var list: MutableList<String> = ArrayList()
    for (keyword in keywords) {
      list.add(keyword.name)
    }
    if (list.size > 7) {
      list = list.subList(0, 6)
    }
    return list
  }
}
