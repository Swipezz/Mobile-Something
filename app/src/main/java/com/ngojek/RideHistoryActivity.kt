package com.ngojek

import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RideHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DEMO NOTE: "Sama seperti halaman map, halaman history ini juga menggunakan
        // Edge-to-Edge UI supaya tampilannya konsisten dan modern di seluruh aplikasi."
        enableEdgeToEdge()
        setContentView(R.layout.history_ride)

        val root: View = findViewById(android.R.id.content)

        // Mengatur padding agar konten tidak tertutup system bar (jam/baterai)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigasi Kembali ke Home
        val btnBack = findViewById<ImageView>(R.id.icon_back)
        btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        // DEMO NOTE: "Untuk prototype ini, saya menyiapkan beberapa item dummy
        // agar user bisa melihat contoh tampilan riwayat perjalanan."
        val item1 = findViewById<View>(R.id.history_item1)
        val item2 = findViewById<View>(R.id.history_item2)
        val item3 = findViewById<View>(R.id.history_item3)
        val item4 = findViewById<View>(R.id.history_item4)
        val item5 = findViewById<View>(R.id.history_item5)
        
        val close1 = findViewById<ImageView?>(R.id.close_icon2)
        val close2 = findViewById<ImageView?>(R.id.close_icon3)
        val close3 = findViewById<ImageView?>(R.id.close_icon4)
        val close4 = findViewById<ImageView?>(R.id.close_icon5)
        val close5 = findViewById<ImageView?>(R.id.close_icon6)

        // DEMO HIGHLIGHT: PENGGUNAAN HELPER FUNCTION
        // Jelaskan: "Supaya kode saya rapi dan tidak berulang-ulang (Clean Code),
        // saya membuat fungsi khusus 'setCloseAction' untuk menangani klik pada setiap item."
        setCloseAction(close1, item1)
        setCloseAction(close2, item2)
        setCloseAction(close3, item3)
        setCloseAction(close4, item4)
        setCloseAction(close5, item5)
    }

    // Fungsi Helper untuk mengurangi duplikasi kode (DRY Principle)
    private fun setCloseAction(btn: ImageView?, itemView: View?) {
        btn?.setOnClickListener {
            itemView?.visibility = View.GONE
        }
    }
}