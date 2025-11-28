package com.ngojek

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class EnterOTP : Fragment() {
    private val DUMMY_CORRECT_OTP = "123456"
    private lateinit var otpFields: List<EditText>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.enter_otp_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnVerifyOTP: Button = view.findViewById(R.id.btnVerifyOTP)
        val iconBack: ImageView = view.findViewById(R.id.iconBack)

        otpFields = listOf(
            view.findViewById(R.id.otp1),
            view.findViewById(R.id.otp2),
            view.findViewById(R.id.otp3),
            view.findViewById(R.id.otp4),
            view.findViewById(R.id.otp5),
            view.findViewById(R.id.otp6)
        )

        setupOtpListeners()

        iconBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnVerifyOTP.setOnClickListener {
            val inputOtp = getCombinedOtp()

            if (inputOtp.length < 6) {
                Toast.makeText(requireContext(), "Silakan masukkan 6 digit OTP", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (inputOtp == DUMMY_CORRECT_OTP) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ResetPassword())
                    .addToBackStack("EnterOTP")
                    .commit()
            } else {
                Toast.makeText(requireContext(), "OTP Salah", Toast.LENGTH_SHORT).show()
                clearOtpFields()
            }
        }
    }

    private fun setupOtpListeners() {
        for (i in otpFields.indices) {
            val currentOtp = otpFields[i]
            currentOtp.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        if (i < otpFields.size - 1) {
                            otpFields[i + 1].requestFocus()
                        }
                    } else if (s?.isEmpty() == true) {
                        if (i > 0) {
                            otpFields[i - 1].requestFocus()
                        }
                    }
                }
            })
        }
    }

    private fun getCombinedOtp(): String {
        return otpFields.joinToString("") { it.text.toString() }
    }

    private fun clearOtpFields() {
        otpFields.forEach { it.setText("") }
        otpFields.first().requestFocus()
    }
}