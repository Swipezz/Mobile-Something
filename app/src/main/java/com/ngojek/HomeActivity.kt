package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnMapIcon= findViewById<ImageView>(R.id.img_map_preview)

        val btnHistoryIcon = findViewById<ImageView>(R.id.btn_history_icon)

        val btnSearchDestination = findViewById<LinearLayout>(R.id.btn_search_destination)

        val btnSaveAddress = findViewById<LinearLayout>(R.id.section_save_address)

        val btnHome = findViewById<ImageView>(R.id.btn_home)
        val btnProfile = findViewById<ImageView>(R.id.btn_profile)
        val btnMotor = findViewById<MaterialCardView>(R.id.btn_motor)

        val openHistory = {
            startActivity(Intent(this, RideHistoryActivity::class.java))
        }
        btnHistoryIcon.setOnClickListener { openHistory() }

        btnMapIcon.setOnClickListener {
            startActivity(Intent(this, LocationPickActivity::class.java))
        }

        btnSearchDestination.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        btnMotor.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        btnHome.setOnClickListener {

        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, UserSettingActivity::class.java))
        }
    }
}
