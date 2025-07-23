package com.sobolev.calculatorapp_info_tecs_25.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val expression: String,
    val result: String
)

