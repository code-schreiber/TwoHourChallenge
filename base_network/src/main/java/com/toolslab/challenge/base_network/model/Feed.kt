package com.toolslab.challenge.base_network.model

import com.squareup.moshi.Json

data class Feed(

        @Json(name = "data")
        val data: Data,

        @Json(name = "meta")
        val meta: Meta?
)

