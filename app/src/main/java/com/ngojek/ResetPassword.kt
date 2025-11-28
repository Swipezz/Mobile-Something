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

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Semua kolom password harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                Toast.makeText(requireContext(), "Kata sandi baru dan konfirmasi tidak cocok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(), "Password Telah di Reset", Toast.LENGTH_LONG).show()

            parentFragmentManager.popBackStack(null, 0)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignIn())
                .commit()
        }
    }
}