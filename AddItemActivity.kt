package com.example.admincineease

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admincineease.databinding.ActivityAddItemBinding

class AddItemActivity : AppCompatActivity() {

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    private lateinit var binding: ActivityAddItemBinding
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle click event to pick image from gallery
        binding.buttonSelectImage.setOnClickListener {
            openGallery()
        }

        binding.imageViewBack.setOnClickListener {
            onBackPressed() // Go back to previous activity
        }

        // Handle click event to add item
        binding.buttonAddItem.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val description = binding.editTextDescription.text.toString()
            val price = binding.editTextPrice.text.toString().toDoubleOrNull() ?: 0.0

            // Validate input fields
            if (selectedImageUri == null || name.isEmpty() || description.isEmpty() || price <= 0) {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save item to database or perform other operations
            saveItem(name, description, price)
            finish() // Finish activity after saving item
        }
    }

    // Function to open gallery and select image
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    // Handle result from gallery image selection
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            // Display selected image in ImageView
            binding.imageView.setImageURI(selectedImageUri)
        }
    }

    // Function to save item details
    private fun saveItem(name: String, description: String, price: Double) {
        // Implement your logic to save the item to database or perform other operations
        // Example: You can use Room, SQLiteOpenHelper, Firestore, etc. for database operations
        // Here's a placeholder code:
        val imageUriString = selectedImageUri?.toString() ?: ""
        val newItem = Item(imageUriString, name, description, price)
        // Save newItem to your database or perform other operations
        // This is where you should handle the actual saving of data
    }
}