package com.ngojek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class ForgotPassword : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forgot_password_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val iconBack: ImageView = view.findViewById(R.id.iconBack)
        val btnContinue: MaterialButton = view.findViewById(R.id.btnContinue)
        val inputEmail: EditText = view.findViewById(R.id.inputEmail)

        iconBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnContinue.setOnClickListener {
            val email = inputEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Silakan masukkan email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email == "admin@gmail.com") {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, EnterOTP())
                    .addToBackStack("ForgotPassword")
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Email tidak terdaftar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
