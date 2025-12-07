package com.ngojek // Sesuaikan package

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        supportActionBar?.hide()

        val btnClose = findViewById<ImageView>(R.id.btnClose)
        btnClose.setOnClickListener { finish() }

        val spinnerEwallet = findViewById<Spinner>(R.id.spinnerEwallet)
        val spinnerBank = findViewById<Spinner>(R.id.spinnerBank)

        val btnContinueOrder = findViewById<Button>(R.id.btnContinueOrder)

        btnContinueOrder.setOnClickListener {
            val selectedWallet = spinnerEwallet.selectedItem.toString()
            val selectedBank = spinnerBank.selectedItem.toString()

             if (selectedWallet == "Pilih E-Wallet..." && selectedBank == "Pilih Bank...") {
                 Toast.makeText(this, "Pilih metode pembayaran dulu", Toast.LENGTH_SHORT).show()
             } else {

            val intent = Intent(this, DriverActivity::class.java)
            startActivity(intent)
             }
        }
    }
}