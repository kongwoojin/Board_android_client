package com.kongjak.board_client.data

import com.google.gson.annotations.SerializedName

data class Board(
    @SerializedName("title")
    val title: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("id")
    val id: Int
)