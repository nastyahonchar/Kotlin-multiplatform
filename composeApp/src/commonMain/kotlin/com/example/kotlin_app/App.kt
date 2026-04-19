package com.example.kotlin_app

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

import com.example.kotlin_app.ui.theme.AppTheme
import com.example.kotlin_app.ui.screens.AppNavigation

@Composable
@Preview
fun App() {
    AppTheme {
        AppNavigation()
    }
}