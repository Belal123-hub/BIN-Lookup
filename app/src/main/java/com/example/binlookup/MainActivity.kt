package com.example.binlookup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.binlookup.presentation.navigation.AppNavHost
import com.example.binlookup.presentation.ui.theme.BINLookupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BINLookupTheme {
                AppNavHost()
                }
            }
        }
    }
