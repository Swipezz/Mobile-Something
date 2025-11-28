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

class ForgotPassword : Fragment() {
    private val DUMMY_REGISTERED_EMAIL = "dummy@dummy.com"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forgot_password_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputEmail: EditText = view.findViewById(R.id.inputEmail)
        val btnContinue: Button = view.findViewById(R.id.btnContinue)
        val iconBack: ImageView = view.findViewById(R.id.iconBack)

        iconBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnContinue.setOnClickListener {
            val email = inputEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email == DUMMY_REGISTERED_EMAIL) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, EnterOTP())
                    .addToBackStack("ForgotPassword")
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Email Tidak Terdaftar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}