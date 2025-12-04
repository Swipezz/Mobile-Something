package com.ngojek
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ngojek.LocationAdapter

class BookingActivity : AppCompatActivity() {

    // Siapkan wadah untuk data lokasi
    private val savedLocations = mutableListOf<String>()
    private lateinit var adapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        supportActionBar?.hide()

        // 1. Inisialisasi Komponen
        val etDest = findViewById<EditText>(R.id.etDest)
        val btnAddDest = findViewById<LinearLayout>(R.id.layoutActions).getChildAt(1) // Mengambil tombol "Add a destination"
        // ATAU lebih aman beri ID di LinearLayout tombol Add tadi, misal: btnAddDestination

        val rvLocations = findViewById<RecyclerView>(R.id.rvLocations)

        // 2. Setup RecyclerView (List)
        // Saat item diklik, teksnya akan dicopy ke EditText atas
        adapter = LocationAdapter(savedLocations) { selectedLocation ->
            etDest.setText(selectedLocation)
            Toast.makeText(this, "Lokasi terpilih: $selectedLocation", Toast.LENGTH_SHORT).show()
        }

        rvLocations.layoutManager = LinearLayoutManager(this)
        rvLocations.adapter = adapter

        // 3. Logika Tombol "Add a destination"
        // Karena di XML tombolnya ada di dalam LinearLayout tanpa ID spesifik,
        // pastikan kamu memberi ID pada LinearLayout pembungkus tombol "Add a destination" di XML.
        // Misal id-nya: @+id/btnAddDestinationContainer

        val btnAdd = findViewById<LinearLayout>(R.id.layoutActions) // Ini contoh ambil parent-nya dulu
        // Tips: Sebaiknya buka XML dan beri ID: android:id="@+id/btnAddAction" pada LinearLayout tombol oranye itu

        // Anggap saja kamu sudah memberi ID @+id/btnAddAction di XML pada tombol oranye
        // val btnAddAction = findViewById<LinearLayout>(R.id.btnAddAction)
        val layoutActions = findViewById<LinearLayout>(R.id.layoutActions)
        val btnAddAction = layoutActions.getChildAt(1) // Tombol "Add a destination"

        btnAddAction.setOnClickListener {
            val textBaru = etDest.text.toString()

            if (textBaru.isNotEmpty()) {
                savedLocations.add(textBaru)

                adapter.notifyItemInserted(savedLocations.size - 1)

                etDest.setText("")
                Toast.makeText(this, "Lokasi disimpan!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Isi lokasi dulu ya", Toast.LENGTH_SHORT).show()
            }
        }
        val btnSelectMap = findViewById<LinearLayout>(R.id.btnSelectMap)

        btnSelectMap.setOnClickListener {
            val intent = Intent(this, LocationPickActivity::class.java)
            startActivity(intent)
        }

        val btnClose = findViewById<ImageView>(R.id.btnClose)
        btnClose.setOnClickListener {
            finish()
        }

        val btnContinue = findViewById<Button>(R.id.btnContinueBooking)

        btnContinue.setOnClickListener {

            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }
    }
}