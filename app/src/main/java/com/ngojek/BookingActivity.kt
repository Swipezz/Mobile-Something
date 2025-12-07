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

    private val savedLocations = mutableListOf<String>()
    private lateinit var adapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        supportActionBar?.hide()

        val etDest = findViewById<EditText>(R.id.etDest)
        val btnAddDest = findViewById<LinearLayout>(R.id.layoutActions).getChildAt(1)
        val rvLocations = findViewById<RecyclerView>(R.id.rvLocations)


        adapter = LocationAdapter(savedLocations) { selectedLocation ->
            etDest.setText(selectedLocation)
            Toast.makeText(this, "Lokasi terpilih: $selectedLocation", Toast.LENGTH_SHORT).show()
        }

        rvLocations.layoutManager = LinearLayoutManager(this)
        rvLocations.adapter = adapter


        val btnAdd = findViewById<LinearLayout>(R.id.layoutActions)
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