package com.example.movieui.module.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieui.R
import com.example.movieui.core.theme.Cinema
import java.net.URLEncoder

@Composable
fun SnackScreen(navController: NavController) {
    val cinemas = listOf(
        Cinema("CIPUTRA WORLD XXI", "Jam operasi F&B 11:00 - 20:40"),
        Cinema("DELTA XXI", "Jam operasi F&B 11:30 - 21:30"),
        Cinema("GALAXY XXI", "Jam operasi F&B 11:30 - 21:00"),
        Cinema("GRAND CITY XXI", "Jam operasi F&B 11:30 - 21:15"),
        Cinema("LENMARC XXI", "Jam operasi F&B 11:30 - 21:50"),
        Cinema("PAKUWON CITY XXI", "Jam operasi F&B 11:00 - 22:00"),
        Cinema("PAKUWON MALL XXI", "Jam operasi F&B 11:30 - 21:30"),
        Cinema("PTC XXI", "Jam operasi F&B 09:30 - 21:30"),
        Cinema("ROYAL XXI", "Jam operasi F&B 11:30 - 21:30"),
        Cinema("TRANS ICON MALL XXI", "Jam operasi F&B 11:30 - 21:00"),
        Cinema("TRANSMART NGAGEL XXI", "Jam operasi F&B 10:00 - 20:45")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cinemas) { cinema ->
            CinemaItem(cinema, navController)
        }
    }
}

@Composable
fun CinemaItem(cinema: Cinema, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val encodedName = URLEncoder.encode(cinema.name, "UTF-8")
                navController.navigate("SnackDetailScreen/$encodedName")
            },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo XXI
            Image(
                painter = painterResource(id = R.drawable.ic_xxi_logo3),
                contentDescription = "XXI Logo",
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 8.dp)
            )
            // Nama dan Jam Operasi Bioskop
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = cinema.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = cinema.operatingHours,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
