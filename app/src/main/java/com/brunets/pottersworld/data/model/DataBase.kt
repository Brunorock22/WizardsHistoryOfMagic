package com.brunets.pottersworld.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Wizard::class],version = 1)
abstract class DataBase: RoomDatabase() {
    abstract val dao: WizardDao
}