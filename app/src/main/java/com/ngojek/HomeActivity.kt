package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.card.MaterialCardView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

//Deklarasi Deklarasi class HomeActivity yang:
//1. Mewarisi AppCompatActivity
//2. Mengimplementasi BottomNavCallback
class HomeActivity : AppCompatActivity(), BottomNavCallback {

    //Variabel berisi ID layout tempat fragment akan ditampilkan
    private val CONTAINER_ID = R.id.fragment_container

    //Metode yang dipanggil saat Activity pertama kali dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        //Memanggil fungsi onCreate bawaan Android
        super.onCreate(savedInstanceState)
        //Menghubungkan Activity dengan layout activity_home.xml
        setContentView(R.layout.activity_home)

        //mengambil elemen UI berdasarkan ID yang ada di xml
        val btnMapIcon= findViewById<ImageView>(R.id.img_map_preview)
        val btnHistoryIcon = findViewById<ImageView>(R.id.btn_history_icon)
        val btnSearchDestination = findViewById<LinearLayout>(R.id.btn_search_destination)
        val btnSaveAddress = findViewById<LinearLayout>(R.id.section_save_address)
        val btnHome = findViewById<ImageView>(R.id.btn_home)
        val btnProfile = findViewById<ImageView>(R.id.btn_profile)
        val btnMotor = findViewById<MaterialCardView>(R.id.btn_motor)

        //Saat Activity dibuka, fragment pertama yang ditampilkan adalah HomeFragment
        loadFragment(HomeFragment())


        //Saat icon history ditekan membuka RideHistoryActivity
        btnHistoryIcon.setOnClickListener {
            startActivity(Intent(this, RideHistoryActivity::class.java))
        }

        //Saat klik map membuka halaman LocationPickActivity
        btnMapIcon.setOnClickListener {
            startActivity(Intent(this, LocationPickActivity::class.java))
        }

        //Saat klik search membuka halaman BookingActivity
        btnSearchDestination.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        //Saat klik Motor membuka halaman BookingActivity
        btnMotor.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        // Jika tombol Home ditekan:
        // 1. Tampilkan HomeFragment
        // 2. Ubah icon Home jadi biru (aktif)
        // 3. Ubah icon Profile jadi abu-abu (nonaktif)
        btnHome.setOnClickListener {
            loadFragment(HomeFragment())
            btnHome.setImageResource(R.drawable.alfian_rumah_main_biru)
            btnProfile.setImageResource(R.drawable.alfian_wong_main)
        }

        //  Jika tombol Profile ditekan:
        // 1. Tampilkan UserSettingFragment
        // 2. Ubah icon Home menjadi abu-abu (nonaktif)
        // 3. Ubah icon Profile menjadi biru (aktif)
        btnProfile.setOnClickListener {
            loadFragment(UserSettingFragment())
            btnHome.setImageResource(R.drawable.alfian_rumah_main)
            btnProfile.setImageResource(R.drawable.alfian_wong_main_biru)
        }
    }

    //Fungsi ini dipanggil dari fragment jika ingin mengaktifkan kembali icon home
    override fun onHomeSelectedFromFragment() {
        //Ambil ulang referensi tombol navbar
        val btnHome = findViewById<ImageView>(R.id.btn_home)
        val btnProfile = findViewById<ImageView>(R.id.btn_profile)

        btnHome.setImageResource(R.drawable.alfian_rumah_main_biru)
        btnProfile.setImageResource(R.drawable.alfian_wong_main)
    }

        //Dipakai untuk menampilkan fragment baru:
        // 1. beginTransaction() → mulai proses pergantian fragment
        // 2. replace() → ganti fragment lama dengan yang baru
        // 3. commit() → eksekusi
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(CONTAINER_ID, fragment)
            .commit()
    }
}
