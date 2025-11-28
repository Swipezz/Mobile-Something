package com.ngojek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SignIn : Fragment() {
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

        linkSignUp.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUp())
                .addToBackStack("SignIn")
                .commit()
        }

        textForgetPassword.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ForgotPassword())
                .addToBackStack("SignIn")
                .commit()
        }
    }
}