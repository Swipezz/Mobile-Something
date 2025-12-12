package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        supportActionBar?.hide()

        val btnClose = findViewById<ImageView>(R.id.btnClose)
        btnClose.setOnClickListener { finish() }

        // =========================================================================
        // DEMO HIGHLIGHT 1: MENERIMA DATA ANTAR ACTIVITY (INTENT)
        // =========================================================================

        // Jelaskan: "Di sini logic penerimaan data bekerja. Saat Activity ini dibuat,
        // sistem langsung membongkar 'paket' (Intent) yang dikirim dari BookingActivity."
        val originName = intent.getStringExtra("EXTRA_ORIGIN")
        val destinationName = intent.getStringExtra("EXTRA_DESTINATION")

        // Inisialisasi TextView untuk judul lokasi
        val tvOrigin = findViewById<TextView>(R.id.tvOriginTitle)
        val tvDest = findViewById<TextView>(R.id.tvDestTitle)

        // Jelaskan: "Data yang diterima langsung kita tampilkan ke UI.
        // Penggunaan operator '?:' (Elvis Operator) adalah safety logic:
        // jika data null/error, aplikasi tidak crash melainkan menampilkan teks default."
        tvOrigin.text = originName ?: "Lokasi Jemput"
        tvDest.text = destinationName ?: "Lokasi Tujuan"

        // =========================================================================
        // DEMO HIGHLIGHT 2: LOGIC PEMBAYARAN (BUSINESS LOGIC)
        // =========================================================================

        val spinnerEwallet = findViewById<Spinner>(R.id.spinnerEwallet)
        val spinnerBank = findViewById<Spinner>(R.id.spinnerBank)
        val btnContinueOrder = findViewById<Button>(R.id.btnContinueOrder)

        btnContinueOrder.setOnClickListener {
            // Mengambil status pilihan user saat ini
            val selectedWallet = spinnerEwallet.selectedItem.toString()
            val selectedBank = spinnerBank.selectedItem.toString()

            // Jelaskan: "Ini adalah 'Gatekeeper Logic'. Sistem memvalidasi apakah user
            // sudah memilih metode pembayaran. Jika user membiarkan keduanya dalam posisi default
            // (Pilih E-Wallet/Pilih Bank), sistem akan MENOLAK pesanan."
            if (selectedWallet == "Pilih E-Wallet..." && selectedBank == "Pilih Bank...") {
  
                // Feedback Error ke User
                Toast.makeText(this, "Pilih metode pembayaran dulu", Toast.LENGTH_SHORT).show()

            } else {
                // Jelaskan: "Jika validasi lolos, barulah kita panggil DriverActivity."
                val intent = Intent(this, DriverActivity::class.java)
                startActivity(intent)
            }
        }
    }
}