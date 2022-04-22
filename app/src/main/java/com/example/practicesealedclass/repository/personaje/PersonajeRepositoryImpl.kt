package com.example.practicesealedclass.repository.personaje

import com.example.practicesealedclass.db.PersonajeDAO
import com.example.practicesealedclass.network.parsedata.personaje.ParsePersonaje
import com.example.practicesealedclass.network.retrofit.Api


class PersonajeRepositoryImpl constructor(personajeDao: PersonajeDAO) : PersonajeRepository {

    private var url = ""
    //var flow = Flow<String>()

    override fun setUrl(url: String) {
        this.url = url
    }

    override suspend fun getPersonaje(): EstadosService {
        val responseNetwork = Api.getInstance(url).getPersonajes()
        return try {
            if (responseNetwork.isSuccessful) {
                if (!(responseNetwork.body()?.parsePersonajes!!.isNullOrEmpty())) {
                    EstadosService.Success(responseNetwork.body()!!.parsePersonajes)
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
    data class Success(val personaje: List<ParsePersonaje>) : EstadosService()
    data class Error(val error: Exception) : EstadosService()
    data class Loading(val cargando: String) : EstadosService()
}
