package com.example.pasarup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StoreDetailFragment : Fragment() {

    companion object {
        fun newInstance(nama: String, desc: String, imageResId: Int): StoreDetailFragment {
            val fragment = StoreDetailFragment()
            val args = Bundle()
            args.putString("nama", nama)
            args.putString("desc", desc)
            args.putInt("image", imageResId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store_detail, container, false)

        val tvName = view.findViewById<TextView>(R.id.tvStoreDetailName)
        val tvDesc = view.findViewById<TextView>(R.id.tvStoreDetailDesc)
        val ivHeader = view.findViewById<ImageView>(R.id.ivStoreHeader)
        val btnBack = view.findViewById<ImageButton>(R.id.btnBackStore)
        val rvProducts = view.findViewById<RecyclerView>(R.id.rvStoreProducts)

        // Cart Bottom Views
        val tvBottomSubtotal = view.findViewById<TextView>(R.id.tvBottomSubtotal)
        val tvCartBadge = view.findViewById<TextView>(R.id.tvCartBadge)
        val btnCheckout = view.findViewById<Button>(R.id.btnBottomCheckout)

        val nama = arguments?.getString("nama") ?: "Pasar Jaya Fresh Market"
        val desc = arguments?.getString("desc") ?: "Sayuran segar langsung dari petani lokal."
        val image = arguments?.getInt("image") ?: R.drawable.ic_pasarup

        tvName.text = nama
        tvDesc.text = desc
        ivHeader.setImageResource(image)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnCheckout.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, CheckoutFragment())
                .addToBackStack(null)
                .commit()
        }

        // Update Cart Bottom UI
        fun updateCartBottom() {
            val total = CartManager.getTotalHarga()
            val count = CartManager.items.sumOf { it.jumlah }
            tvBottomSubtotal.text = "Rp $total"
            tvCartBadge.text = count.toString()
            view.findViewById<View>(R.id.layoutBottomCart).visibility =
                if (count > 0) View.VISIBLE else View.GONE
        }

        // DATA SAYURAN UNTUK DEMO UTS
        // Menggunakan icon berbeda-beda agar tidak terlihat sama semua (Tomat semua)
        val products = listOf(
            CartItem("v1", "Sawi Hijau", 5000, R.drawable.ic_pasarup, 1, nama),
            CartItem("v2", "Wortel Lokal", 8000, R.drawable.ic_home, 1, nama),
            CartItem("v3", "Tomat Merah", 12000, R.drawable.ic_search, 1, nama),
            CartItem("v4", "Bayam Segar", 4000, R.drawable.ic_pesanan, 1, nama)
        )

        // Gunakan GridProductAdapter dengan data 'products' yang sudah dibuat di atas
        rvProducts.layoutManager = GridLayoutManager(context, 2)
        rvProducts.adapter = GridProductAdapter(products) { item ->
            CartManager.addItem(item)
            updateCartBottom()
        }

        updateCartBottom()

        return view
    }
}