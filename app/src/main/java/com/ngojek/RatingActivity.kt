package com.ngojek

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RatingActivity : AppCompatActivity() {

    private var currentRating = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_driver)
        supportActionBar?.hide()

        // 1. Inisialisasi View
        val star1 = findViewById<ImageView>(R.id.star1)
        val star2 = findViewById<ImageView>(R.id.star2)
        val star3 = findViewById<ImageView>(R.id.star3)
        val star4 = findViewById<ImageView>(R.id.star4)
        val star5 = findViewById<ImageView>(R.id.star5)

        // Tombol Confirm (Pastikan ID di XML sudah @+id/btnConfirm)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)

        val stars = listOf(star1, star2, star3, star4, star5)

        // Fungsi untuk mengubah warna bintang visual
        fun updateStarColors(rating: Int) {
            currentRating = rating // Simpan nilai rating terakhir

            for (i in stars.indices) {
                if (i < rating) {
                    stars[i].setColorFilter(Color.parseColor("#FFD700")) // Kuning
                } else {
                    stars[i].setColorFilter(Color.parseColor("#E0E0E0")) // Abu-abu
                }
            }
        }

        // 2. Setup Listener Bintang (Hanya ubah tampilan, JANGAN pindah activity dulu)
        star1.setOnClickListener {
            updateStarColors(1)
            Toast.makeText(this, "1 Bintang dipilih", Toast.LENGTH_SHORT).show()
        }

        star2.setOnClickListener {
            updateStarColors(2)
            Toast.makeText(this, "2 Bintang dipilih", Toast.LENGTH_SHORT).show()
        }

        star3.setOnClickListener {
            updateStarColors(3)
            Toast.makeText(this, "3 Bintang dipilih", Toast.LENGTH_SHORT).show()
        }

        star4.setOnClickListener {
            updateStarColors(4)
            Toast.makeText(this, "4 Bintang dipilih", Toast.LENGTH_SHORT).show()
        }

        star5.setOnClickListener {
            updateStarColors(5)
            Toast.makeText(this, "5 Bintang dipilih", Toast.LENGTH_SHORT).show()
        }

        // 3. Setup Tombol Confirm (Baru pindah activity di sini)
        btnConfirm.setOnClickListener {
            Toast.makeText(this, "Terima kasih atas rating $currentRating bintang!", Toast.LENGTH_SHORT).show()

            // Di sini nanti bisa tambahkan kode untuk kirim data rating ke Server/API

            finishRating()
        }
    }

    private fun finishRating() {
        val intent = Intent(this, HomeActivity::class.java)
        // Clear stack agar user tidak bisa back ke halaman rating
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}