package com.ngojek

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
    
    private lateinit var sharedPreferences: SharedPreferences
    
    companion object {
        private const val PREF_NAME = "UserSettings"
        private const val KEY_FULL_NAME = "fullName"
        private const val KEY_EMAIL = "email"
        private const val KEY_PHONE = "phone"
        private const val KEY_CARD_NUMBER = "cardNumber"
        private const val KEY_CARD_NAME = "cardName"
        private const val KEY_DANA_NUMBER = "danaNumber"
        private const val KEY_DANA_NAME = "danaName"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

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
        
        // Load saved data
        loadUserData()

        enableTapToEdit(fullName)
        enableTapToEdit(email)
        enableTapToEdit(phone)
        enableTapToEdit(cardNumber)
        enableTapToEdit(cardName)
        enableTapToEdit(danaNumber)
        enableTapToEdit(danaName)

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
        
        // Hide fragment container saat kembali ke home
        val fragmentContainer = requireActivity().findViewById<View>(R.id.fragment_container)
        fragmentContainer?.visibility = View.GONE

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
    }



    private fun setEditable(editText: EditText, enable: Boolean) {
        editText.isFocusable = enable
        editText.isFocusableInTouchMode = enable
        editText.isCursorVisible = enable
    }

    private fun enableTapToEdit(editText: EditText) {
        editText.isFocusable = false
        editText.isFocusableInTouchMode = false
        editText.isCursorVisible = false

        editText.setOnClickListener {
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
        setEditable(cardName, false)
        setEditable(danaNumber, false)
        setEditable(danaName, false)
    }

    private fun saveUserData() {
        val newFullName = fullName.text.toString()
        val newEmail = email.text.toString()
        val newPhone = phone.text.toString()
        val newCardNumber = cardNumber.text.toString()
        val newCardName = cardName.text.toString()
        val newDanaNumber = danaNumber.text.toString()
        val newDanaName = danaName.text.toString()

        // Validasi
        if (newFullName.isBlank()) {
            Toast.makeText(context, "Nama lengkap tidak boleh kosong.", Toast.LENGTH_SHORT).show()
            return
        }

        // Simpan ke SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString(KEY_FULL_NAME, newFullName)
        editor.putString(KEY_EMAIL, newEmail)
        editor.putString(KEY_PHONE, newPhone)
        editor.putString(KEY_CARD_NUMBER, newCardNumber)
        editor.putString(KEY_CARD_NAME, newCardName)
        editor.putString(KEY_DANA_NUMBER, newDanaNumber)
        editor.putString(KEY_DANA_NAME, newDanaName)
        editor.apply()

        Toast.makeText(context, "Pengaturan berhasil diperbarui!", Toast.LENGTH_LONG).show()
    }
    
    private fun loadUserData() {
        // Load data dari SharedPreferences
        fullName.setText(sharedPreferences.getString(KEY_FULL_NAME, "Adam Noverian"))
        email.setText(sharedPreferences.getString(KEY_EMAIL, "adamnoverian@gmail.com"))
        phone.setText(sharedPreferences.getString(KEY_PHONE, "856 1234 1234"))
        cardNumber.setText(sharedPreferences.getString(KEY_CARD_NUMBER, "1234 1234 1234 1234"))
        cardName.setText(sharedPreferences.getString(KEY_CARD_NAME, "Adam Noverian"))
        danaNumber.setText(sharedPreferences.getString(KEY_DANA_NUMBER, "0856 1234 1234"))
        danaName.setText(sharedPreferences.getString(KEY_DANA_NAME, "Adam Noverian"))
    }
}
