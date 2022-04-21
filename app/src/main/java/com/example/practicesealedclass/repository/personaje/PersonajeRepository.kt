package com.example.practicesealedclass.repository.personaje


interface PersonajeRepository {
    suspend fun getPersonaje() : EstadosService
    fun setUrl(url: String)
}