package com.ar.projectfb.model

import com.google.gson.annotations.SerializedName

class tvShowFavorite(

    @SerializedName("id") val id : Int,
    @SerializedName("userId") val userId : String,
    @SerializedName("tvShowId") val tvShowId : String,
)

