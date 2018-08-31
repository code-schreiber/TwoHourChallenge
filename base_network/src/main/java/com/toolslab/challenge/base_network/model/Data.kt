package com.toolslab.challenge.base_network.model

import com.squareup.moshi.Json

data class Data(

        @Json(name = "nextLink")
        val nextLink: String,

        @Json(name = "selfLink")
        val selfLink: String,

        @Json(name = "pageIndex")
        val pageIndex: Int,

        @Json(name = "itemsPerPage")
        val itemsPerPage: Int,

        @Json(name = "items")
        val items: List<Item>
)
