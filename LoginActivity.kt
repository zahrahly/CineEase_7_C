package com.example.admincineease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLoginAdmin = findViewById<Button>(R.id.buttonLoginAdmin)
        val textViewLoginUser = findViewById<TextView>(R.id.textViewLoginUser)

        buttonLoginAdmin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Handle admin login
                Toast.makeText(this, "Login as Admin", Toast.LENGTH_SHORT).show()
                // Start Main Activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }

        textViewLoginUser.setOnClickListener {
            // Handle user login
            Toast.makeText(this, "Login as User", Toast.LENGTH_SHORT).show()
            // Start User Activity
            // startActivity(Intent(this, UserActivity::class.java))
        }
    }
}
