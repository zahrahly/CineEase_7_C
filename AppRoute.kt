package com.example.movieui.core.route

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieui.module.detail.presentation.DetailScreen
import com.example.movieui.module.detail.presentation.SnackDetailScreen
import com.example.movieui.module.home.model.SnackViewModel
import com.example.movieui.module.home.model.nowPlayingMovie
import com.example.movieui.module.home.presentation.HomeScreen
import com.example.movieui.module.home.presentation.ProfileScreen
import com.example.movieui.module.home.presentation.SnackScreen
import com.example.movieui.module.home.profile.EditProfileScreen
import com.example.movieui.module.seat_selector.presentation.SeatSelectorScreen
import com.example.movieui.module.transaction.PaymentScreen
import com.example.movieui.module.transaction.PaymentSuccessScreen
import com.example.movieui.module.transaction.TransactionConfirmationScreen
import com.example.movieui.module.transaction.TransactionDetailScreen

object AppRoute {
    @Composable
    fun GenerateRoute(
        navController: NavHostController,
        modifier: Modifier = Modifier
    ) {
        val snackViewModel: SnackViewModel = viewModel()

        NavHost(
            navController = navController,
            startDestination = AppRouteName.Login,
            modifier = modifier
        ) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("transaction_confirmation") {
                TransactionConfirmationScreen(navController)
            }
            composable(AppRouteName.Profile) {
                ProfileScreen(navController)
            }
            composable(AppRouteName.EditProfile) {
                EditProfileScreen(navController)
            }
            composable(AppRouteName.Login) {
                LoginScreen(navController = navController)
            }
            composable(AppRouteName.Snack) {
                SnackScreen(navController = navController)
            }
            composable("${AppRouteName.Detail}/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                val movie = nowPlayingMovie.firstOrNull { it.id == id }
                movie?.let {
                    DetailScreen(navController = navController, movie = it)
                }
            }
            composable(AppRouteName.SeatSelector) {
                SeatSelectorScreen(navController = navController, selectedDate = "", selectedTime = "")
            }
            composable(
                route = "transaction_detail/{selectedDate}/{selectedTime}/{selectedCinema}/{movieTitle}"
            ) { backStackEntry ->
                val selectedDate = backStackEntry.arguments?.getString("selectedDate")
                val selectedTime = backStackEntry.arguments?.getString("selectedTime")
                val selectedCinema = backStackEntry.arguments?.getString("selectedCinema")
                val movieTitle = backStackEntry.arguments?.getString("movieTitle")

                TransactionDetailScreen(
                    navController = navController,
                    selectedDate = selectedDate,
                    selectedTime = selectedTime,
                    selectedCinema = selectedCinema,
                    movieTitle = movieTitle
                )
            }
            composable("SnackDetailScreen/{cinemaName}") { backStackEntry ->
                val cinemaName = backStackEntry.arguments?.getString("cinemaName") ?: "Default Cinema"
                SnackDetailScreen(navController = navController, cinemaName = cinemaName, viewModel = snackViewModel)
            }
            composable("PaymentScreen") {
                PaymentScreen(navController = navController, viewModel = snackViewModel)
            }
            composable("PaymentSuccessScreen") {
                PaymentSuccessScreen(navController = navController)
            }
            composable(AppRouteName.TransactionConfirmation) {
                TransactionConfirmationScreen(navController = navController)
            }
        }
    }
}
