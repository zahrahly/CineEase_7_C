package com.example.admincineease.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.admincineease.R
import com.example.admincineease.data.OrderItem

class OrderItemsAdapter(private val items: List<OrderItem>) :
    RecyclerView.Adapter<OrderItemsAdapter.OrderItemViewHolder>() {

    inner class OrderItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemDescription: TextView = itemView.findViewById(R.id.itemDescription)
        private val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)

        fun bind(item: OrderItem) {
            itemImage.setImageResource(item.imageResource)
            itemName.text = item.name
            itemDescription.text = item.description
            itemPrice.text = "Rp ${item.price}" // Menggunakan item.price dari OrderItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_detail, parent, false)
        return OrderItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
