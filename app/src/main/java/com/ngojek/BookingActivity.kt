package com.ngojek // Sesuaikan package kamu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        supportActionBar?.hide()

        val btnClose = findViewById<ImageView>(R.id.btnClose)
        btnClose.setOnClickListener {
            finish()
        }

        val btnContinue = findViewById<Button>(R.id.btnContinueBooking)

        btnContinue.setOnClickListener {

            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }
    }
}