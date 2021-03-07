package com.brunets.pottersworld.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wizard(val name: String, val photo: String, val age: Int?,val description: String?){
    @PrimaryKey(autoGenerate = true)
    var wid: Int = 0
}