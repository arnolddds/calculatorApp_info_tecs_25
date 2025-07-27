package com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.calculator

import androidx.lifecycle.ViewModel
import com.sobolev.calculatorapp_info_tecs_25.NativeCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class CalculatorViewModel @Inject constructor(): ViewModel() {

    private val _state =
        MutableStateFlow<CalculatorState>(CalculatorState.Initial)
    val state = _state.asStateFlow()


    fun calculate(num1: String, num2: String, operation: String) {
        try {
            val a = num1.toDouble()
            val b = num2.toDouble()

            val result = when (operation) {
                "+" -> NativeCalculator.add(a, b)
                "-" -> NativeCalculator.subtract(a, b)
                "*" -> NativeCalculator.multiply(a, b)
                "/" -> NativeCalculator.divide(a, b)
                else -> throw IllegalArgumentException("Unknown operation")
            }

            _state.value = CalculatorState.Success(result.toString())
        } catch (e: Exception) {
            _state.value = CalculatorState.Error(e.message ?: "Ошибка ввода")
        }
    }
}





