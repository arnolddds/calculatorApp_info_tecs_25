package com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobolev.calculatorapp_info_tecs_25.data.repository.HistoryRepository
import com.sobolev.calculatorapp_info_tecs_25.domain.CalculatorCommand
import com.sobolev.calculatorapp_info_tecs_25.domain.model.CalculatorState
import com.sobolev.calculatorapp_info_tecs_25.domain.model.Symbol
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mariuszgromada.math.mxparser.Expression
import kotlin.isFinite
import kotlin.let
import kotlin.takeIf
import kotlin.text.count
import kotlin.text.isDigit
import kotlin.text.isEmpty
import kotlin.text.last
import kotlin.text.replace

class CalculatorViewModel (
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CalculatorState>(CalculatorState.Initial)
    val state = _state.asStateFlow()

    val history = historyRepository.getHistory()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private var expression = ""

    fun processCommand(command: CalculatorCommand) {
        when (command) {
            CalculatorCommand.Clear -> {
                expression = ""
                _state.value = CalculatorState.Initial
            }

            CalculatorCommand.Evaluate -> {
                val result = evaluate()
                if (result != null) {
                    _state.value = CalculatorState.Success(result)
                    saveToHistory(expression, result)
                    expression = result
                } else {
                    _state.value = CalculatorState.Error(expression)
                }
            }

            is CalculatorCommand.Input -> {
                val symbol = if (command.symbol != Symbol.PARENTHESIS) {
                    command.symbol.value
                } else {
                    getCorrectParenthesis()
                }

                expression += symbol
                _state.value = CalculatorState.Input(
                    expression = expression,
                    result = evaluate() ?: ""
                )
            }
        }
    }

    private fun getCorrectParenthesis(): String {
        val openCount = expression.count { it == '(' }
        val closeCount = expression.count { it == ')' }
        return when {
            expression.isEmpty() -> "("
            expression.last().let { !it.isDigit() && it != ')' && it != 'π' } -> "("
            openCount > closeCount -> ")"
            else -> "("
        }
    }

    private fun evaluate(): String? {
        return expression
            .replace('x', '*')
            .replace(',', '.')
            .let { Expression(it) }
            .calculate()
            .takeIf { it.isFinite() }
            ?.toString()
    }

    private fun saveToHistory(expr: String, result: String) {
        viewModelScope.launch {
            historyRepository.addItem(expr, result)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            historyRepository.clearHistory()
        }
    }
}





