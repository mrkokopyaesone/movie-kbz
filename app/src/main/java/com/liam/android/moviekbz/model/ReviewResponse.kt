package com.liam.android.moviekbz.model

import com.google.gson.annotations.SerializedName
import com.liam.android.moviekbz.model.entity.MovieModel

data class ReviewResponse(
    @SerializedName("id") val id : Int = 0,
    @SerializedName("results")val results: List<ReviewModel> = emptyList(),
)