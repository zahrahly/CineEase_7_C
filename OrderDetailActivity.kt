package com.example.admincineease

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admincineease.adapter.OrderItemsAdapter
import com.example.admincineease.data.OrderItem
import com.example.admincineease.databinding.ActivityOrderDetailBinding

class OrderDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Arrow Back
        binding.imageViewBack.setOnClickListener {
            onBackPressed()
        }

        // Setup RecyclerView (Assuming you have order items data)
        val orderItems = listOf(
            OrderItem(R.drawable.popcorn, "Popcorn Paket 1 (M)", "Popcorn + Soda Coca Cola (M)", 65000.0),
            OrderItem(R.drawable.big_popcorn, "Popcorn Paket 2 (L)", "Popcorn Large", 65000.0),
            OrderItem(R.drawable.popcorn, "Popcorn Paket 3 (M)", "Popcorn Medium", 65000.0),
            OrderItem(R.drawable.godzilla, "Tiket Godzilla", "Delta Plaza Cinema XXI", 30000.0),
            OrderItem(R.drawable.pemandijenazah, "Tiket Pemandi Jenazah", "Pakuwon City Cinema XXI", 30000.0),
            OrderItem(R.drawable.avatar, "Tiket Avatar", "Grand City Cinema XXI", 30000.0),
            OrderItem(R.drawable.sonic, "Tiket Sonic", "Tunjungan Plaza 3 Cinema XXI", 30000.0),
            OrderItem(R.drawable.agaklaen, "Ticket Agak Laen", "Galaxy Mall Cinema XXI", 30000.0),
            OrderItem(R.drawable.godzilla, "Tiket Godzilla", "Royal Plaza Cinema XXI", 30000.0),
            OrderItem(R.drawable.sonic, "Tiket Sonic", "BG Junction Cinema XXI", 30000.0),
            // Tambahkan lebih banyak item sesuai kebutuhan
        )

        // Setup RecyclerView
        binding.orderItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.orderItemsRecyclerView.adapter = OrderItemsAdapter(orderItems)
    }
}
