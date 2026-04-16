package com.example.pasarup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchAdapter(
    private var listProduk: List<CartItem>,
    private val onAddClick: (CartItem) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduk: ImageView = view.findViewById(R.id.ivProduk)
        val tvNama: TextView = view.findViewById(R.id.tvNamaProduk)
        val tvHarga: TextView = view.findViewById(R.id.tvHargaProduk)
        val tvToko: TextView = view.findViewById(R.id.tvTokoProduk)
        val btnAdd: ImageButton = view.findViewById(R.id.btnAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val produk = listProduk[position]
        holder.ivProduk.setImageResource(produk.gambarResId)
        holder.tvNama.text = produk.nama
        holder.tvHarga.text = "Rp ${produk.harga}"
        holder.tvToko.text = produk.tokoId // Kita pakai tokoId sebagai nama toko sementara
        
        holder.btnAdd.setOnClickListener {
            onAddClick(produk)
        }
    }

    override fun getItemCount(): Int = listProduk.size

    fun updateList(newList: List<CartItem>) {
        listProduk = newList
        notifyDataSetChanged()
    }
}