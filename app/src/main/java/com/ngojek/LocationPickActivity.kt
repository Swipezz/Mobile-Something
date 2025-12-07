package com.ngojek

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class LocationPickActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.pick_location)

        val root: View = findViewById(android.R.id.content)

        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSearchContainer = findViewById<LinearLayout>(R.id.btn_search_destination)
        val etSearch = findViewById<EditText>(R.id.et_search)
        val btnSearch = findViewById<ImageView>(R.id.btn_search)
        val btnConfirm = findViewById<MaterialButton>(R.id.btn_confirm)

        btnSearchContainer.setOnClickListener {
            Toast.makeText(this, "Klik search bar", Toast.LENGTH_SHORT).show()
        }

        btnSearch.setOnClickListener {
            val text = etSearch.text.toString()

            if (text.isEmpty()) {
                Toast.makeText(this, "Masukkan lokasi dulu", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Mencari: $text", Toast.LENGTH_SHORT).show()
            }
        }

        btnConfirm.setOnClickListener {
            Toast.makeText(this, "Lokasi dikonfirmasi", Toast.LENGTH_SHORT).show()

 startActivity(Intent(this, OrderActivity::class.java))
        }
    }
}
