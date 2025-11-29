package com.ngojek

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RatingActivity : AppCompatActivity() {

    // Variabel untuk menyimpan nilai rating saat ini (1-5)
    private var currentRating = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_driver) // Pastikan nama layout XML sesuai
        supportActionBar?.hide()

        // 1. Inisialisasi Bintang dari XML
        val star1 = findViewById<ImageView>(R.id.star1)
        val star2 = findViewById<ImageView>(R.id.star2)
        val star3 = findViewById<ImageView>(R.id.star3)
        val star4 = findViewById<ImageView>(R.id.star4)
        val star5 = findViewById<ImageView>(R.id.star5)

        // 2. Masukkan ke dalam List agar mudah diatur (Looping)
        val stars = listOf(star1, star2, star3, star4, star5)

        // 3. Fungsi untuk Mengubah Warna Bintang
        fun updateStarColors(rating: Int) {
            currentRating = rating // Simpan nilai rating

            for (i in stars.indices) {
                if (i < rating) {
                    // Jika indeks kurang dari rating, warnai EMAS
                    stars[i].setColorFilter(Color.parseColor("#FFD700"))
                } else {
                    // Sisanya warnai ABU-ABU
                    stars[i].setColorFilter(Color.parseColor("#E0E0E0"))
                }
            }
        }

        // 4. Pasang Listener Klik pada Setiap Bintang
        star1.setOnClickListener {
            updateStarColors(1)
            Toast.makeText(this, "Rating: 1 Bintang", Toast.LENGTH_SHORT).show()
        }

        star2.setOnClickListener {
            updateStarColors(2)
            Toast.makeText(this, "Rating: 2 Bintang", Toast.LENGTH_SHORT).show()
        }

        star3.setOnClickListener {
            updateStarColors(3)
            Toast.makeText(this, "Rating: 3 Bintang", Toast.LENGTH_SHORT).show()
        }

        star4.setOnClickListener {
            updateStarColors(4)
            Toast.makeText(this, "Rating: 4 Bintang", Toast.LENGTH_SHORT).show()
        }

        star5.setOnClickListener {
            updateStarColors(5)
            Toast.makeText(this, "Rating: 5 Bintang! Mantap!", Toast.LENGTH_SHORT).show()

            // OPSIONAL: Jika klik bintang 5 langsung selesai dan kembali ke menu awal
            // finishRating()
        }
    }

    // Fungsi tambahan jika kamu ingin menutup halaman rating
    private fun finishRating() {
        // Misalnya kembali ke halaman awal (Home/Booking)
        // val intent = Intent(this, BookingActivity::class.java)
        // intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        // startActivity(intent)
        // finish()
    }
}