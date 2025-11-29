package com.ngojek // Sesuaikan package

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DriverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_location) // Pastikan nama layout XML sesuai
        supportActionBar?.hide()

        // Tampilkan pesan agar user tahu harus menunggu
        Toast.makeText(this, "Simulasi perjalanan dimulai... (Tunggu 10 detik)", Toast.LENGTH_LONG).show()

        // LOGIC TIMER 10 DETIK
        Handler(Looper.getMainLooper()).postDelayed({

            // Aksi yang dilakukan setelah 10 detik (10000 ms) berlalu:
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
            finish() // Menutup halaman Driver agar tidak bisa kembali (Back)

        }, 10000) // 10000 milidetik = 10 detik
    }
}