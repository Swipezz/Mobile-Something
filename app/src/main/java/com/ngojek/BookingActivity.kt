package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookingActivity : AppCompatActivity() {

    // DEMO NOTE: "Disini kita menggunakan List of TripHistory (bukan String biasa),
    // tujuannya agar bisa menyimpan SEPASANG data: Lokasi Jemput & Tujuan dalam satu riwayat."
    private val savedTrips = mutableListOf<TripHistory>()
    private lateinit var adapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        supportActionBar?.hide()

        val etOrigin = findViewById<EditText>(R.id.etOrigin)
        val etDest = findViewById<EditText>(R.id.etDest)
        val rvLocations = findViewById<RecyclerView>(R.id.rvLocations)
        
        // Get destination from intent if available
        val destinationFromIntent = intent.getStringExtra("DESTINATION_NAME")
        if (!destinationFromIntent.isNullOrEmpty()) {
            etDest.setText(destinationFromIntent)
        }

// 1. Setup Adapter & Logic Klik Item
        // DEMO NOTE: "Ini fitur UX penting: Saat user mengklik salah satu item di history,
        // aplikasi otomatis mengisi KEDUA kolom (Jemput & Tujuan) sekaligus."
        adapter = LocationAdapter(savedTrips) { selectedTrip ->
            // Saat item riwayat diklik, ISIKAN KEDUA KOLOM SEKALIGUS
            etOrigin.setText(selectedTrip.origin)
            etDest.setText(selectedTrip.destination)

            Toast.makeText(this, "Rute terpilih: ${selectedTrip.origin} -> ${selectedTrip.destination}", Toast.LENGTH_SHORT).show()
        }

        rvLocations.layoutManager = LinearLayoutManager(this)
        rvLocations.adapter = adapter

        // 3. Logika Simpan (Add a destination)
        val layoutActions = findViewById<LinearLayout>(R.id.layoutActions)
        val btnAddAction = layoutActions.getChildAt(1) // Tombol "Add a destination"

        btnAddAction.setOnClickListener {
            val textOrigin = etOrigin.text.toString()
            val textDest = etDest.text.toString()

            // Validasi: Pastikan minimal salah satu terisi (atau keduanya, sesuai kebutuhanmu)
            if (textOrigin.isNotEmpty() || textDest.isNotEmpty()) {

                // DEMO NOTE: "Kita membungkus input Jemput & Tujuan menjadi satu objek 'newTrip',
                // lalu objek itulah yang disimpan ke dalam list RecyclerView."
                val newTrip = TripHistory(
                    origin = textOrigin,
                    destination = textDest
                )

                // Simpan paket tersebut ke list
                savedTrips.add(newTrip)
                adapter.notifyItemInserted(savedTrips.size - 1)

                // Bersihkan input setelah simpan
                etOrigin.setText("")
                etDest.setText("")

                Toast.makeText(this, "Rute berhasil disimpan!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Isi lokasi dulu ya sebelum disimpan", Toast.LENGTH_SHORT).show()
            }
        }

        // ... Sisa kode lainnya (Tombol Map, Close, Continue) tetap sama ...
        val btnSelectMap = findViewById<LinearLayout>(R.id.btnSelectMap)
        btnSelectMap.setOnClickListener {
            val intent = Intent(this, LocationPickActivity::class.java)
            startActivity(intent)
        }

        val btnClose = findViewById<ImageView>(R.id.btnClose)
        btnClose.setOnClickListener { finish() }

        val btnContinue = findViewById<Button>(R.id.btnContinueBooking)
        btnContinue.setOnClickListener {

            // DEMO NOTE: "Validasi Wajib: Kita memastikan user tidak bisa lanjut order
            // kalau lokasi jemput atau tujuan masih kosong."
            if (etOrigin.text.isNotEmpty() && etDest.text.isNotEmpty()) {
                val intent = Intent(this, OrderActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Mohon isi lokasi jemput & tujuan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}