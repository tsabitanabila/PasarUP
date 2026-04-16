package com.example.pasarup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DetailProdukFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_produk, container, false)

        // 1. Tangkap data barang yang dikirim dari halaman Search
        val nama = arguments?.getString("EXTRA_NAMA")
        val harga = arguments?.getString("EXTRA_HARGA")
        val toko = arguments?.getString("EXTRA_TOKO")
        val gambar = arguments?.getInt("EXTRA_GAMBAR") ?: R.drawable.ic_pasarup

        // 2. Kenalkan ID ke kode Kotlin
        val ivGambar = view.findViewById<ImageView>(R.id.ivDetailGambar)
        val tvNama = view.findViewById<TextView>(R.id.tvDetailNama)
        val tvHarga = view.findViewById<TextView>(R.id.tvDetailHarga)
        val tvToko = view.findViewById<TextView>(R.id.tvDetailToko)

        // 3. Masukkan data ke layar
        tvNama.text = nama
        tvHarga.text = harga
        tvToko.text = toko
        ivGambar.setImageResource(gambar)

        // ... di dalam onCreateView ...
        val btnBack = view.findViewById<View>(R.id.btnBack)
        btnBack.setOnClickListener {
            // Perintah untuk kembali ke halaman sebelumnya
            parentFragmentManager.popBackStack()
        }

        return view
    }
}