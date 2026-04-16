package com.example.pasarup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class search : Fragment() {

    private lateinit var adapter: ProdukAdapter
    private val listDataAsli = mutableListOf<Produk>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // 1. Inisialisasi Data Dummy
        isiDataDummy()

        // 2. Setup RecyclerView dengan Fitur Klik (Tugas No. 4)
        val rvSearch = view.findViewById<RecyclerView>(R.id.rvSearchResults)

        // Kita kirim data produk yang diklik ke DetailProdukFragment
        adapter = ProdukAdapter(listDataAsli) { produk ->
            val bundle = Bundle()
            bundle.putString("EXTRA_NAMA", produk.nama)
            bundle.putString("EXTRA_HARGA", produk.harga)
            bundle.putString("EXTRA_TOKO", produk.toko)
            bundle.putInt("EXTRA_GAMBAR", produk.gambar)

            val detailFrag = DetailProdukFragment()
            detailFrag.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, detailFrag)
                .addToBackStack(null) // Agar bisa kembali ke Search saat klik back
                .commit()
        }

        rvSearch.layoutManager = LinearLayoutManager(context)
        rvSearch.adapter = adapter

        // 3. Logika Search Real-time
        val etSearch = view.findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterData(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // 4. Navigasi Bottom Navigation
        view.findViewById<LinearLayout>(R.id.navHome)?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frameLayout, BerandaFragment()).commit()
        }
        view.findViewById<LinearLayout>(R.id.navOrder)?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frameLayout, pesanan()).commit()
        }
        view.findViewById<LinearLayout>(R.id.navProfile)?.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frameLayout, ProfileFragment()).commit()
        }

        return view
    }

    // Fungsi untuk menyaring data berdasarkan ketikan user
    private fun filterData(query: String) {
        val listHasilFilter = listDataAsli.filter {
            it.nama.contains(query, ignoreCase = true) ||
                    it.toko.contains(query, ignoreCase = true)
        }
        adapter.updateList(listHasilFilter)
    }

    // Data simulasi
    private fun isiDataDummy() {
        listDataAsli.clear()
        listDataAsli.add(Produk("Wortel Segar", "Rp 5.000", "Toko Bu Siti", R.drawable.ic_pasarup))
        listDataAsli.add(Produk("Bayam Hijau", "Rp 3.000", "Sayur Mayur Jaya", R.drawable.ic_pasarup))
        listDataAsli.add(Produk("Apel Merah", "Rp 15.000", "Buah Segar Abadi", R.drawable.ic_pasarup))
        listDataAsli.add(Produk("Tomat Merah", "Rp 8.000", "Toko Bu Siti", R.drawable.ic_pasarup))
    }
}