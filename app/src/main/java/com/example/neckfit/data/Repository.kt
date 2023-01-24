package com.example.neckfit.data

import com.example.neckfit.data.datamodel.Theme
import com.example.neckfit.data.datamodel.Training
import com.example.neckfit.data.datamodel.Uebung
import com.example.neckfit.data.remote.NeckFitApi


class Repository() {

    suspend fun loadThemes() : List<Theme> {
        return NeckFitApi.retrofitService.getThemes()
    }

    suspend fun loadExercises() : List<Uebung> {
        return NeckFitApi.retrofitService.getExercises()
    }

    suspend fun loadAllTraining() : List<Training> {
        return NeckFitApi.retrofitService.getAllTraining()
    }
}

