package com.example.practicesealedclass.repository.personaje

import com.example.practicesealedclass.network.retrofit.Api


class PersonajeRepositoryImpl : PersonajeRepository {

    private var url = ""
    //var flow = Flow<String>()

    override fun setUrl(url: String) {
        this.url = url
    }

    override suspend fun getPersonaje(): EstadosService {
        val responseNetwork = Api.getInstance(url).getPersonajes()
        return try {
            if (responseNetwork.isSuccessful) {
                if (!(responseNetwork.body()?.personajes!!.isNullOrEmpty())) {
                    EstadosService.Success(responseNetwork.body().toString())
                } else {
                    EstadosService.Loading(responseNetwork.body().toString())
                }
            } else {
                EstadosService.Error(Exception(responseNetwork.errorBody().toString()))
            }
        } catch (e: Exception) {
            EstadosService.Error(e)
        }
    }

}

sealed class EstadosService {
    data class Success(val response: String) : EstadosService()
    data class Error(val error: Exception) : EstadosService()
    data class Loading(val cargando: String) : EstadosService()
}
