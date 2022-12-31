package com.liam.android.moviekbz.model

import com.google.gson.annotations.SerializedName

data class ReviewModel(
    @field:SerializedName("id")val id: String,
    @field:SerializedName("author")val author: String,
    @field:SerializedName("content")val content: String,
    @field:SerializedName("url")val url: String
)