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

        // --- HISTORY BUTTON ---
        val btnHistoryIcon = findViewById<ImageView>(R.id.btn_history_icon)

        // --- SEARCH BUTTON ---
        val btnSearchDestination = findViewById<LinearLayout>(R.id.btn_search_destination)

        // --- SAVE ADDRESS BUTTON ---
        val btnSaveAddress = findViewById<LinearLayout>(R.id.section_save_address)


        // --- BOTTOM BAR ---
        val btnHome = findViewById<ImageView>(R.id.btn_home)
        val btnProfile = findViewById<ImageView>(R.id.btn_profile)
        val btnMotor = findViewById<MaterialCardView>(R.id.btn_motor)

        // ==========================
        //      EVENT LISTENERS
        // ==========================


        // HISTORY PAGE
        val openHistory = {
            startActivity(Intent(this, RideHistoryActivity::class.java))
        }
        btnHistoryIcon.setOnClickListener { openHistory() }

        // MAP PAGE
        btnMapIcon.setOnClickListener {
            startActivity(Intent(this, LocationPickActivity::class.java))
        }


        // SEARCH → GO TO PICK LOCATION
        btnSearchDestination.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        btnMotor.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        // HOME BUTTON (stay here)
        btnHome.setOnClickListener {
            // current page
        }

        // PROFILE BUTTON
        btnProfile.setOnClickListener {
            startActivity(Intent(this, UserSettingActivity::class.java))
        }
    }
}
