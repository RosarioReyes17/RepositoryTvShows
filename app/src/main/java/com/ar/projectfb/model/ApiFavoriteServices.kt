package com.ar.projectfb.model

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiFavoriteServices {

    @GET("/api/Favorites/{userId}")
    fun getFavorite(@Path("userId") UserId : String) : Call<List<ResponseFavoriteApi>>

    @POST("/api/Favorites")
    fun post(@Body ResponseFavoriteApi: ResponseFavoriteApi): Call<ResponseFavoriteApi>

    @DELETE("/api/Favorites")
    fun delete(@Path("q")q : String) : Call<ResponseFavoriteApi>


    companion object{
        private var _instance : ApiFavoriteServices? = null

        fun getInstance(): ApiFavoriteServices{
            if(_instance == null){

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://moviestvshow.azurewebsites.net")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build()
                _instance= retrofit.create(ApiFavoriteServices::class.java)

            }
            return _instance!!
        }
    }
}