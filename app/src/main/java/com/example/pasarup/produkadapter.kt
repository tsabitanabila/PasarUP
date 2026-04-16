package com.example.pasarup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProdukAdapter(
    private var listProduk: List<Produk>,
    private val onItemClick: (Produk) -> Unit // Ini fungsi tambahan untuk klik
) : RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder>() {

    class ProdukViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama: TextView = view.findViewById(R.id.tvNamaProduk)
        val harga: TextView = view.findViewById(R.id.tvHargaProduk)
        val toko: TextView = view.findViewById(R.id.tvTokoProduk)
        val gambar: ImageView = view.findViewById(R.id.ivProduk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return ProdukViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        val produk = listProduk[position]
        holder.nama.text = produk.nama
        holder.harga.text = produk.harga
        holder.toko.text = produk.toko
        holder.gambar.setImageResource(produk.gambar)

        // PASANG KLIK DI SINI
        holder.itemView.setOnClickListener {
            onItemClick(produk)
        }
    }

    override fun getItemCount(): Int = listProduk.size

    fun updateList(newList: List<Produk>) {
        listProduk = newList
        notifyDataSetChanged()
    }
}