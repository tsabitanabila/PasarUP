package com.example.pasarup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class pesanan : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pesanan, container, false)

        // 1. Inisialisasi RecyclerView
        val rvOrderList = view.findViewById<RecyclerView>(R.id.rvOrderList)

        // 2. Cek apakah ada pesanan
        if (CartManager.orders.isEmpty()) {
            Toast.makeText(context, "Belum ada riwayat pesanan", Toast.LENGTH_SHORT).show()
        }

        // 3. Setup Adapter
        rvOrderList.layoutManager = LinearLayoutManager(context)
        rvOrderList.adapter = OrderAdapter(CartManager.orders) { order ->
            // Aksi saat item pesanan diklik (buka detail)
            val detailFrag = OrderDetailFragment.newInstance(order)
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, detailFrag)
                .addToBackStack(null)
                .commit()
        }

        // 4. Navigasi Bottom Navigation
        view.findViewById<LinearLayout>(R.id.navHome)?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frameLayout, BerandaFragment()).commit()
        }
        view.findViewById<LinearLayout>(R.id.navSearch)?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frameLayout, search()).commit()
        }
        view.findViewById<LinearLayout>(R.id.navProfile)?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frameLayout, ProfileFragment()).commit()
        }

        return view
    }
}