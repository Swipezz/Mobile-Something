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

    //deklarasi variable yang akan digunakan di dalam fragment
    //private :hanya bisa diakses di dalam class ini
    //lateinit :variabel yang akan diinisasi
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

    //Ini adalah wadah penyimpanan data lokal
    //Digunakan untuk menyimpan: nama, email, nomor hp, dan nomor kartu
    //SharedPreferences :penyimpanan kecil yang tidak akan hilang meskipun aplikasi ditutup
    private lateinit var sharedPreferences: SharedPreferences

    //Ini membuka blok companion object—bagian khusus untuk menyimpan nilai tetap (konstanta)
    companion object {
        //Ini digunakan sebagai “nama variabel” untuk menyimpan data ke SharedPreferences.
        private const val PREF_NAME = "UserSettings"
        private const val KEY_FULL_NAME = "fullName"
        private const val KEY_EMAIL = "email"
        private const val KEY_PHONE = "phone"
        private const val KEY_CARD_NUMBER = "cardNumber"
        private const val KEY_CARD_NAME = "cardName"
        private const val KEY_DANA_NUMBER = "danaNumber"
        private const val KEY_DANA_NAME = "danaName"
    }

    //untuk mengganti implemaentasi fungsi onCreateView() bawaan dari Fragment
    override fun onCreateView(
        //Parameter Fungsi
        // inflater: LayoutInflater-> Objek yang bertugas “mengembangkan/membaca”
        // file XML layout dan mengubahnya menjadi objek View yang bisa ditampilkan
        // container: ViewGroup?->Parent layout tempat fragment akan ditempel
        // savedInstanceState: Bundle?-> Data yang disimpan jika fragment pernah dibuat ulang
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Fragment akan menampilkan layout dari file user_setting.xml
        return inflater.inflate(R.layout.user_setting, container, false)
    }

    //overide fun diguanakan ketika menggantifungsi yang sudah ada di kelas induk
    //onViewCreated() -> layout sudah jadi, siap dipakai
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Memanggil versi bawaan (default) dari fungsi onViewCreated milik kelas Fragment
        super.onViewCreated(view, savedInstanceState)

        // Inisiasi SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        //mengambil (menghubungkan) komponen-komponen UI dari layout XML ke dalam file Kotlin
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

        // Memanggil fungsi loadUserData() untuk mengambil data pengguna yang sudah disimpan
        // (biasanya dari SharedPreferences) dan menampilkannya pada EditText atau TextView
        loadUserData()

        //Memanggil fungsi enableTapToEdit() agar dapat Mengaktifkan fitur klik untuk setiap input supaya bisa diedit
        enableTapToEdit(fullName)
        enableTapToEdit(email)
        enableTapToEdit(phone)
        enableTapToEdit(cardNumber)
        enableTapToEdit(cardName)
        enableTapToEdit(danaNumber)
        enableTapToEdit(danaName)

        //listener untuk tombol-tombol di fragment UserSettingFragment, maksudnya setiap tombol ketika diklik akan melakukan aksi
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


    //memberitahu activity bahwa fragment ingin kembali ke Home, supaya bottom navigation diperbarui
    private fun goToHomeFragment() {
        (activity as? BottomNavCallback)?.onHomeSelectedFromFragment()
        
        // Hide fragment container saat kembali ke home
        val fragmentContainer = requireActivity().findViewById<View>(R.id.fragment_container)
        //
        //visibility = View.GONE ->menyembunyikan view fragment container, sehingga konten fragment tidak terlihat
        //bertujuan untuk saat kembali ke home fragment container fragment disembunyikan agar tampilan home lebih bersih
        fragmentContainer?.visibility = View.GONE
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
    }


    //Fungsi ini memudahkan untuk mengaktifkan atau menonaktifkan kemampuan edit pada EditText
    private fun setEditable(editText: EditText, enable: Boolean) {
        editText.isFocusable = enable
        editText.isFocusableInTouchMode = enable
        editText.isCursorVisible = enable
    }

    //Awalnya EditText tidak bisa diketik, tapi bisa diklik.
    private fun enableTapToEdit(editText: EditText) {
        editText.isFocusable = false
        editText.isFocusableInTouchMode = false
        editText.isCursorVisible = false
    //Saat diklik baru bisa editable
        editText.setOnClickListener {
            editText.isFocusable = true
            editText.isFocusableInTouchMode = true
            editText.isCursorVisible = true
            editText.requestFocus()
        }
    }

    //Mengunci semua kolom setelah save / cancel.
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
