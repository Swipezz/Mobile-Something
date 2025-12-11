package com.ngojek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment

class UserSettingFragment : Fragment() {

    // Deklarasi Views
    private lateinit var iconBack: ImageView
    private lateinit var fullName: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var cardNumber: EditText
    // Catatan: cardName dan danaName tidak diimplementasikan dengan tap-to-edit
    // karena di XML sebelumnya mereka memiliki atribut 'text' bukan 'hint'.
    private lateinit var cardName: EditText
    private lateinit var danaNumber: EditText
    private lateinit var danaName: EditText

    private lateinit var btnSave: LinearLayout
    private lateinit var btnCancel: LinearLayout


    // =======================================================
    // LIFECYCLE FRAGMENT
    // =======================================================

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Asumsi layout XML Anda telah disiapkan untuk Fragment
        // Anda mungkin perlu mengganti R.layout.user_setting jika Anda membuat file baru (misalnya R.layout.fragment_user_setting)
        return inflater.inflate(R.layout.user_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // =======================================================
        // INISIALISASI VIEWS (PENTING: Gunakan 'view.findViewById')
        // =======================================================

        // Asumsi icon back adalah ImageView dengan ID ra4wqg4z93d5 (ikon 'X' di header)
        iconBack = view.findViewById(R.id.icon_back)

        // Inisialisasi EditTexts
        fullName = view.findViewById(R.id.full_name)
        email = view.findViewById(R.id.email_address)
        phone = view.findViewById(R.id.phone_number)
        cardNumber = view.findViewById(R.id.credit_card_number)
        cardName = view.findViewById(R.id.card_name)
        danaNumber = view.findViewById(R.id.dana_number)
        danaName = view.findViewById(R.id.dana_name)

        // Inisialisasi Tombol (LinearLayout yang dibuat clickable)
        btnSave = view.findViewById(R.id.outline_save)          // Tombol Save (Warna Biru)
        btnCancel = view.findViewById(R.id.outline_cancel)  // Tombol Cancel (Outline)



        // Terapkan logika tap-to-edit hanya pada kolom yang ingin diubah
        enableTapToEdit(fullName)
        enableTapToEdit(email)
        enableTapToEdit(phone)
        enableTapToEdit(cardNumber)
        enableTapToEdit(danaNumber)

        // Secara default, tombol simpan dan batal biasanya disembunyikan
        // sebelum pengguna melakukan edit atau validasi. Anda bisa menambahkan
        // logika visibility di sini jika diperlukan.
        // Contoh: btnSave.visibility = View.GONE


        // =======================================================
        // LISTENER TOMBOL
        // =======================================================

        // Listener Tombol Kembali (Ikon 'X')
        iconBack.setOnClickListener {
            // Menghapus Fragment ini dari back stack dan kembali ke tampilan Home
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Listener Tombol Simpan
        btnSave.setOnClickListener {
            // Lakukan validasi dan ambil data di sini
            saveUserData()
            disableAll() // Nonaktifkan mode edit setelah disimpan
        }

        // Listener Tombol Batal
        btnCancel.setOnClickListener {
            // Mungkin Anda ingin me-reset data ke nilai awal sebelum memanggil disableAll()
            disableAll() // Nonaktifkan mode edit
            Toast.makeText(context, "Perubahan dibatalkan.", Toast.LENGTH_SHORT).show()
        }
    }


    // =======================================================
    // PRIVATE METHODS (FUNGSI PEMBANTU)
    // =======================================================

    private fun setEditable(editText: EditText, enable: Boolean) {
        editText.isFocusable = enable
        editText.isFocusableInTouchMode = enable
        editText.isCursorVisible = enable
        editText.isEnabled = enable
    }

    private fun enableTapToEdit(editText: EditText) {
        // Set awal: tidak bisa diedit
        setEditable(editText, false)

        // Listener: ketika diklik, aktifkan mode edit
        editText.setOnClickListener {
            setEditable(editText, true)
            editText.requestFocus()
            // Anda mungkin ingin menampilkan btnSave dan btnCancel di sini
        }
    }

    private fun disableAll() {
        // Nonaktifkan mode edit untuk semua kolom utama
        setEditable(fullName, false)
        setEditable(email, false)
        setEditable(phone, false)
        setEditable(cardNumber, false)
        setEditable(danaNumber, false)

        // Sembunyikan tombol simpan dan batal jika sudah selesai
        // Contoh: btnSave.visibility = View.GONE
    }

    private fun saveUserData() {
        val newFullName = fullName.text.toString()
        val newEmail = email.text.toString()
        val newPhone = phone.text.toString()
        // ... ambil data lainnya ...

        // TODO: Lakukan validasi data (misalnya cek email kosong atau format)
        if (newFullName.isBlank()) {
            Toast.makeText(context, "Nama lengkap tidak boleh kosong.", Toast.LENGTH_SHORT).show()
            return // Jangan simpan jika validasi gagal
        }

        // TODO: Kirim data ke API atau database (Logika bisnis)

        Toast.makeText(context, "Pengaturan pengguna berhasil diperbarui!", Toast.LENGTH_LONG).show()
    }
}