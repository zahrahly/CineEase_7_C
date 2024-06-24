package com.example.movieui.module.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieui.R
import com.example.movieui.module.home.model.SnackViewModel
import com.example.movieui.core.theme.Snack

@Composable
fun PaymentScreen(navController: NavController, viewModel: SnackViewModel) {
    val cart = viewModel.selectedSnacks.filter { it.quantity.value > 0 }
    val totalPayment = cart.sumOf { it.quantity.value * it.price.toCurrencyInt() }
    var selectedPaymentMethod by remember { mutableStateOf("QRIS") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "PAKUWON MALL XXI") },
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
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text(text = "Order Confirmation", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                // Display selected items
                LazyColumn(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                    items(cart) { snack ->
                        SnackSummaryItem(snack)
                    }
                }

                // Total Payment
                Text(
                    text = "Total Payment: Rp$totalPayment",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Payment Method Selector
                PaymentMethodSelector(
                    selectedMethod = selectedPaymentMethod,
                    onMethodSelected = { selectedPaymentMethod = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate("PaymentSuccessScreen") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE8B349))
                ) {
                    Text(text = "Confirm Payment", color = Color.White)
                }
            }
        }
    )
}

fun String.toCurrencyInt(): Int {
    return this.replace("Rp ", "")
        .replace(",", "")
        .toIntOrNull() ?: 0
}

@Composable
fun SnackSummaryItem(snack: Snack) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = snack.imageRes),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = snack.name, fontSize = 20.sp, color = Color.Black)
            Text(text = snack.description, fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Rp ${snack.price}", fontSize = 16.sp, color = Color.Black)
            Text(text = "Quantity: ${snack.quantity.value}", fontSize = 16.sp, color = Color.Black)
        }
    }
}

@Composable
fun PaymentMethodSelector(selectedMethod: String, onMethodSelected: (String) -> Unit) {
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
