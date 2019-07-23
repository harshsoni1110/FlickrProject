package com.example.projectflickr.api

import com.example.projectflickr.constants.FlickrProjectConstants
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter(FlickrProjectConstants.api_key_key, FlickrProjectConstants.api_key_value)
            .addQueryParameter("format", "json")
            .addQueryParameter("nojsoncallback", "1")
            .build()

        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}