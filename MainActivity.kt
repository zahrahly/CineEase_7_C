package com.example.movieui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieui.core.route.AppRoute
import com.example.movieui.core.route.BottomNavigationBar
import com.example.movieui.core.theme.MovieUITheme
import com.example.movieui.module.detail.presentation.SnackDetailScreen
import com.example.movieui.module.home.model.SnackViewModel
import com.example.movieui.module.transaction.PaymentScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Mengatur warna status bar dan navigation bar menjadi transparan
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = true,
            )
            systemUiController.setNavigationBarColor(
                color = Color.Transparent,
                darkIcons = true,
            )
            @Composable
            fun MainScreen() {
                val navController = rememberNavController()
                val snackViewModel: SnackViewModel = viewModel()

                NavHost(navController = navController, startDestination = "SnackDetailScreen") {
                    composable("SnackDetailScreen") {
                        SnackDetailScreen(navController = navController, cinemaName = "Your Cinema Name", viewModel = snackViewModel)
                    }
                    composable("PaymentScreen") {
                        PaymentScreen(navController = navController, viewModel = snackViewModel)
                    }
                }
            }

            @Composable
            fun DefaultPreview() {
                val navController = rememberNavController()
                AppRoute.GenerateRoute(navController = navController)
            }
            // Menerapkan tema aplikasi
            MovieUITheme {
                // Membuat NavController untuk navigasi
                val navController = rememberNavController()

                // Scaffold untuk menyusun layout utama
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    },
                    backgroundColor = Color.White // Warna latar belakang aplikasi
                ) { innerPadding ->
                    // Menjalankan rute navigasi dengan padding untuk menghindari overlap
                    AppRoute.GenerateRoute(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


