package com.example.pasarup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheckoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)

        val rvCartItems = view.findViewById<RecyclerView>(R.id.rvCartItems)
        val tvTotalHarga = view.findViewById<TextView>(R.id.tvTotalHarga)
        val tvSubtotal = view.findViewById<TextView>(R.id.tvSubtotal)
        val tvSubtotalLabel = view.findViewById<TextView>(R.id.tvSubtotalLabel)
        val btnLacak = view.findViewById<Button>(R.id.btnLacak)
        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        val btnBatalkan = view.findViewById<Button>(R.id.btnBatalkan)

        // Tombol Kembali
        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Setup RecyclerView
        rvCartItems.layoutManager = LinearLayoutManager(context)
        rvCartItems.adapter = CartAdapter(CartManager.items)

        // Update Data Harga
        val subtotal = CartManager.getTotalHarga()
        val ongkir = 12000
        val total = subtotal + ongkir
        val itemCount = CartManager.items.sumOf { it.jumlah }

        tvSubtotal.text = "Rp ${subtotal}"
        tvTotalHarga.text = "Rp ${total}"
        tvSubtotalLabel.text = "Subtotal ($itemCount Barang)"

        // Tombol Order / Lacak (Sesuai gambar alur akhir)
        btnLacak.setOnClickListener {
            if (CartManager.items.isNotEmpty()) {
                CartManager.checkout() // Pindah ke list Order
                Toast.makeText(context, "Pesanan Berhasil! Menuju Pembayaran...", Toast.LENGTH_SHORT).show()

                // Pindah ke Fragment Pesanan
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, pesanan())
                    .commit()
            } else {
                Toast.makeText(context, "Keranjang Anda Kosong", Toast.LENGTH_SHORT).show()
            }
        }

        btnBatalkan.setOnClickListener {
            CartManager.items.clear()
            parentFragmentManager.popBackStack()
        }

        return view
    }
}