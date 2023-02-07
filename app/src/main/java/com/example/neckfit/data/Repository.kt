package com.example.neckfit.data

import androidx.lifecycle.LiveData
import com.example.neckfit.data.datamodel.Theme
import com.example.neckfit.data.datamodel.Training
import com.example.neckfit.data.datamodel.Uebung
import com.example.neckfit.data.local.TrainingsDatabase
import com.example.neckfit.data.remote.NeckFitApi

class Repository(private val database: TrainingsDatabase) {

    suspend fun loadThemes(): List<Theme> {
        return NeckFitApi.retrofitService.getThemes()
    }

 //   suspend fun loadExercises(): List<Uebung> {
 //       return NeckFitApi.retrofitService.getExercises()
 //   }

    suspend fun loadAllTraining(): List<Training> {
        return NeckFitApi.retrofitService.getAllTraining()
    }

 //   fun getAllFavorite(): LiveData<List<Training>> =
  //      database.trainingsDatabaseDao.getAllFavorites()

 //   suspend fun deleteFavorite(training: Training){
 //       database.trainingsDatabaseDao.delete(training)
 //   }

    suspend fun setFavorite(training: Training) {
        if (training.favorite) {
            database.trainingsDatabaseDao.insert(training)
        }
    }
}