package com.toolslab.challenge.base_network

import com.toolslab.challenge.base_network.ApiEndpoint.Header.AUTHORIZATION
import okhttp3.*
import javax.inject.Inject

class Reauthenticator @Inject constructor() : Authenticator {

    internal lateinit var credentials: String

    override fun authenticate(route: Route?, response: Response?): Request? {
        if (response == null) return null
        val originalRequest = response.request()
        if (originalRequest.header(AUTHORIZATION) != null) return null // Already failed to authenticate
        if (!::credentials.isInitialized) {
            synchronized(this) {
                if (!::credentials.isInitialized) { // Check if another thread already got a token
                    credentials = Credentials.basic("", "")
                }
            }
        }
        return originalRequest.newBuilder()
                .header(AUTHORIZATION, credentials)
                .build()
    }

}
