package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.card.MaterialCardView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class HomeActivity : AppCompatActivity(), BottomNavCallback {

    private val CONTAINER_ID = R.id.fragment_container

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

        // Default fragment pertama (HomeFragment)
        loadFragment(HomeFragment())

        // ====================================
        // BUTTON LISTENER
        // ====================================

        btnHistoryIcon.setOnClickListener {
            startActivity(Intent(this, RideHistoryActivity::class.java))
        }

        btnMapIcon.setOnClickListener {
            startActivity(Intent(this, LocationPickActivity::class.java))
        }

        btnSearchDestination.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        btnMotor.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        // ===== HOME FRAGMENT =====
        btnHome.setOnClickListener {
            loadFragment(HomeFragment())
            btnHome.setImageResource(R.drawable.alfian_rumah_main_biru)
            btnProfile.setImageResource(R.drawable.alfian_wong_main)
        }

        // ===== PROFILE FRAGMENT =====
        btnProfile.setOnClickListener {
            loadFragment(UserSettingFragment())
            btnHome.setImageResource(R.drawable.alfian_rumah_main)
            btnProfile.setImageResource(R.drawable.alfian_wong_main_biru)
        }
    }

    override fun onHomeSelectedFromFragment() {
        val btnHome = findViewById<ImageView>(R.id.btn_home)
        val btnProfile = findViewById<ImageView>(R.id.btn_profile)

        btnHome.setImageResource(R.drawable.alfian_rumah_main_biru)
        btnProfile.setImageResource(R.drawable.alfian_wong_main)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(CONTAINER_ID, fragment)
            .commit()
    }
}
