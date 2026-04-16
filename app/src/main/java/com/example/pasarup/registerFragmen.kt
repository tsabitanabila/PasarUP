package com.example.pasarup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nama = view.findViewById<EditText>(R.id.etNama)
        val email = view.findViewById<EditText>(R.id.etEmail)
        val phone = view.findViewById<EditText>(R.id.etPhone)
        val password = view.findViewById<EditText>(R.id.etPassword)
        val confirm = view.findViewById<EditText>(R.id.etConfirmPassword)
        val btnDaftar = view.findViewById<Button>(R.id.btnDaftar)
        val txtLogin = view.findViewById<TextView>(R.id.txtLogin)

        btnDaftar.setOnClickListener {
            if (
                nama.text.toString().isEmpty() ||
                email.text.toString().isEmpty() ||
                phone.text.toString().isEmpty() ||
                password.text.toString().isEmpty() ||
                confirm.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "Lengkapi semua data!", Toast.LENGTH_SHORT).show()
            } else if (password.text.toString() != confirm.text.toString()) {
                Toast.makeText(requireContext(), "Password tidak sama!", Toast.LENGTH_SHORT).show()
            } else {
                // Simpan data ke SharedPreferences
                val sharedPref = requireActivity().getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("USERNAME", nama.text.toString())
                    putString("EMAIL", email.text.toString())
                    putString("PASSWORD", password.text.toString())
                    apply()
                }

                Toast.makeText(requireContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, LoginFragment())
                    .commit()
            }
        }

        txtLogin.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, LoginFragment())
                .commit()
        }
    }
}