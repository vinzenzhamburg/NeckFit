package com.example.neckfit.data.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Training(

    @PrimaryKey
    val id : Int,
    val image : String,
    val description : String,
    var favorite : Boolean = false
)
