package com.example.practicesealedclass.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicesealedclass.repository.personaje.EstadosService
import com.example.practicesealedclass.repository.personaje.PersonajeRepository
import kotlinx.coroutines.launch


class ApiViewModel constructor(private val repository: PersonajeRepository): ViewModel() {

    val personajeLiveDataSuccess = MutableLiveData<List<String>>()
    val personajeLivedataError = MutableLiveData<Exception>()

    fun setUrl(url: String) {
        repository.setUrl(url = url)
    }

    fun getPersonaje() {
        viewModelScope.launch {
            val response = repository.getPersonaje()
            when(response){
                is EstadosService.Success -> {personajeLiveDataSuccess.postValue(listOf(response.response))}
                is EstadosService.Loading -> {personajeLiveDataSuccess.postValue(listOf(response.cargando))}
                is EstadosService.Error -> {personajeLivedataError.postValue(response.error)}
            }
        }
    }


}

sealed class EstadosUI {

}


