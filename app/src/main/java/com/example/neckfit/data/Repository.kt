package com.example.neckfit.data

import com.example.neckfit.data.datamodel.Theme
import com.example.neckfit.data.datamodel.Training
import com.example.neckfit.data.local.TrainingsDatabase
import com.example.neckfit.data.remote.NeckFitApi

class Repository(private val database: TrainingsDatabase) {

    suspend fun loadThemes(): List<Theme> {
        return NeckFitApi.retrofitService.getThemes()
    }

    suspend fun loadAllTraining(): List<Training> {
        return NeckFitApi.retrofitService.getAllTraining()
    }

    suspend fun deleteFavorite(training: Training) {
        database.trainingsDatabaseDao.delete(training)
    }

    suspend fun setFavorite(training: Training) {
        if (training.favorite) {
            database.trainingsDatabaseDao.insert(training)
        }
    }
}