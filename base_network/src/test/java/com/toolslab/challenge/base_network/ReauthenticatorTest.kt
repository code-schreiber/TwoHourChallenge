package com.toolslab.challenge.base_network

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.challenge.base_network.ApiEndpoint.Header.AUTHORIZATION
import okhttp3.Request
import okhttp3.Response
import org.amshove.kluent.shouldEqual
import org.junit.Test

class ReauthenticatorTest {

    private val credentials = "some credentials"

    private val mockRequest: Request = mock()
    private val mockNewRequest: Request = mock()
    private val mockResponse: Response = mock()
    private val mockBuilder: Request.Builder = mock()

    private val underTest = Reauthenticator()

    @Test
    fun authenticateWithNullResponse() {
        val request = underTest.authenticate(null, null)

        request shouldEqual null
    }

    @Test
    fun authenticateAlreadyFailedToAuthenticate() {
        whenever(mockResponse.request()).thenReturn(mockRequest)
        whenever(mockRequest.header(ApiEndpoint.Header.AUTHORIZATION)).thenReturn("a header's token")

        val request = underTest.authenticate(null, mockResponse)

        request shouldEqual null
    }

    @Test
    fun authenticateWhenCredentialsAreInitialized() {
        underTest.credentials = credentials
        whenever(mockResponse.request()).thenReturn(mockRequest)
        whenever(mockRequest.header(ApiEndpoint.Header.AUTHORIZATION)).thenReturn(null)
        whenever(mockRequest.newBuilder()).thenReturn(mockBuilder)
        whenever(mockBuilder.header(AUTHORIZATION, credentials)).thenReturn(mockBuilder)
        whenever(mockBuilder.build()).thenReturn(mockNewRequest)

        val request = underTest.authenticate(null, mockResponse)

        request shouldEqual mockNewRequest
    }

    @Test
    fun authenticateWhenCredentialsAreNotInitialized() {
        whenever(mockResponse.request()).thenReturn(mockRequest)
        whenever(mockRequest.header(ApiEndpoint.Header.AUTHORIZATION)).thenReturn(null)
        whenever(mockRequest.newBuilder()).thenReturn(mockBuilder)
        whenever(mockBuilder.header(AUTHORIZATION, "Basic Og==")).thenReturn(mockBuilder)
        whenever(mockBuilder.build()).thenReturn(mockNewRequest)

        val request = underTest.authenticate(null, mockResponse)

        request shouldEqual mockNewRequest
    }

}
