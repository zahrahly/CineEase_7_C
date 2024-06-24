package com.example.movieui.module.seat_selector.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieui.core.route.AppRouteName
import com.example.movieui.core.theme.Gray
import com.example.movieui.core.theme.LightGray
import com.example.movieui.core.theme.Yellow
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


@Composable
fun SeatSelectorScreen(
    navController: NavController,
    selectedDate: String,
    selectedTime: String
) {
    val selectedSeat = remember { mutableStateListOf<String>() }

    Scaffold(
        backgroundColor = LightGray
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TopAppBar(
                title = { Text("Select Seat", style = MaterialTheme.typography.h6) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back Button")
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = AppBarDefaults.TopAppBarElevation
            )

            // Screen layout
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = Yellow, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Screen", style = MaterialTheme.typography.body2.copy(color = Color.White))
            }

            // Seat map
            for (i in 1..6) {
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    for (j in 1..8) {
                        val seatNumber = "${(64 + i).toChar()}$j"
                        SeatComp(
                            isEnabled = i != 6,
                            isSelected = selectedSeat.contains(seatNumber),
                            seatNumber = seatNumber
                        ) { selected, seat ->
                            if (selected) {
                                selectedSeat.remove(seat)
                            } else {
                                selectedSeat.add(seat)
                            }
                        }

                        if (j != 8) Spacer(modifier = Modifier.width(if (j == 4) 16.dp else 8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Seat legend
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SeatComp(isEnabled = false)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Reserved", style = MaterialTheme.typography.caption)

                Spacer(modifier = Modifier.width(16.dp))

                SeatComp(isEnabled = true, isSelected = true)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Selected", style = MaterialTheme.typography.caption)

                Spacer(modifier = Modifier.width(16.dp))

                SeatComp(isEnabled = true, isSelected = false)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Available", style = MaterialTheme.typography.caption)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Total price section
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Select Seat", style = MaterialTheme.typography.subtitle1)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Total Price", style = MaterialTheme.typography.subtitle1)
                            Text("Rp ${selectedSeat.size * 30000}", style = MaterialTheme.typography.subtitle1)
                        }

                        // Continue button
                        Button(
                            onClick = {
                                val selectedSeatsStr = selectedSeat.joinToString(",")
                                if (selectedSeatsStr.isNotEmpty()) {
                                    navController.navigate("${AppRouteName.TransactionDetail}/$selectedDate/$selectedTime/$selectedSeatsStr/${selectedSeat.size * 30000}")
                                }
                            },
                            modifier = Modifier
                                .wrapContentWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Yellow),
                            shape = RoundedCornerShape(32.dp)
                        ) {
                            Text("Continue")
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun SeatComp(
    isEnabled: Boolean = false,
    isSelected: Boolean = false,
    seatNumber: String = "",
    onClick: (Boolean, String) -> Unit = { _, _ -> },
) {
    val seatColor = when {
        !isEnabled -> Color.Gray
        isSelected -> Yellow
        else -> Color.White
    }

    val textColor = when {
        isSelected -> Color.White
        else -> Color.Black
    }

    Box(
        modifier = Modifier
            .size(32.dp)
            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(color = seatColor)
            .clickable(enabled = isEnabled) {
                onClick(isSelected, seatNumber)
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            seatNumber,
            style = MaterialTheme.typography.caption.copy(color = textColor),
        )
    }
}