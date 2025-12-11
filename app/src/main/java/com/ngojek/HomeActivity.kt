package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.card.MaterialCardView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment // Tambahkan Import ini

class HomeActivity : AppCompatActivity() {

    // ID untuk FrameLayout di layout Anda yang akan menampung Fragment
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
            // Ketika tombol home diklik, pastikan kembali ke tampilan utama (misalnya HomeFragment)
            // Jika HomeActivity hanya menampilkan HomeFragment, tidak perlu navigasi di sini.
        }

        // ✨ PERUBAHAN UTAMA: Memuat Fragment saat btnProfile diklik
        btnProfile.setOnClickListener {
            loadFragment(UserSettingFragment())
            btnHome.setImageResource(R.drawable.alfian_rumah_main)
        }
    }

    /**
     * Fungsi untuk mengganti (replace) Fragment di container
     */
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(CONTAINER_ID, fragment)
            .addToBackStack("user_settings") // Tag untuk back stack
            .commit()
    }
}