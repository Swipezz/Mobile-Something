package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment

interface BottomNavCallback {
    fun onHomeSelectedFromFragment()
}

class UserSettingFragment : Fragment() {

    private lateinit var iconBack: ImageView
    private lateinit var fullName: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var cardNumber: EditText
    private lateinit var cardName: EditText
    private lateinit var danaNumber: EditText
    private lateinit var danaName: EditText

    private lateinit var btnSave: LinearLayout
    private lateinit var btnCancel: LinearLayout
    private lateinit var btnHome: ImageView
    private lateinit var iconProfile: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iconBack = view.findViewById(R.id.icon_back)

        fullName = view.findViewById(R.id.full_name)
        email = view.findViewById(R.id.email_address)
        phone = view.findViewById(R.id.phone_number)
        cardNumber = view.findViewById(R.id.credit_card_number)
        cardName = view.findViewById(R.id.card_name)
        danaNumber = view.findViewById(R.id.dana_number)
        danaName = view.findViewById(R.id.dana_name)

        btnSave = view.findViewById(R.id.outline_save)
        btnCancel = view.findViewById(R.id.outline_cancel)

        btnHome = view.findViewById(R.id.btn_home_setting)
        iconProfile = view.findViewById(R.id.btn_profile)

        enableTapToEdit(fullName)
        enableTapToEdit(email)
        enableTapToEdit(phone)
        enableTapToEdit(cardNumber)
        enableTapToEdit(danaNumber)

        iconBack.setOnClickListener {
            goToHomeFragment()
        }

        btnSave.setOnClickListener {
            saveUserData()
            disableAll()
            goToHomeFragment()
        }

        btnCancel.setOnClickListener {
            disableAll()
            Toast.makeText(context, "Perubahan dibatalkan.", Toast.LENGTH_SHORT).show()
            goToHomeFragment()
        }

        btnHome.setOnClickListener {
            goToHomeFragment()
        }
    }

    private fun goToHomeFragment() {
        (activity as? BottomNavCallback)?.onHomeSelectedFromFragment()

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
    }



    private fun setEditable(editText: EditText, enable: Boolean) {
        editText.isFocusable = enable
        editText.isFocusableInTouchMode = enable
        editText.isCursorVisible = enable
        editText.isEnabled = enable
    }

    private fun enableTapToEdit(editText: EditText) {
        editText.isEnabled = false
        editText.isFocusable = false
        editText.isFocusableInTouchMode = false
        editText.isClickable = true

        editText.setOnClickListener {
            editText.isEnabled = true
            editText.isFocusable = true
            editText.isFocusableInTouchMode = true
            editText.isCursorVisible = true
            editText.requestFocus()
        }
    }

    private fun disableAll() {
        setEditable(fullName, false)
        setEditable(email, false)
        setEditable(phone, false)
        setEditable(cardNumber, false)
        setEditable(danaNumber, false)
    }

    private fun saveUserData() {
        val newFullName = fullName.text.toString()

        if (newFullName.isBlank()) {
            Toast.makeText(context, "Nama lengkap tidak boleh kosong.", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(context, "Pengaturan berhasil diperbarui!", Toast.LENGTH_LONG).show()
    }
}
