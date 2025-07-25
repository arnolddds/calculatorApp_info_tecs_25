package com.sobolev.calculatorapp_info_tecs_25.presentation.navigation

import CalculatorScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sobolev.calculatorapp_info_tecs_25.presentation.ui.screens.history.HistoryScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "calculator",
            modifier = Modifier.Companion.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable("calculator") {
                CalculatorScreen(
                    onHistoryClick = {navController.navigate("history")}
                )
            }
            composable("history") {
                HistoryScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}