package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class LocationPickActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DEMO HIGHLIGHT: "Modern UI Experience"
        // Jelaskan: "Kami menggunakan 'enableEdgeToEdge' agar tampilan Peta terlihat luas
        // dan imersif sampai ke belakang status bar (jam/baterai) dan navigasi bawah."
        enableEdgeToEdge()
        setContentView(R.layout.pick_location)

        // Logic untuk menangani padding agar tombol tidak tertutup system bar
        val root: View = findViewById(android.R.id.content)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi Komponen
        val btnSearchContainer = findViewById<LinearLayout>(R.id.btn_search_destination)
        val etSearch = findViewById<EditText>(R.id.et_search)
        val btnSearch = findViewById<ImageView>(R.id.btn_search)
        val btnConfirm = findViewById<MaterialButton>(R.id.btn_confirm)

        btnSearchContainer.setOnClickListener {
            Toast.makeText(this, "Klik search bar", Toast.LENGTH_SHORT).show()
        }

        // DEMO NOTE: "Simulasi Pencarian Lokasi"
        // Jelaskan: "Di prototype ini, saat user mencari lokasi, kita lakukan validasi input.
        // Jika input ada, kita berikan feedback visual bahwa sistem sedang mencari titik koordinat."
        btnSearch.setOnClickListener {
            val text = etSearch.text.toString()

            if (text.isEmpty()) {
                Toast.makeText(this, "Masukkan lokasi dulu", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Mencari: $text", Toast.LENGTH_SHORT).show()
            }
        }

        // DEMO NOTE: "Konfirmasi Pinpoint Map"
        // Jelaskan: "Setelah user menentukan titik jemput yang pas di peta,
        // tombol Confirm akan membawa data tersebut ke halaman Order Summary."
        btnConfirm.setOnClickListener {
            Toast.makeText(this, "Lokasi dikonfirmasi", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, OrderActivity::class.java))
        }
    }
}