package com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.calculator

sealed interface CalculatorState {

    data object Initial : CalculatorState

    data class Success(val result: String) : CalculatorState

    data class Error(val expression: String) : CalculatorState


}