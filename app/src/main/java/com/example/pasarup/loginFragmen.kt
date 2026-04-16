package com.example.pasarup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = view.findViewById<EditText>(R.id.etEmail)
        val password = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val txtRegister = view.findViewById<TextView>(R.id.txtRegister)

        btnLogin.setOnClickListener {
            val isiEmail = email.text.toString()
            val isiPassword = password.text.toString()

            val sharedPref = requireActivity().getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE)
            val registeredEmail = sharedPref.getString("EMAIL", "")
            val registeredPassword = sharedPref.getString("PASSWORD", "")

            if (isiEmail.isEmpty() || isiPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Isi semua data!", Toast.LENGTH_SHORT).show()
            } else if (isiEmail == "admin@gmail.com" && isiPassword == "admin123") {
                // Login Admin khusus
                with(sharedPref.edit()) {
                    putBoolean("IS_LOGGED_IN", true)
                    putString("USERNAME", "Administrator")
                    apply()
                }
                Toast.makeText(requireContext(), "Login Admin Berhasil", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction().replace(R.id.frameLayout, BerandaFragment()).commit()
            } else if (isiEmail == registeredEmail && isiPassword == registeredPassword) {
                // Login User dari Register
                with(sharedPref.edit()) {
                    putBoolean("IS_LOGGED_IN", true)
                    apply()
                }
                Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction().replace(R.id.frameLayout, BerandaFragment()).commit()
            } else {
                Toast.makeText(requireContext(), "Email atau Password Salah!", Toast.LENGTH_SHORT).show()
            }
        }

        txtRegister.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}