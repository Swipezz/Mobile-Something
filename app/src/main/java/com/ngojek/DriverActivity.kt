package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DriverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_location)
        supportActionBar?.hide()

        // DEMO NOTE: "Berikan notifikasi agar user tahu sistem sedang berjalan (simulasi trip)."
        Toast.makeText(this, "Simulasi perjalanan dimulai... (Tunggu 10 detik)", Toast.LENGTH_LONG).show()

        // DEMO NOTE: "Kita menggunakan Handler untuk membuat delay selama 10 detik (10000ms).
        // Ini fungsinya untuk mensimulasikan waktu perjalanan driver di dunia nyata."
        Handler(Looper.getMainLooper()).postDelayed({

            // DEMO NOTE: "Setelah 10 detik berlalu (dianggap sudah sampai tujuan),
            // sistem otomatis mengarahkan user ke halaman Rating."
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
            finish()

        }, 10000)
    }
}