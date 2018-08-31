package com.toolslab.challenge.base_network.service

import com.toolslab.challenge.base_network.ApiEndpoint.Endpoint.FEED
import com.toolslab.challenge.base_network.model.Feed
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(FEED)
    fun getFeed(): Single<Feed>

}
