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
        enableEdgeToEdge()
        //Menghubungkan Activity dengan layout history_ride.xml
        setContentView(R.layout.history_ride)

        //Mengambil root view dari seluruh layout
        val root: View = findViewById(android.R.id.content)

        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //Mengammbil ImageView tombol back dari XML
        val btnBack = findViewById<ImageView>(R.id.icon_back)

        //Saat tombol back diklik pindah ke HomeActivity
        btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        //Mengambil 5 tombol “X” (ikon close) dari layout HistoryRide
        val close1 = findViewById<ImageView?>(R.id.close_icon2)
        val close2 = findViewById<ImageView?>(R.id.close_icon3)
        val close3 = findViewById<ImageView?>(R.id.close_icon4)
        val close4 = findViewById<ImageView?>(R.id.close_icon5)
        val close5 = findViewById<ImageView?>(R.id.close_icon6)

        //Setiap tombol X dihubungkan dengan fungsi setCloseAction
        //Ketika ditutup tampilkan toast “Item X ditutup
        setCloseAction(close1, "Item 1")
        setCloseAction(close2, "Item 2")
        setCloseAction(close3, "Item 3")
        setCloseAction(close4, "Item 4")
        setCloseAction(close5, "Item 5")
    }


    private fun setCloseAction(btn: ImageView?, name: String) {
        btn?.setOnClickListener {
            //Menampilkan pesan sesuai item yang ditekan
            Toast.makeText(this, "$name ditutup", Toast.LENGTH_SHORT).show()
        }
    }
}
