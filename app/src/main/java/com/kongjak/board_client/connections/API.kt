package com.kongjak.board_client.connections

import com.kongjak.board_client.data.Article
import com.kongjak.board_client.data.Board
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("api/post")
    fun getBoard(): Call<ArrayList<Board>>
    @GET("api/{id}")
    fun getArticle(@Path("id") id: Int): Call<Article>
}