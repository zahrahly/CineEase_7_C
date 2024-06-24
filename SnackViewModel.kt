package com.example.movieui.module.home.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movieui.core.theme.Snack

class SnackViewModel : ViewModel() {
    val selectedSnacks = mutableStateListOf<Snack>()

    fun updateSelectedSnacks(snacks: List<Snack>) {
        selectedSnacks.clear()
        selectedSnacks.addAll(snacks.filter { it.quantity.value > 0 })
    }
    fun saveTransaction(cart: List<Snack>, totalPayment: Int, paymentMethod: String) {
        // Implement your transaction saving logic here
    }
}



