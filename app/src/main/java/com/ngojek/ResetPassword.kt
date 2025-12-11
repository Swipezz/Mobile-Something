package com.ngojek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ResetPassword : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reset_password_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Komponen
        val inputNewPassword: EditText = view.findViewById(R.id.inputNewPassword)
        val inputConfirmPassword: EditText = view.findViewById(R.id.inputConfirmPassword)
        val btnContinue: Button = view.findViewById(R.id.btnContinue)
        val iconBack: ImageView = view.findViewById(R.id.iconBack)

        iconBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnContinue.setOnClickListener {
            val newPassword = inputNewPassword.text.toString()
            val confirmPassword = inputConfirmPassword.text.toString()

            // DEMO NOTE: Validasi Dasar
            // "Pertama, kita pastikan user tidak mengosongkan kolom apapun."
            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Semua kolom password harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // DEMO HIGHLIGHT: VALIDASI KECOCOKAN PASSWORD
            // Jelaskan: "Ini fitur pencegahan error yang penting. Kita wajib membandingkan
            // apakah 'Password Baru' sama persis dengan 'Konfirmasi Password'.
            // Kalau beda satu huruf saja, sistem akan menolak."
            if (newPassword != confirmPassword) {
                Toast.makeText(requireContext(), "Kata sandi baru dan konfirmasi tidak cocok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Feedback Sukses
            Toast.makeText(requireContext(), "Password Telah di Reset", Toast.LENGTH_LONG).show()

            // DEMO NOTE: NAVIGASI KEAMANAN
            // Jelaskan: "Setelah password berhasil diubah, praktik keamanan yang baik adalah
            // memaksa user untuk Login ulang dengan password baru.
            // Makanya di sini kita clear backstack dan arahkan ke SignIn."

            // Hapus tumpukan fragment sebelumnya (agar tidak bisa di-back)
            parentFragmentManager.popBackStack(null, 0)

            // Pindah ke layar Login
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignIn())
                .commit()
        }
    }
}