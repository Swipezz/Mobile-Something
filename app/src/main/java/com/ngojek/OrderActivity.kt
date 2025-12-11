package com.ngojek

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

        // 1. Inisialisasi Input Pembayaran
        // DEMO NOTE: "Sistem menyediakan opsi pembayaran yang dinamis (via Spinner/Dropdown)."
        val spinnerEwallet = findViewById<Spinner>(R.id.spinnerEwallet)
        val spinnerBank = findViewById<Spinner>(R.id.spinnerBank)

        val btnContinueOrder = findViewById<Button>(R.id.btnContinueOrder)

        // 2. LOGIKA INTI: Validasi Transaksi
        btnContinueOrder.setOnClickListener {
            // Mengambil nilai string yang sedang dipilih user
            val selectedWallet = spinnerEwallet.selectedItem.toString()
            val selectedBank = spinnerBank.selectedItem.toString()

            // DEMO HIGHLIGHT: VALIDASI PEMBAYARAN
            // Jelaskan: "Ini logic krusial, Pak/Bu. Sistem akan MEMBLOKIR order jika
            // user belum memilih metode pembayaran sama sekali (masih default).
            // Tujuannya untuk mencegah error transaksi null di backend."
            if (selectedWallet == "Pilih E-Wallet..." && selectedBank == "Pilih Bank...") {
                // Feedback error ke user
                Toast.makeText(this, "Pilih metode pembayaran dulu", Toast.LENGTH_SHORT).show()
            } else {

                // DEMO NOTE: "Jika validasi lolos, barulah sistem menghubungkan ke Driver."
                val intent = Intent(this, DriverActivity::class.java)
                startActivity(intent)
            }
        }
    }
}