package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import androidx.fragment.app.Fragment

class SignIn : Fragment() {

    private var isPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Komponen UI
        val linkSignUp: TextView = view.findViewById(R.id.linkSignUp)
        val textForgetPassword: TextView = view.findViewById(R.id.textForgetPassword)
        val btnLogin: MaterialButton = view.findViewById(R.id.btnLogin)

        val inputEmail: EditText = view.findViewById(R.id.inputEmail)
        val inputPassword: EditText = view.findViewById(R.id.inputPassword)
        val visibilityIcon: ImageView = view.findViewById(R.id.visibilityIcon)

        // DEMO HIGHLIGHT: FITUR TOGGLE PASSWORD
        // Jelaskan: "Fitur UX penting: User bisa melihat password yang mereka ketik
        // untuk memastikan tidak ada typo sebelum menekan tombol Login."
        visibilityIcon.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                // Ubah input jadi teks biasa (terbaca) & ganti ikon mata terbuka
                inputPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                visibilityIcon.setImageResource(R.drawable.ic_visibility)
            } else {
                // Ubah input jadi password (bintang/titik) & ganti ikon mata tertutup
                inputPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                visibilityIcon.setImageResource(R.drawable.ic_visibility_off)
            }

            // DEMO DETAIL: "Bug fix kecil tapi penting: Saat tipe input berubah, kursor biasanya
            // loncat ke depan. Baris ini memaksa kursor tetap di akhir teks agar nyaman diedit."
            inputPassword.setSelection(inputPassword.text.length)
        }

        // ------------------ Login Button ------------------
        btnLogin.setOnClickListener {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            // Validasi Input Kosong
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // DEMO NOTE: SIMULASI AKUN
            // Jelaskan: "Untuk demo ini, kita hardcode kredensial admin.
            // Email: admin@gmail.com, Pass: admin123. Jika input sesuai, baru masuk ke Home."
            if (email == "admin@gmail.com" && password == "admin123") {
                Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()

                // Pindah ke HomeActivity
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)

                // PENTING: Finish activity login agar user tidak bisa kembali ke sini dengan tombol Back
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        // ------------------ Navigasi Fragment ------------------

        // Pindah ke SignUp
        linkSignUp.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUp())
                .addToBackStack("SignIn") // Agar bisa di-back
                .commit()
        }

        // Pindah ke Forgot Password
        textForgetPassword.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ForgotPassword())
                .addToBackStack("SignIn") // Agar bisa di-back
                .commit()
        }
    }
}