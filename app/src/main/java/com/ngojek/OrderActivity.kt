package com.ngojek // Pastikan package ini sesuai dengan folder projectmu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order) // Menghubungkan ke layout XML Confirm Ride

        // Sembunyikan ActionBar bawaan
        supportActionBar?.hide()

        // 1. Logika Tombol Close (X)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        btnClose.setOnClickListener {
            finish() // Kembali ke halaman sebelumnya (Booking)
        }

        // 2. Logika Tombol Continue
        val btnContinue = findViewById<Button>(R.id.btnContinue)
        btnContinue.setOnClickListener {
            // Aksi saat tombol Continue ditekan
            // Kita akan pindah ke halaman "Driver Found" atau "Driver Location"
            // Pastikan kamu nanti membuat Activity tujuan, misal namanya "DriverActivity"

            val intent = Intent(this, DriverActivity::class.java)
            startActivity(intent)
        }
    }
}