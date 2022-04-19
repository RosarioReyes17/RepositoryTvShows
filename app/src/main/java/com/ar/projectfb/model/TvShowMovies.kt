package com.ar.projectfb.model

import com.google.gson.annotations.SerializedName

class TvShow {

    @SerializedName("id")
    val id: Int=0
    @SerializedName("name")
    val title: String=""
    @SerializedName("url")
    val url: String=""
    @SerializedName("description")
    val description: String=""
    @SerializedName("description_source")
    val description_source: String=""
    @SerializedName("start_date")
    val releasedate: String=""
    @SerializedName("end_date")
    val end_date: String=""
    @SerializedName("country")
    val country: String=""
    @SerializedName("status")
    val status: String=""
    @SerializedName("network")
    val network: String=""
    @SerializedName("youtube_link")
    val youtube_link: String=""
    @SerializedName("image_thumbnail_path")
    val image_thumbnail_path: String=""
}