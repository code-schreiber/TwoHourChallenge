package com.toolslab.challenge.base_network.model

import com.squareup.moshi.Json

data class TopCategory(

        @Json(name = "levelOneCategory")
        val levelOneCategory: String,

        @Json(name = "levelTwoCategory")
        val levelTwoCategory: String
)
