package com.ngojek


import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class UserSettingActivity : AppCompatActivity() {

    private lateinit var fullName: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var cardNumber: EditText
    private lateinit var danaNumber: EditText

    private lateinit var btnSave: LinearLayout
    private lateinit var btnCancel: LinearLayout
    private lateinit var btnHome: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_setting)

        // 🔗 Hubungkan ID
        fullName = findViewById(R.id.rvm66v2klmh)
        email = findViewById(R.id.rlwj24u46c68)
        phone = findViewById(R.id.rcnhr5ucwpeo)
        cardNumber = findViewById(R.id.r0m4h1eoxmmcq)
        danaNumber = findViewById(R.id.rcnhr5ucwpeo)


        btnSave = findViewById(R.id.rx1gtb8fjvjn)
        btnCancel = findViewById(R.id.rdl8u8g6m3x7)
        btnHome = findViewById(R.id.btn_home)


        // Aktifkan sistem tap-to-edit
        enableTapToEdit(fullName)
        enableTapToEdit(email)
        enableTapToEdit(phone)
        enableTapToEdit(cardNumber)
        enableTapToEdit(danaNumber)

        // Tombol save → matikan mode edit lagi
        btnSave.setOnClickListener {
            disableAll()
        }

        // Tombol cancel → matikan mode edit juga
        btnCancel.setOnClickListener {
            disableAll()
        }

        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //  Fungsi agar kolom tidak bisa diubah
    private fun setEditable(editText: EditText, enable: Boolean) {
        editText.isFocusable = enable
        editText.isFocusableInTouchMode = enable
        editText.isCursorVisible = enable
        editText.isEnabled = enable
    }

    // Fitur Tap-to-Edit (inti)
    private fun enableTapToEdit(editText: EditText) {
        // Awalnya non-editable
        setEditable(editText, false)

        // Ketika user men-tap → langsung bisa edit
        editText.setOnClickListener {
            setEditable(editText, true)
            editText.requestFocus()
        }
    }

    // 🔒 Setelah save/cancel → semua terkunci lagi
    private fun disableAll() {
        setEditable(fullName, false)
        setEditable(email, false)
        setEditable(phone, false)
        setEditable(cardNumber, false)
        setEditable(danaNumber, false)
    }

}
