package com.example.pasarup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(
    private val listOrder: List<Order>,
    private val onItemClick: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStoreName: TextView = view.findViewById(R.id.tvOrderStoreName)
        val tvOrderId: TextView = view.findViewById(R.id.tvOrderId)
        val tvStatus: TextView = view.findViewById(R.id.tvOrderStatus)
        val tvDate: TextView = view.findViewById(R.id.tvOrderDate)
        val tvTotal: TextView = view.findViewById(R.id.tvOrderTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = listOrder[position]
        holder.tvStoreName.text = order.items.firstOrNull()?.tokoId ?: "Toko"
        holder.tvOrderId.text = order.id
        holder.tvStatus.text = order.status
        holder.tvDate.text = order.tanggal
        holder.tvTotal.text = "Rp ${order.totalHarga}"

        holder.itemView.setOnClickListener {
            onItemClick(order)
        }
    }

    override fun getItemCount(): Int = listOrder.size
}