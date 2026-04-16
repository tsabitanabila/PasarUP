package com.example.pasarup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class BerandaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_beranda, container, false)

        val navProfile = view.findViewById<LinearLayout>(R.id.navProfile)
        val navSearch = view.findViewById<LinearLayout>(R.id.navSearch)
        val navOrder = view.findViewById<LinearLayout>(R.id.navOrder)
        val cardToko1 = view.findViewById<CardView>(R.id.cardToko1)

        navProfile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        navSearch.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, search())
                .addToBackStack(null)
                .commit()
        }

        navOrder.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, pesanan())
                .addToBackStack(null)
                .commit()
        }

        cardToko1.setOnClickListener {
            val detailFragment = StoreDetailFragment.newInstance(
                "Pasar Jaya Fresh Market",
                "Sayuran segar langsung dari petani lokal.",
                R.drawable.ic_pasarup
            )
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}