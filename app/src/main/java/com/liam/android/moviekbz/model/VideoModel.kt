package com.liam.android.moviekbz.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "video")
data class VideoModel(
    @PrimaryKey
    @field:SerializedName("id")val id: String,
    @field:SerializedName("name")val name: String,
    @field:SerializedName("site")val site: String,
    @field:SerializedName("key")val key: String,
    @field:SerializedName("size")val size: Int,
    @field:SerializedName("type")val type: String,
    val page: Int
)
