package com.example.neckfit.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.neckfit.data.datamodel.Training

@Dao
interface TrainingsDatabaseDao {

    @Query("SELECT * FROM Training")
    suspend fun getAllFavorites() : LiveData<List<Training>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(training: Training)

    @Delete
    suspend fun delete(training: Training)


}
