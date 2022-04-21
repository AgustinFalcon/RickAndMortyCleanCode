package com.example.practicesealedclass.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var liveData: MutableLiveData<Estados> = MutableLiveData()

    fun validate(user: String, pass: String) {
        if (user.isNotEmpty() && pass.isNotEmpty()) {
            if (user == "pepe" && pass == "123") {
                liveData.postValue(Estados.SUCCESS)
            } else {
                liveData.postValue(Estados.ERROR)
            }
        } else {
            liveData.postValue(Estados.EMPTY)
        }
    }

}

enum class Estados {
    SUCCESS, ERROR, EMPTY
}

