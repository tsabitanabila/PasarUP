package com.example.pasarup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderDetailFragment : Fragment() {

    companion object {
        private var currentOrder: Order? = null

        fun newInstance(order: Order): OrderDetailFragment {
            val fragment = OrderDetailFragment()
            currentOrder = order
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)

        val tvOrderId = view.findViewById<TextView>(R.id.tvOrderId)
        val tvStoreName = view.findViewById<TextView>(R.id.tvStoreName)
        val rvItems = view.findViewById<RecyclerView>(R.id.rvCartItems)
        val tvTotal = view.findViewById<TextView>(R.id.tvTotalHarga)
        val tvSubtotal = view.findViewById<TextView>(R.id.tvSubtotal)
        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        
        // Sembunyikan tombol aksi karena ini hanya detail riwayat
        view.findViewById<View>(R.id.layoutBottomAction).visibility = View.GONE

        currentOrder?.let { order ->
            tvOrderId.text = "ID Pesanan: ${order.id}"
            tvStoreName.text = order.items.firstOrNull()?.tokoId ?: "Toko"
            tvTotal.text = "Rp ${order.totalHarga}"
            tvSubtotal.text = "Rp ${order.totalHarga - 12000}"
            
            rvItems.layoutManager = LinearLayoutManager(context)
            rvItems.adapter = CartAdapter(order.items)
        }

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}