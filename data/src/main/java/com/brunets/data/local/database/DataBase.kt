package com.brunets.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brunets.data.local.model.WizardCache

@Database(entities = [WizardCache::class],version = 3)
abstract class DataBase: RoomDatabase() {
    abstract val dao: WizardDao
}