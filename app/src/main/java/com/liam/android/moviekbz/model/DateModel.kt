package com.liam.android.moviekbz.model

import com.google.gson.annotations.SerializedName

data class DateModel(
    @SerializedName("maximum") val maximum : String,
    @SerializedName("minimum")val minimum : String,
)
