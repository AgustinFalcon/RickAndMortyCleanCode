package com.example.practicesealedclass.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class Api {

    companion object {

        private fun buildService(url: String) : Service {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()


            val builder : Retrofit.Builder = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())


            val retrofit : Retrofit = builder.build()
            val client: Service = retrofit.create(Service::class.java)

            return client
        }


        @Volatile
        private var INSTANCE: Service? = null

        fun getInstance(url: String) : Service =
            INSTANCE ?: buildService(url).also {
                INSTANCE = it
            }
    }
}