package com.brunets.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import entities.WizardDomain

@Entity(tableName = "wizard_cache")
data class WizardCache(
    val name: String,
    val photo: String,
    val age: Int?,
    val description: String?
) {
    @PrimaryKey(autoGenerate = true)
    var wid: Int = 0

    companion object {
        fun convetData(wizardDomain: WizardDomain) =
            WizardCache(wizardDomain.name, wizardDomain.photo, wizardDomain.age, wizardDomain.description)
    }
}

