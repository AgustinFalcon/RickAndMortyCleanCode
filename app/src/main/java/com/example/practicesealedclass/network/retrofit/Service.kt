package com.example.practicesealedclass.network.retrofit

import com.example.practicesealedclass.network.parsedata.personaje.ResponsePersonaje
import retrofit2.Response
import retrofit2.http.GET


interface Service {

    @GET("character")
    fun getPersonajes() : Response<ResponsePersonaje>

}