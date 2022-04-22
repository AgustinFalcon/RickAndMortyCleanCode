package com.example.practicesealedclass.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicesealedclass.network.parsedata.personaje.ParsePersonaje
import com.example.practicesealedclass.repository.personaje.EstadosService
import com.example.practicesealedclass.repository.personaje.PersonajeRepository
import kotlinx.coroutines.launch


class PersonajeViewModel constructor(private val repository: PersonajeRepository): ViewModel() {

    val personajeLiveDataSuccess = MutableLiveData<List<ParsePersonaje>>()
    val personajeLivedataError = MutableLiveData<Exception>()
    var personajeCargandoData: String = ""

    fun setUrl(url: String) {
        repository.setUrl(url = url)
    }

    fun getPersonaje() {
        viewModelScope.launch {
            val response = repository.getPersonaje()
            when(response){
                is EstadosService.Success -> {personajeLiveDataSuccess.postValue(response.personaje)}
                is EstadosService.Loading -> {personajeCargandoData = response.cargando}
                is EstadosService.Error -> {personajeLivedataError.postValue(response.error)}
            }
        }
    }


}

sealed class EstadosUI {

}


