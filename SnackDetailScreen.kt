package com.example.movieui.module.detail.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieui.R
import com.example.movieui.core.theme.Snack
import com.example.movieui.module.home.model.SnackViewModel

@Composable
fun SnackDetailScreen(navController: NavController, cinemaName: String, viewModel: SnackViewModel = viewModel()) {
    val snacks by remember { mutableStateOf(getInitialSnacks()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = cinemaName) },
                backgroundColor = Color(0xFFE8B349), // Ubah warna di sini
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painterResource(R.drawable.ic_back), contentDescription = null)
                    }
                }
            )
        },
        content = { padding ->
            SnackDetailContent(snacks, Modifier.padding(padding))
        },
        bottomBar = {
            val totalItems = snacks.sumOf { it.quantity.value }
            val totalPrice = snacks.sumOf { it.quantity.value * it.price.replace("Rp ", "").replace(",", "").toInt() }
            BottomBar(totalItems, totalPrice) {
                viewModel.updateSelectedSnacks(snacks)
                navController.navigate("PaymentScreen")
            }
        }
    )
}

@Composable
fun SnackDetailContent(snacks: List<Snack>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        items(snacks) { snack ->
            SnackItem(snack)
        }
    }
}

@Composable
fun SnackItem(snack: Snack) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
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
            Column(modifier = Modifier.weight(1f)) {
                Text(text = snack.name, fontSize = 20.sp, color = Color.Black)
                Text(text = snack.description, fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = snack.price, fontSize = 16.sp, color = Color.Black)
            }
            QuantitySelector(snack)
        }
    }
}

@Composable
fun QuantitySelector(snack: Snack) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            if (snack.quantity.value > 0) snack.quantity.value--
        }) {
            Icon(painterResource(R.drawable.ic_min), contentDescription = null)
        }
        Text(
            text = snack.quantity.value.toString(),
            fontSize = 20.sp,
            modifier = Modifier.width(24.dp),
            textAlign = TextAlign.Center
        )
        IconButton(onClick = {
            snack.quantity.value++
        }) {
            Icon(painterResource(R.drawable.ic_plus), contentDescription = null)
        }
    }
}

@Composable
fun BottomBar(itemCount: Int, totalPayment: Int, onProceedToPayment: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text(
            text = "Total: $itemCount item(s) Rp. ${totalPayment.formatAsCurrency()}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onProceedToPayment,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE8B349))
        ) {
            Text(text = "Continue", color = Color.White)
        }
    }
}

// Helper function to get initial snacks list
fun getInitialSnacks(): List<Snack> {
    return listOf(
        Snack("1", "Popcorn Paket 1 (M)", "Popcorn + Cola Paket 1 Medium", "Rp 65,000", R.drawable.paket1),
        Snack("2", "Popcorn Paket 2 (M)", "Popcorn + Cola Paket 2 Medium", "Rp 65,000", R.drawable.paket2),
        Snack("3", "Popcorn Paket 3 (M)", "Popcorn + Cola Paket 3 Medium", "Rp 65,000", R.drawable.paket3),
        Snack("4", "Soda Coca Cola (L)", "Soda Coca Cola Large", "Rp 30,000", R.drawable.paket4),
        Snack("5", "Soda Sprite (L)", "Soda Sprite Large", "Rp 30,000", R.drawable.paket5),
        Snack("6", "Burger Paket 1 (M)", "Burger Paket 1 Medium", "Rp 50,000", R.drawable.paket6)
    )
}

// Helper extension function to format Int as currency
fun Int.formatAsCurrency(): String = "%,d".format(this).replace(",", ".")
