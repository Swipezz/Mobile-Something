package com.ngojek
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ngojek.R


class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        supportActionBar?.hide()

        val btnClose = findViewById<ImageView>(R.id.btnClose)

        btnClose.setOnClickListener {
            finish()
        }

        val tvDest = findViewById<android.view.View>(R.id.tvDest)
        tvDest.setOnClickListener {
            Toast.makeText(this, "Buka pencarian lokasi...", Toast.LENGTH_SHORT).show()
        }
    }
}