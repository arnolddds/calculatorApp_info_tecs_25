package com.sobolev.calculatorapp_info_tecs_25.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sobolev.calculatorapp_info_tecs_25.presentation.navigation.NavGraph
import com.sobolev.calculatorapp_info_tecs_25.presentation.ui.theme.CalculatorApp_info_tecs_25Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorApp_info_tecs_25Theme {
                NavGraph()
            }
        }
    }
}