package com.example.movieui.module.detail.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieui.R
import com.example.movieui.core.route.AppRouteName
import com.example.movieui.core.theme.Gray
import com.example.movieui.core.theme.Yellow
import com.example.movieui.module.home.model.MovieModel
import com.google.accompanist.flowlayout.FlowRow
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DetailScreen(
    navController: NavHostController,
    movie: MovieModel,
) {
    val tabTitles = listOf("Synopsis", "Jadwal")
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            // No floating action button on this screen
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Back Button and Title
            TopAppBar(
                title = { Text(text = "Movie Detail", style = MaterialTheme.typography.h6) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back Button")
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = AppBarDefaults.TopAppBarElevation
            )

            // Tab Row
            TabRow(
                selectedTabIndex = selectedTab,
                backgroundColor = Color.White,
                contentColor = Yellow
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            // Content based on the selected tab
            when (selectedTab) {
                0 -> SynopsisTab(movie = movie)
                1 -> JadwalTab(movie = movie, navController = navController)
            }
        }
    }
}

@Composable
fun SynopsisTab(movie: MovieModel) {
    val scrollState = rememberScrollState()  // Menciptakan state untuk scroll

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)  // Menggunakan scrollState pada Column
    ) {
        // Row yang saat ini tidak memiliki konten. Bisa dihapus jika tidak diperlukan.
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp, vertical = 8.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Konten Row dapat ditambahkan di sini jika diperlukan
        }
        Row(
            modifier = Modifier
                .height(320.dp)
                .padding(horizontal = 24.dp)
        ) {
            Image(
                painter = painterResource(id = movie.assetImage),
                contentDescription = "Movie Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .weight(0.7f)
                    .height(320.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.width(24.dp))
            Column(
                modifier = Modifier
                    .height(320.dp)
                    .weight(0.3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                MovieInfo(
                    painterResourceId = R.drawable.baseline_videocam,
                    title = "Genre",
                    value = movie.type
                )
                MovieInfo(
                    painterResourceId = R.drawable.baseline_access_time_filled,
                    title = "Duration",
                    value = movie.duration
                )
                MovieInfo(
                    painterResourceId = R.drawable.baseline_stars,
                    title = "Rating",
                    value = movie.rating
                )
            }
        }
        Text(
            movie.title, style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                horizontal = 24.dp, vertical = 16.dp
            )
        )
        Text(
            "Synopsis", style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(
                horizontal = 24.dp
            )
        )
        Text(
            movie.synopsis, style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(
                horizontal = 24.dp, vertical = 16.dp
            )
        )
        Spacer(modifier = Modifier.height(64.dp))
    }
}



@Composable
fun JadwalTab(movie: MovieModel, navController: NavHostController) {
    val dateScrollState = rememberScrollState()
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }
    val selectedTime = remember { mutableStateOf<String?>(null) }
    val selectedCinema = remember { mutableStateOf<String?>(null) }

    val cinemas = listOf(
        Cinema("CIPUTRA WORLD XXI", "12:00, 13:00, 15:00, 16:00, 18:30, 19:30, 21:05"),
        Cinema("TRANSMART RUNGKUT", "12:00, 13:00, 15:00, 16:00, 18:30, 19:30, 21:05"),
        Cinema("PAKUWON CITY", "12:00, 13:00, 15:00, 16:00, 18:30, 19:30, 21:05"),
        Cinema("TUNJUNGAN PLAZA", "12:00, 13:00, 15:00, 16:00, 18:30, 19:30, 21:05"),
        Cinema("PAKUWON MALL", "12:00, 13:00, 15:00, 16:00, 18:30, 19:30, 21:05")
    )
    val expandedCinema = remember { mutableStateOf<Cinema?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Date selection
        Row(
            modifier = Modifier.horizontalScroll(dateScrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val today = LocalDate.now()
            for (i in 0..16) {
                val date = today.plusDays(i.toLong())
                DateComp(
                    date = date,
                    isSelected = selectedDate.value == date,
                    onClick = { selectedDate.value = it }
                )
            }
        }

        // Cinema schedules
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            cinemas.forEach { cinema ->
                CinemaScheduleCard(
                    cinema = cinema,
                    expanded = expandedCinema.value == cinema,
                    onToggleExpand = { expandedCinema.value = if (expandedCinema.value == cinema) null else cinema },
                    onTimeSelect = { time ->
                        selectedTime.value = time
                        selectedCinema.value = cinema.name // Store the selected cinema
                    },
                    selectedTime = selectedTime.value
                )
            }
        }

        // Continue Button
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = {
                val selectedDateStr = selectedDate.value?.toString() ?: ""
                val selectedTimeStr = selectedTime.value ?: ""
                val selectedCinemaStr = selectedCinema.value ?: ""

                if (selectedDateStr.isNotEmpty() && selectedTimeStr.isNotEmpty() && selectedCinemaStr.isNotEmpty()) {
                    navController.navigate(
                        "transaction_detail/$selectedDateStr/$selectedTimeStr/$selectedCinemaStr/${movie.title}"
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Yellow),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Continue")
        }
    }
}



@Composable
fun CinemaScheduleCard(
    cinema: Cinema,
    expanded: Boolean,
    onToggleExpand: () -> Unit,
    onTimeSelect: (String) -> Unit,
    selectedTime: String?
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 14.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.clickable(onClick = onToggleExpand)
        ) {
            Text(
                text = cinema.name,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(14.dp)
            )

            if (expanded) {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    mainAxisSpacing = 8.dp,
                    crossAxisSpacing = 8.dp
                ) {
                    cinema.operatingHours.split(", ").forEach { time ->
                        Button(
                            onClick = { onTimeSelect(time) },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (selectedTime == time) Yellow else Yellow.copy(alpha = 0.15f)
                            ),
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text(text = time, style = MaterialTheme.typography.body1)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun DateComp(
    date: LocalDate,
    isSelected: Boolean = false,
    onClick: (LocalDate) -> Unit = {},
) {
    val color = when {
        isSelected -> Yellow
        else -> Yellow.copy(alpha = 0.15f)
    }
    val textBg = when {
        isSelected -> Color.White
        else -> Color.Transparent
    }
    Surface(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick(date)
            }, shape = RoundedCornerShape(16.dp), color = color
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                style = MaterialTheme.typography.caption
            )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(textBg)
                    .padding(4.dp),
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}

@Composable
fun MovieInfo(
    @DrawableRes painterResourceId: Int,
    title: String,
    value: String,
) {
    Column(
        modifier = Modifier
            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable { }
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = painterResourceId),
            contentDescription = title,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = title, style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, style = MaterialTheme.typography.subtitle1)
    }
}

data class Cinema(
    val name: String,
    val operatingHours: String
)