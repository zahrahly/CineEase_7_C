package com.example.movieui.module.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieui.R // Replace with your actual package name
import com.example.movieui.core.route.AppRouteName

@Composable
fun ProfileScreen(navController: NavController) {
    val userEmail = remember { mutableStateOf(TextFieldValue("zahrahaliyahh@gmail.com")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile title
        Text(text = "Profile", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Profile icon
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile Icon",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 24.dp),
            tint = Color.Black
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Email", fontSize = 18.sp, color = Color.Gray)
            BasicTextField(
                value = userEmail.value,
                onValueChange = { userEmail.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }


        // Edit Profile button
        ProfileButton(
            text = "Edit Profile",
            iconResourceId = R.drawable.ic_profile, // Replace with your edit profile icon resource ID
            onClick = { navController.navigate(AppRouteName.EditProfile) }
        )


        // Log Out button with custom drawable
        ProfileButton(
            text = "Log Out",
            iconResourceId = R.drawable.logout, // Replace with your logout icon resource ID
            onClick = { navController.navigate(AppRouteName.Login) } // Navigate to login screen on log out
        )
    }
}

@Composable
fun ProfileButton(text: String, iconResourceId: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // Use Image with painterResource to load custom drawable
            Image(
                painter = painterResource(id = iconResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp) // Increased size for better alignment with text
                    .padding(end = 16.dp)
            )
            Text(text = text, fontSize = 18.sp, color = Color.Black)
        }
    }
}
