package com.example.projectflickr.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", "6bf318919bbbc455f3573d18798a58e3")
            .addQueryParameter("format", "json")
            .addQueryParameter("nojsoncallback", "1")
            .build()
        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}