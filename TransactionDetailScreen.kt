package com.example.movieui.module.transaction

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TransactionDetailScreen(
    navController: NavHostController,
    selectedDate: String?,
    selectedTime: String?,
    selectedCinema: String?,
    movieTitle: String?
) {
    Log.d("TransactionDetailScreen", "Navigating to TransactionDetailScreen with Date: $selectedDate, Time: $selectedTime, Cinema: $selectedCinema, Movie: $movieTitle")

    // Validation checks
    if (selectedDate == null || selectedTime == null || selectedCinema == null || movieTitle == null) {
        Log.e("TransactionDetailScreen", "Null parameter(s) detected")
        navController.popBackStack()
        return
    }

    var selectedPaymentMethod by remember { mutableStateOf("QRIS") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = selectedCinema) },
                backgroundColor = Color(0xFFE8B349), // Updated color
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text(text = "Transaction Details", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                // Movie and cinema details
                Text(text = "Movie: $movieTitle", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Cinema: $selectedCinema", fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Date: $selectedDate", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Time: $selectedTime", fontSize = 16.sp)

                Spacer(modifier = Modifier.height(16.dp))

                // Payment Method Selector
                TransactionPaymentMethodSelector(
                    selectedMethod = selectedPaymentMethod,
                    onMethodSelected = { selectedPaymentMethod = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Confirm Payment Button
                Button(
                    onClick = { navController.navigate("transaction_confirmation") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE8B349)) // Updated color
                ) {
                    Text(text = "Confirm Payment", color = Color.White)
                }
            }
        }
    )
}

@Composable
fun TransactionPaymentMethodSelector(selectedMethod: String, onMethodSelected: (String) -> Unit) {
    Column {
        Text(
            text = "Choose Payment Method",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val paymentMethods = listOf("OVO", "Shopeepay", "Gopay")

        paymentMethods.forEach { method ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onMethodSelected(method) }
            ) {
                RadioButton(
                    selected = (selectedMethod == method),
                    onClick = { onMethodSelected(method) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = method, fontSize = 16.sp, color = Color.Black)
            }
        }
    }
}
