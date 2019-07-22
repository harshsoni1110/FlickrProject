package com.hootsuite.shipmyid.api

import com.android.example.github.util.LiveDataCallAdapterFactory
import com.example.projectflickr.api.AuthenticationInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import java.lang.reflect.Type


class ServiceGenerator {
    companion object {
        private const val BASE_URL = "https://www.flickr.com/services/"
        private val httpClient = OkHttpClient.Builder()
        private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())


        private var retrofit = builder
            .client(httpClient.build())
            .build()

        private fun  addDeSerialzer (type: Type, typeAdapter: Any): GsonConverterFactory {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(type, typeAdapter)
            val gson = gsonBuilder.create()
            return GsonConverterFactory.create(gson)
        }

        fun addCustomGsonConverter (type: Type, typeAdapter: Any) {
            builder.addConverterFactory(addDeSerialzer(type, typeAdapter))
            retrofit = builder.client(httpClient.build()).build()
        }

        fun <T> createService (service: Class<T>) : T{
            val authenticationInterceptor = AuthenticationInterceptor()
            if (!httpClient.interceptors().contains(authenticationInterceptor)) {
                httpClient.addInterceptor(authenticationInterceptor)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
            return retrofit.create(service)
        }
    }

}