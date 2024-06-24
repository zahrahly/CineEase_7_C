import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieui.R
import com.example.movieui.core.route.AppRouteName

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top text
        Text(text = "CineEase", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))

        // Centered Cinema Image
        Image(
            painter = painterResource(id = R.drawable.ic_cinema),
            contentDescription = null,
            modifier = Modifier.size(200.dp).padding(bottom = 16.dp)
        )

        // Login Text
        Text(text = "Login", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Email Input
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

        // Password Input
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Password", fontSize = 18.sp)
            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(Color.LightGray.copy(alpha = 0.1f))
                    .padding(8.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            )
            Image(
                painter = painterResource(if (passwordVisible) R.drawable.ic_visibility_on else R.drawable.ic_visibility_off),
                contentDescription = "Toggle password visibility",
                modifier = Modifier
                    .clickable { passwordVisible = !passwordVisible }
                    .padding(top = 8.dp)
                    .size(24.dp)
            )
        }

        // Login as User Button
        Button(
            onClick = { navController.navigate(AppRouteName.Profile) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE8B349))
        ) {
            Text(text = "Login as User", color = Color.White)
        }

        // Login as Admin Button
        Text(
            text = "Login as Admin",
            color = Color(0xFFE8B349),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable {

                }
        )
    }
}
