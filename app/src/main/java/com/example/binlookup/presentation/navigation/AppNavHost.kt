package com.example.binlookup.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.binlookup.presentation.ui.screen.BinLookupScreen
import com.example.binlookup.presentation.ui.screen.HistoryScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "BinLookup") {
        composable("BinLookup") {
            BinLookupScreen(onNavigateToHistory = { navController.navigate("history") })
        }
        composable("history") {
            HistoryScreen(onBack = {navController.popBackStack()})
        }
    }
}
