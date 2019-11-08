package com.dafian.android.remote.model

import com.google.gson.annotations.SerializedName

data class QuoteDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("text")
    val text: String
)