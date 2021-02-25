package com.brunets.pottersworld.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WizardDao {

    @Query("SELECT * FROM Wizard")
    suspend fun findAll(): List<Wizard>

    @Insert
    suspend fun insertAll(wizard: List<Wizard>)

    @Query("DELETE FROM Wizard")
    suspend fun deleteAll()
}