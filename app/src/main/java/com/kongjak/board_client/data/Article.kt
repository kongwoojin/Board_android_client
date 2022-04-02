package com.kongjak.board_client.data

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    val title: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("view_count")
    val viewCount: String
)