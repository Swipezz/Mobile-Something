package com.ngojek

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RatingActivity : AppCompatActivity() {

    // DEMO NOTE: "State Management sederhana. Kita butuh variabel ini untuk
    // 'mengingat' berapa bintang yang dipilih user sebelum tombol Confirm ditekan."
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
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)

        val stars = listOf(star1, star2, star3, star4, star5)

        // DEMO HIGHLIGHT: ALGORITMA PERUBAHAN WARNA
        // Jelaskan: "Fungsi ini adalah otak visualnya. Dia melakukan looping:
        // Jika saya klik bintang 3, maka bintang 1, 2, & 3 diubah jadi Kuning (Gold),
        // sisanya jadi Abu-abu. Ini memberikan efek visual rating yang nyata."
        fun updateStarColors(rating: Int) {
            currentRating = rating

            for (i in stars.indices) {
                if (i < rating) {
                    stars[i].setColorFilter(Color.parseColor("#FFD700")) // Kuning Emas
                } else {
                    stars[i].setColorFilter(Color.parseColor("#E0E0E0")) // Abu-abu
                }
            }
        }

        // 2. Setup Listener Bintang (Interaksi Lokal)
        // Jelaskan: "Saat bintang diklik, kita hanya update tampilan & variabel.
        // Kita BELUM mengirim data ke server/pindah halaman."
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

        // 3. Setup Tombol Confirm (Finalisasi)
        // Jelaskan: "Ini adalah tombol komitmen. Saat ditekan, barulah transaksi dianggap selesai."
        btnConfirm.setOnClickListener {
            Toast.makeText(this, "Terima kasih atas rating $currentRating bintang!", Toast.LENGTH_SHORT).show()

            // (Disini tempat API Call untuk kirim rating ke database)

            finishRating()
        }
    }

    // DEMO HIGHLIGHT: NAVIGASI & KEAMANAN UX
    private fun finishRating() {
        val intent = Intent(this, HomeActivity::class.java)

        // Jelaskan: "Sangat Penting: Kita pakai FLAG_ACTIVITY_CLEAR_TOP.
        // Tujuannya menghapus 'riwayat' perjalanan. Jadi kalau user tekan tombol Back di HP,
        // dia TIDAK BISA kembali ke halaman rating ini lagi (karena ordernya sudah selesai)."
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}