package com.example.pasarup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // UI References
        val navHome = view.findViewById<LinearLayout>(R.id.navHome)
        val navSearch = view.findViewById<LinearLayout>(R.id.navSearch)
        val navOrder = view.findViewById<LinearLayout>(R.id.navOrder)
        val profileName = view.findViewById<TextView>(R.id.profileName)

        // Recent Order References
        val orderItem1 = view.findViewById<LinearLayout>(R.id.orderItem1)
        val orderTitle1 = view.findViewById<TextView>(R.id.orderTitle1)
        val orderDate1 = view.findViewById<TextView>(R.id.orderDate1)
        val orderStatus1 = view.findViewById<TextView>(R.id.orderStatus1)

        val orderItem2 = view.findViewById<LinearLayout>(R.id.orderItem2)
        val orderTitle2 = view.findViewById<TextView>(R.id.orderTitle2)
        val orderDate2 = view.findViewById<TextView>(R.id.orderDate2)
        val orderStatus2 = view.findViewById<TextView>(R.id.orderStatus2)

        // Ambil data dari SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE)
        val savedUsername = sharedPref.getString("USERNAME", "User")
        profileName.text = savedUsername

        // Set Dummy Data Riwayat (Agar tidak kosong saat demo)
        orderTitle1.text = "Bayam Segar & Wortel"
        orderDate1.text = "24 Okt 2023 • Rp 12.000"
        orderStatus1.text = "Dikemas"

        orderTitle2.text = "Sawi Hijau"
        orderDate2.text = "22 Okt 2023 • Rp 5.000"
        orderStatus2.text = "Selesai"

        // Navigasi Bottom Bar
        navHome.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, BerandaFragment())
                .commit()
        }

        navSearch.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, search())
                .commit()
        }

        navOrder.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, pesanan())
                .commit()
        }

        // Klik pada riwayat pesanan langsung ke halaman Pesanan
        orderItem1.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, pesanan())
                .commit()
        }

        orderItem2.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, pesanan())
                .commit()
        }

        return view
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
