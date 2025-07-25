package com.sobolev.calculatorapp_info_tecs_25.presentation.ui

import CalculatorScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.sobolev.calculatorapp_info_tecs_25.data.local.CalculatorDatabase
import com.sobolev.calculatorapp_info_tecs_25.data.repository.HistoryRepository
import com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.calculator.CalculatorViewModel
import com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.history.HistoryScreen
import com.sobolev.calculatorapp_info_tecs_25.presentation.ui.theme.CalculatorApp_info_tecs_25Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(
            applicationContext,
            CalculatorDatabase::class.java,
            "calculator.db"
        ).build()
        val repository = HistoryRepository(db.historyDao())


        val viewModel = CalculatorViewModel(repository)

        setContent {
            CalculatorApp_info_tecs_25Theme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "calculator",
                        modifier = Modifier.Companion.padding(bottom = innerPadding.calculateBottomPadding())
                    ) {
                        composable("calculator") {
                            CalculatorScreen(
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        composable("history") {
                            HistoryScreen(
                                viewModel = viewModel,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}