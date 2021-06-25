package com.brunets.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.brunets.data.local.model.WizardCache

@Dao
interface WizardDao {

    @Query("SELECT * FROM WizardCache")
    suspend fun findAll(): List<WizardCache>

    @Insert
    suspend fun insertAll(wizard: List<WizardCache>)

    @Query("DELETE FROM WizardCache")
    suspend fun deleteAll()
}