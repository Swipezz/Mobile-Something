package com.ngojek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class SignUp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_up_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputName: EditText = view.findViewById(R.id.inputName)
        val inputEmail: EditText = view.findViewById(R.id.inputEmail)
        val inputPassword: EditText = view.findViewById(R.id.inputPassword)
        val btnCreate: MaterialButton = view.findViewById(R.id.btnCreateAccount)
        val linkSignIn: TextView = view.findViewById(R.id.linkSignIn)

        // Pindah ke Sign In ketika klik "Sign In"
        linkSignIn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Tombol Create Account ditekan
        btnCreate.setOnClickListener {

            val name = inputName.text.toString().trim()
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            // Validasi satu per satu

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(requireContext(), "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Jika semua terisi -> pindah ke Sign In
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignIn())
                .addToBackStack("SignUp")
                .commit()

            Toast.makeText(requireContext(), "Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
        }
    }
}
