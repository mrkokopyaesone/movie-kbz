package com.liam.android.moviekbz.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.liam.android.moviekbz.model.KeywordModel
import com.liam.android.moviekbz.model.ReviewModel
import com.liam.android.moviekbz.model.VideoModel
@Entity(tableName = "movie")
data class MovieModel(
    @PrimaryKey @field:SerializedName("id") val id: Int,
    @field:SerializedName("page") var page: Int,
    @field:SerializedName("keywords") var keywords: List<KeywordModel>? = ArrayList(),
    @field:SerializedName("videos") var videos: List<VideoModel>? = ArrayList(),
    @field:SerializedName("reviews") var reviews: List<ReviewModel>? = ArrayList(),
    @field:SerializedName("poster_path") val poster_path: String?,
    @field:SerializedName("adult") val adult: Boolean,
    @field:SerializedName("overview") val overview: String,
    @field:SerializedName("release_date") val release_date: String?,
    @field:SerializedName("genre_ids") val genre_ids: List<Int>,
    @field:SerializedName("original_title") val original_title: String,
    @field:SerializedName("original_language") val original_language: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("backdrop_path") val backdrop_path: String?,
    @field:SerializedName("popularity") val popularity: Float,
    @field:SerializedName("vote_count") val vote_count: Int,
    @field:SerializedName("video") val video: Boolean,
    @field:SerializedName("vote_average") val vote_average: Float
)
