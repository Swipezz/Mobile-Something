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

        val linkSignUp: TextView = view.findViewById(R.id.linkSignUp)
        val textForgetPassword: TextView = view.findViewById(R.id.textForgetPassword)
        val btnLogin: MaterialButton = view.findViewById(R.id.btnLogin)

        val inputEmail: EditText = view.findViewById(R.id.inputEmail)
        val inputPassword: EditText = view.findViewById(R.id.inputPassword)
        val visibilityIcon: ImageView = view.findViewById(R.id.visibilityIcon)

        visibilityIcon.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                inputPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                visibilityIcon.setImageResource(R.drawable.ic_visibility)
            } else {
                inputPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                visibilityIcon.setImageResource(R.drawable.ic_visibility_off)
            }

            // Keep cursor at the end
            inputPassword.setSelection(inputPassword.text.length)
        }

        // Login Button
        btnLogin.setOnClickListener {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Sample Valid User
            if (email == "admin@gmail.com" && password == "admin") {
                Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()

                // Pindah ke HomeActivity
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        // ------------------ Move to SignUp ------------------
        linkSignUp.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUp())
                .addToBackStack("SignIn")
                .commit()
        }

        // ------------------ Move to Forgot Password ------------------
        textForgetPassword.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ForgotPassword())
                .addToBackStack("SignIn")
                .commit()
        }
    }
}
