package com.example.admincineease.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admincineease.OnItemDeleteListener
import com.example.admincineease.OrderDetailActivity
import com.example.admincineease.data.InventoryItem
import com.example.admincineease.databinding.ItemInventarisBinding

class InventoryAdapter(
    private val items: MutableList<InventoryItem>,
    private val listener: OnItemDeleteListener
) : RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {

    private lateinit var context: Context

    inner class InventoryViewHolder(private val binding: ItemInventarisBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InventoryItem) {
            binding.itemImage.setImageResource(item.imageResource)
            binding.itemName.text = item.name
            binding.itemDescription.text = item.description
            binding.itemPrice.text = item.price

            binding.root.setOnClickListener {
                val intent = Intent(context, OrderDetailActivity::class.java).apply {
                    putExtra("imageResource", item.imageResource)
                    putExtra("name", item.name)
                    putExtra("description", item.description)
                    putExtra("itemPrice" , item.price)
                }
                context.startActivity(intent)
            }

            binding.trashIcon.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemDelete(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        context = parent.context
        val binding = ItemInventarisBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return InventoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
