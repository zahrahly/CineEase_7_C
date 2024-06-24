package com.example.movieui.module.transaction

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieui.R
import com.example.movieui.core.theme.Snack

@Composable
fun PaymentSuccessScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Payment Success") },
                backgroundColor = Color(0xFFE8B349),
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painterResource(R.drawable.ic_back), contentDescription = null)
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.qr_code), // Replace with your QR code resource
                    contentDescription = "QR Code",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Payment Successful!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE8B349),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        // Navigate to the HomeScreen and clear the back stack
                        navController.navigate("home") {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE8B349)) // Updated color
                ) {
                    Text(text = "Back to Home", color = Color.White)
                }
            }
        }
    )
}

fun saveTransaction(cart: List<Snack>, totalPayment: Int, paymentMethod: String) {
    // Implementasi penyimpanan transaksi ke dalam database atau riwayat lokal
}
