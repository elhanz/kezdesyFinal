package com.example.kezdesy.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    const val BASE_URL = "https://kezdesy.herokuapp.com/"

    private val retrofit by lazy {


        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val API_USER: ApiKezdesy by lazy {
        retrofit.create(ApiKezdesy::class.java)
    }
}