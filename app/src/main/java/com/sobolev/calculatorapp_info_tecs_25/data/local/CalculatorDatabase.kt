package com.sobolev.calculatorapp_info_tecs_25.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sobolev.calculatorapp_info_tecs_25.domain.model.HistoryItem

@Database(entities = [HistoryItem::class], version = 1)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}