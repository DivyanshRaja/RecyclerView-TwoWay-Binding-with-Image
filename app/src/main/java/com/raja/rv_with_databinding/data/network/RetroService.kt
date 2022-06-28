package com.raja.rv_with_databinding.data.network

import com.raja.rv_with_databinding.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("repositories")
    fun getDataFromAPI(@Query("q") query: String): Call<User>
}