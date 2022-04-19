package com.ar.projectfb.model

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    //  https://www.episodate.com/api/most-popular?page=1
    @GET("/api/most-popular")
    fun getPopular(@Query("page")page: Int = 1):
            Call<ResponseApi>

    @GET("/api/search")
    fun search(@Query("q")q : String) : Call<ResponseApi>

    @GET("/api/show-details")
    fun tvDetailsShow(@Query("q")details : String) : Call<ResponseAll>


    companion object{
        private var _instance : ApiServices? = null

        fun getInstance(): ApiServices{
            if(_instance == null){

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.episodate.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build()
                _instance= retrofit.create(ApiServices::class.java)

            }
            return _instance!!
        }
    }
}

