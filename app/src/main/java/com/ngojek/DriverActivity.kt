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
        setContentView(R.layout.activity_driver_location) // Pastikan nama layout XML sesuai
        supportActionBar?.hide()

        Toast.makeText(this, "Simulasi perjalanan dimulai... (Tunggu 10 detik)", Toast.LENGTH_LONG).show()


        Handler(Looper.getMainLooper()).postDelayed({


            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
            finish()

        }, 10000)
    }
}