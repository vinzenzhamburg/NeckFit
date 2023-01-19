package com.example.neckfit.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.neckfit.data.datamodel.Theme
import com.example.neckfit.data.datamodel.Uebung
import com.example.neckfit.data.remote.NeckFitApi


class Repository() {

    suspend fun loadThemes() : List<Theme> {
        return NeckFitApi.retrofitService.getThemes().themeList
    }

    private val _exercises = MutableLiveData<List<Uebung>>()
    val exercises: LiveData<List<Uebung>>
        get() = _exercises

//    suspend fun loadExercises() {
//        _exercises.value = NeckFitApi.retrofitService.getExercises()
//    }
}

