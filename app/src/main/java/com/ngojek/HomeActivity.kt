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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

class HomeActivity : AppCompatActivity() {

    private val CONTAINER_ID = R.id.fragment_container

    // Class data sederhana untuk menyimpan nama dan view layout-nya
    data class DestinationItem(val name: String, val layout: View)

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 1. Inisialisasi View
        val btnHistoryIcon = findViewById<ImageView>(R.id.btn_history_icon)
        // Perhatikan: btnSearchDestination sekarang kita pakai layoutnya saja, klik-nya kita handle via EditText
        val btnSearchLayout = findViewById<LinearLayout>(R.id.btn_search_destination)
        val etSearch = findViewById<EditText>(R.id.text_search_placeholder)

        val btnHome = findViewById<ImageView>(R.id.btn_home)
        val btnProfile = findViewById<ImageView>(R.id.btn_profile)
        val btnMotor = findViewById<MaterialCardView>(R.id.btn_motor)

        // Inisialisasi Item Lokasi (Pastikan ID sudah ditambahkan di XML)
        val layoutMieAyam = findViewById<LinearLayout>(R.id.item_mie_ayam)
        val layoutMasJo = findViewById<LinearLayout>(R.id.item_mas_jo)
        val layoutKwarcap = findViewById<LinearLayout>(R.id.item_kwarcap)

        // 2. Buat Daftar Lokasi untuk memudahkan filtering
        val destinationList = listOf(
            DestinationItem("Mie ayam", layoutMieAyam),
            DestinationItem("Mas Jo", layoutMasJo),
            DestinationItem("Kwarcap", layoutKwarcap)
        )

        // --- Logic Tombol Lain ---
        val openHistory = {
            startActivity(Intent(this, RideHistoryActivity::class.java))
        }
        btnHistoryIcon.setOnClickListener { openHistory() }

        btnMotor.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        btnProfile.setOnClickListener {
            loadFragment(UserSettingFragment())
            btnHome.setImageResource(R.drawable.alfian_rumah_main)
        }

        // --- LOGIC PENCARIAN (SEARCH SYSTEM) ---

        // Listener ketika text berubah
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase().trim()
                filterDestinations(query, destinationList)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Opsional: Klik item untuk pergi ke booking (Contoh untuk Mie Ayam)
        layoutMieAyam.setOnClickListener { navigateToBooking("Mie ayam") }
        layoutMasJo.setOnClickListener { navigateToBooking("Mas Jo") }
        layoutKwarcap.setOnClickListener { navigateToBooking("Kwarcap") }
    }

    // Fungsi untuk memfilter tampilan
    private fun filterDestinations(query: String, list: List<DestinationItem>) {
        for (item in list) {
            if (item.name.lowercase().contains(query)) {
                // Jika nama cocok dengan ketikan, TAMPILKAN
                item.layout.visibility = View.VISIBLE
            } else {
                // Jika tidak cocok, SEMBUNYIKAN
                item.layout.visibility = View.GONE
            }
        }
    }

    // Fungsi helper untuk pindah halaman saat item diklik
    private fun navigateToBooking(destinationName: String) {
        val intent = Intent(this, BookingActivity::class.java)
        intent.putExtra("DESTINATION_NAME", destinationName) // Kirim data jika perlu
        startActivity(intent)
    }

    private fun loadFragment(fragment: Fragment) {
        val container = findViewById<View>(R.id.fragment_container) // Ambil view container
        container.visibility = View.VISIBLE // Pastikan terlihat

        supportFragmentManager.beginTransaction()
            .replace(CONTAINER_ID, fragment)
            .addToBackStack("user_settings")
            .commit()
    }
}