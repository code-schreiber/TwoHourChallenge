package com.toolslab.challenge.base_network.model

import com.squareup.moshi.Json

data class Item(

        @Json(name = "adId")
        val adId: String,

        @Json(name = "title")
        val title: String,

        @Json(name = "image")
        val image: String?,

        @Json(name = "locationName")
        val locationName: String
)
