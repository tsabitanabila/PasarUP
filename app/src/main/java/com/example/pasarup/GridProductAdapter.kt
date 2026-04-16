package com.example.pasarup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridProductAdapter(
    private val listProduk: List<CartItem>,
    private val onAddClick: (CartItem) -> Unit
) : RecyclerView.Adapter<GridProductAdapter.GridViewHolder>() {

    class GridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduk: ImageView = view.findViewById(R.id.ivProdukGrid)
        val tvNama: TextView = view.findViewById(R.id.tvNamaProdukGrid)
        val tvHarga: TextView = view.findViewById(R.id.tvHargaProdukGrid)
        val btnAdd: ImageButton = view.findViewById(R.id.btnAddToCartGrid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk_grid, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val produk = listProduk[position]
        holder.ivProduk.setImageResource(produk.gambarResId)
        holder.tvNama.text = produk.nama
        holder.tvHarga.text = "Rp ${produk.harga}"

        holder.btnAdd.setOnClickListener {
            onAddClick(produk)
        }
    }

    override fun getItemCount(): Int = listProduk.size
}