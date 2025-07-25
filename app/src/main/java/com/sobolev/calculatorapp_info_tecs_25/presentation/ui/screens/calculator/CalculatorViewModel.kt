package com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobolev.calculatorapp_info_tecs_25.NativeCalculator
import com.sobolev.calculatorapp_info_tecs_25.data.repository.HistoryRepository
import com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.calculator.CalculatorCommand
import com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.calculator.CalculatorState
import com.sobolev.calculatorapp_info_tecs_25.domain.model.Symbol
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CalculatorViewModel @Inject constructor (
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
        val expr = expression
            .replace('x', '*')
            .replace(',', '.')
            .replace('×', '*')
            .replace('÷', '/')

        val match =
            Regex("""(-?\d+(?:\.\d+)?)([+\-*/])(-?\d+(?:\.\d+)?)""").find(expr) ?: return null

        val (lhs, op, rhs) = match.destructured
        val a = lhs.toDoubleOrNull() ?: return null
        val b = rhs.toDoubleOrNull() ?: return null

        val result = when (op) {
            "+" -> NativeCalculator.add(a, b)
            "-" -> NativeCalculator.subtract(a, b)
            "*" -> NativeCalculator.multiply(a, b)
            "/" -> NativeCalculator.divide(a, b)
            "%" -> NativeCalculator.modulo(a,b)
            else -> return null
        }

        return result.takeIf { it.isFinite() }?.toString()
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





