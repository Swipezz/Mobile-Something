package com.ngojek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

        // Inisialisasi Komponen
        val inputName: EditText = view.findViewById(R.id.inputName)
        val inputEmail: EditText = view.findViewById(R.id.inputEmail)
        val inputPassword: EditText = view.findViewById(R.id.inputPassword)
        val btnCreate: MaterialButton = view.findViewById(R.id.btnCreateAccount)
        val linkSignIn: TextView = view.findViewById(R.id.linkSignIn)

        // DEMO NOTE: Navigasi Efisien
        // "Jika user sudah punya akun, kita tidak perlu membuat fragment baru.
        // Cukup 'popBackStack' untuk kembali ke halaman Login yang ada di memori sebelumnya."
        linkSignIn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnCreate.setOnClickListener {

            val name = inputName.text.toString().trim()
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            // DEMO HIGHLIGHT: VALIDASI BERLAPIS (Input Sanitization)
            // Jelaskan: "Sebelum data dikirim ke server, kita wajib memvalidasi di sisi klien.
            // Di sini saya mengecek satu per satu field. Jika ada yang kosong, proses berhenti."

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Stop eksekusi
            }

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(requireContext(), "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // DEMO NOTE: SIMULASI SUKSES
            // Jelaskan: "Jika semua validasi lolos, sistem menganggap akun berhasil dibuat.
            // Secara UX, kita arahkan user ke halaman Sign In agar mereka mencoba login dengan akun barunya."

            Toast.makeText(requireContext(), "Akun berhasil dibuat", Toast.LENGTH_SHORT).show()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignIn())
                // Tidak perlu addToBackStack disini jika kita ingin user login dulu (opsional)
                .commit()
        }
    }
}