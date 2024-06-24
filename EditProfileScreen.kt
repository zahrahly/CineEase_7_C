package com.example.movieui.module.home.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieui.core.route.AppRouteName
import androidx.navigation.NavHostController



@Composable
fun EditProfileScreen(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }

        Text(
            text = "Edit Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )


    Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Email", fontSize = 18.sp)
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .background(Color.LightGray.copy(alpha = 0.1f))
                    .padding(8.dp)
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Password", fontSize = 18.sp)
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .background(Color.LightGray.copy(alpha = 0.1f))
                    .padding(8.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Confirm Password", fontSize = 18.sp)
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(Color.LightGray.copy(alpha = 0.1f))
                    .padding(8.dp),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            )
        }

        Button(
            onClick = {
                if (password.text == confirmPassword.text) {
                    // Implement password update logic here
                    // For example, update password in the repository
                    navController.popBackStack("ProfileScreen", inclusive = true)
                } else {
                    // Show a message to the user that passwords do not match
                }
            },

                    modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE8B349))
        ) {
            Text(text = "Save", color = Color.White)
        }

        Button(
            onClick = {
                // Implement delete account logic here
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
        ) {
            Text(text = "Delete Account", color = Color.White)
        }
    }
}
