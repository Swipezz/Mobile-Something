package com.ngojek

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

class HomeActivity : AppCompatActivity() {

    // ID Container di XML tempat Fragment akan "ditempel"
    private val CONTAINER_ID = R.id.fragment_container

    data class DestinationItem(val name: String, val layout: View)

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 1. Inisialisasi View
        val btnHistoryIcon = findViewById<ImageView>(R.id.btn_history_icon)
        val btnSearchLayout = findViewById<LinearLayout>(R.id.btn_search_destination)
        val etSearch = findViewById<EditText>(R.id.text_search_placeholder)

        val btnHome = findViewById<ImageView>(R.id.btn_home)
        val btnProfile = findViewById<ImageView>(R.id.btn_profile) // FOKUS FRAGMENT DISINI
        val btnMotor = findViewById<MaterialCardView>(R.id.btn_motor)

        val layoutMieAyam = findViewById<LinearLayout>(R.id.item_mie_ayam)
        val layoutMasJo = findViewById<LinearLayout>(R.id.item_mas_jo)
        val layoutKwarcap = findViewById<LinearLayout>(R.id.item_kwarcap)

        val destinationList = listOf(
            DestinationItem("Mie ayam", layoutMieAyam),
            DestinationItem("Mas Jo", layoutMasJo),
            DestinationItem("Kwarcap", layoutKwarcap)
        )

        // --- Logic Navigasi Biasa (Activity) ---
        btnHistoryIcon.setOnClickListener {
            startActivity(Intent(this, RideHistoryActivity::class.java))
        }

        btnMotor.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        // --- DEMO POINT: NAVIGASI FRAGMENT (PROFILE) ---
        // Jelaskan: "Untuk halaman Profile, kita tidak pindah Activity.
        // Kita menggunakan Fragment agar transisi lebih mulus dan ringan."
        btnProfile.setOnClickListener {
            loadFragment(UserSettingFragment())

            // Opsional: Visual feedback mengubah ikon home
            btnHome.setImageResource(R.drawable.alfian_rumah_main)
        }

        // --- LOGIC PENCARIAN (SEARCH SYSTEM) ---
        // Jelaskan singkat: "Fitur search real-time menggunakan TextWatcher"
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase().trim()
                filterDestinations(query, destinationList)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        layoutMieAyam.setOnClickListener { navigateToBooking("Mie ayam") }
        layoutMasJo.setOnClickListener { navigateToBooking("Mas Jo") }
        layoutKwarcap.setOnClickListener { navigateToBooking("Kwarcap") }
    }

    private fun filterDestinations(query: String, list: List<DestinationItem>) {
        for (item in list) {
            if (item.name.lowercase().contains(query)) {
                item.layout.visibility = View.VISIBLE
            } else {
                item.layout.visibility = View.GONE
            }
        }
    }

    private fun navigateToBooking(destinationName: String) {
        val intent = Intent(this, BookingActivity::class.java)
        intent.putExtra("DESTINATION_NAME", destinationName)
        startActivity(intent)
    }

    // --- DEMO HIGHLIGHT: FUNGSI UTAMA FRAGMENT MANAGER ---
    private fun loadFragment(fragment: Fragment) {
        val container = findViewById<View>(R.id.fragment_container)
        container.visibility = View.VISIBLE

        // LOGIC PENTING:
        // 1. beginTransaction(): Memulai proses perubahan UI
        // 2. replace(): Mengganti isi container lama dengan Fragment baru
        // 3. addToBackStack(): PENTING! Agar saat tombol Back HP ditekan,
        //    dia kembali ke Home, bukan menutup aplikasi.
        // 4. commit(): Eksekusi perubahan.
        supportFragmentManager.beginTransaction()
            .replace(CONTAINER_ID, fragment)
            .addToBackStack("user_settings")
            .commit()
    }
}