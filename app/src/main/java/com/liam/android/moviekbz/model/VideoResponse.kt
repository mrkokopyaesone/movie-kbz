package com.liam.android.moviekbz.model

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id") val id : Int = 0,
    @SerializedName("results")val results: List<VideoModel> = emptyList(),
)