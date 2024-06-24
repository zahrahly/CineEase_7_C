package com.example.movieui.core.theme

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Snack(
    val id: String,
    val name: String,
    val description: String,
    val price: String,  // Format: "Rp xx,xxx"
    val imageRes: Int,  // Resource ID for the image
    val quantity: MutableState<Int> = mutableStateOf(0) // Default quantity is 0
)
