package com.kongjak.board_client.connections

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var api : API

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.43:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(API::class.java)
    }
}
