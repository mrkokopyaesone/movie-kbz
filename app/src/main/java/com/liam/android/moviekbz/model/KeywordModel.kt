package com.liam.android.moviekbz.model

import com.google.gson.annotations.SerializedName

data class KeywordModel(
    @field:SerializedName("id")val id: Int,
    @field:SerializedName("name")val name: String
)
