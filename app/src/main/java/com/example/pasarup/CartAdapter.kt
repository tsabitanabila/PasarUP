package com.example.pasarup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val items: List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCartItem: ImageView = view.findViewById(R.id.ivCartItem)
        val tvCartName: TextView = view.findViewById(R.id.tvCartName)
        val tvCartPrice: TextView = view.findViewById(R.id.tvCartPrice)
        val tvCartQty: TextView = view.findViewById(R.id.tvCartQty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.ivCartItem.setImageResource(item.gambarResId)
        holder.tvCartName.text = item.nama
        holder.tvCartPrice.text = "Rp ${item.harga}"
        holder.tvCartQty.text = "x${item.jumlah}"
    }

    override fun getItemCount(): Int = items.size
}