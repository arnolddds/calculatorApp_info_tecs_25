package com.sobolev.calculatorapp_info_tecs_25.data.local

import androidx.room.*
import com.sobolev.calculatorapp_info_tecs_25.domain.model.HistoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: HistoryItem)

    @Query("SELECT * FROM history ORDER BY id DESC")
    fun getAll(): Flow<List<HistoryItem>>

    @Query("DELETE FROM history")
    suspend fun clear()
}
