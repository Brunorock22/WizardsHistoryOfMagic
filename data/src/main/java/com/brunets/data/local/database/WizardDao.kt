package com.brunets.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.brunets.data.local.model.WizardCache
import kotlinx.coroutines.flow.Flow

@Dao
interface WizardDao {

    @Query("SELECT * FROM wizard_cache")
    fun findAll(): Flow<List<WizardCache>>

    @Insert
    suspend fun insertAll(wizard: List<WizardCache>)

    @Query("DELETE FROM wizard_cache")
    suspend fun deleteAll()
}