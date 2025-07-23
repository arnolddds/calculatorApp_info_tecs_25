package com.sobolev.calculatorapp_info_tecs_25.domain

import com.sobolev.calculatorapp_info_tecs_25.domain.model.Symbol


sealed interface CalculatorCommand {

    data object Clear : CalculatorCommand
    data object Evaluate : CalculatorCommand
    data class Input(val symbol: Symbol) : CalculatorCommand

}