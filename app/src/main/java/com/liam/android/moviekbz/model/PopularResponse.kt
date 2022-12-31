package com.liam.android.moviekbz.model

import com.google.gson.annotations.SerializedName
import com.liam.android.moviekbz.model.entity.MovieModel

data class PopularResponse(
    @SerializedName("total_pages") val total_pages : Int = 0,
    @SerializedName("total_results") val total_results : Int = 0,
    @SerializedName("results")val results: List<MovieModel> = emptyList(),
)
