package com.swipezz.mobilesomething // Sesuaikan dengan package name projectmu
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ngojek.R


class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // Sembunyikan ActionBar bawaan agar mirip desain (Full Custom)
        supportActionBar?.hide()

        // Inisialisasi Tombol Close
        val btnClose = findViewById<ImageView>(R.id.btnClose)

        btnClose.setOnClickListener {
            // Aksi ketika tombol close ditekan
            finish() // Menutup activity ini dan kembali ke menu sebelumnya
        }

        // Contoh interaksi sederhana pada input tujuan
        val tvDest = findViewById<android.view.View>(R.id.tvDest)
        tvDest.setOnClickListener {
            Toast.makeText(this, "Buka pencarian lokasi...", Toast.LENGTH_SHORT).show()
        }
    }
}