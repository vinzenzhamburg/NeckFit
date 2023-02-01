package com.example.neckfit.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.neckfit.data.datamodel.Training

@Dao
interface TrainingsDatabaseDao {
    @Query("SELECT * FROM Training")
    fun getAll() : LiveData<List<Training>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(training: Training)

    @Delete
    fun delete(training: Training)


}
