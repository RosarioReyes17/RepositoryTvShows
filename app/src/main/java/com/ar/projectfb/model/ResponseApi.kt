package com.ar.projectfb.model

import com.google.gson.annotations.SerializedName

class ResponseApi {

    var totals: Int = 0;
    var page: Int = 1;
    var pages: Int = 0;
    var tv_shows : List<TvShow>? = null
    val tvshows : TvShow? = null

}
