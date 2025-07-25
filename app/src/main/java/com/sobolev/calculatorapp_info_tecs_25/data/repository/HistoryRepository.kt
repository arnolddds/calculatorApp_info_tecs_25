package com.sobolev.calculatorapp_info_tecs_25.data.repository

import com.sobolev.calculatorapp_info_tecs_25.data.local.HistoryDao
import com.sobolev.calculatorapp_info_tecs_25.domain.model.HistoryItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val dao: HistoryDao
) {
    fun getHistory(): Flow<List<HistoryItem>> = dao.getAll()

    suspend fun addItem(expression: String, result: String) {
        dao.insert(HistoryItem(expression = expression, result = result))
    }

    suspend fun clearHistory() = dao.clear()
}