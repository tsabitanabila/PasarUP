package com.example.pasarup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TokoAdapter(private val listToko: List<Toko>) : RecyclerView.Adapter<TokoAdapter.TokoViewHolder>() {

    class TokoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgToko: ImageView = view.findViewById(R.id.imgToko)
        val txtNama: TextView = view.findViewById(R.id.txtNamaToko)
        val txtRating: TextView = view.findViewById(R.id.txtRatingToko)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_toko, parent, false)
        return TokoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TokoViewHolder, position: Int) {
        val toko = listToko[position]
        holder.imgToko.setImageResource(toko.imageResId)
        holder.txtNama.text = toko.nama
        holder.txtRating.text = toko.rating
    }

    override fun getItemCount(): Int = listToko.size
}