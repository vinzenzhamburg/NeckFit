package com.example.neckfit.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.neckfit.data.datamodel.Training

@Database(entities = [Training::class], version = 1)
abstract class TrainingsDatabase : RoomDatabase() {
    abstract val trainingsDatabaseDao: TrainingsDatabaseDao
}

private lateinit var INSTANCE: TrainingsDatabase

fun getDatabase(context: Context): TrainingsDatabase {
    synchronized(TrainingsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                TrainingsDatabase::class.java,
                "trainings_database"
            ).build()
        }
    }
    return INSTANCE
}