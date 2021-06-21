package com.brunets.pottersworld.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import entities.WizardData

@Entity
data class Wizard(val name: String, val photo: String, val age: Int?,val description: String?){
    @PrimaryKey(autoGenerate = true)
    var wid: Int = 0
companion object{
    fun convetData(wizardData: WizardData) = Wizard(wizardData.name, wizardData.photo, wizardData.age, wizardData.description)
}
}

