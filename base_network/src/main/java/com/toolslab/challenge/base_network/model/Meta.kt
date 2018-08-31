package com.toolslab.challenge.base_network.model

import com.squareup.moshi.Json

data class Meta(

        @Json(name = "topCategory")
        val topCategory: TopCategory,

        @Json(name = "recentKeywords")
        val recentKeywords: List<Any>,

        @Json(name = "recentCategories")
        val recentCategories: List<Any>
)
