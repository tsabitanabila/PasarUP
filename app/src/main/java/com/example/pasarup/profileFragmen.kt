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

        val navHome = view.findViewById<LinearLayout>(R.id.navHome)
        val navSearch = view.findViewById<LinearLayout>(R.id.navSearch)
        val navOrder = view.findViewById<LinearLayout>(R.id.navOrder)
        val profileName = view.findViewById<TextView>(R.id.profileName)

        // Ambil data dari SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE)
        val savedUsername = sharedPref.getString("USERNAME", "User")
        profileName.text = savedUsername

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

        return view
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
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