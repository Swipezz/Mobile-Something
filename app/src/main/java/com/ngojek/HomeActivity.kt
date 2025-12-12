package com.ngojek

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

class HomeActivity : AppCompatActivity(), BottomNavCallback {

    private val CONTAINER_ID = R.id.fragment_container

    data class DestinationItem(val name: String, val layout: View)

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // ---------- INIT UI ----------
        val btnMapIcon = findViewById<ImageView>(R.id.img_map_preview)
        val btnHistoryIcon = findViewById<ImageView>(R.id.btn_history_icon)
        val btnSearchLayout = findViewById<LinearLayout>(R.id.btn_search_destination)
        val etSearch = findViewById<EditText>(R.id.text_search_placeholder)

        val btnHome = findViewById<ImageView>(R.id.btn_home)
        val btnProfile = findViewById<ImageView>(R.id.btn_profile)
        val btnMotor = findViewById<MaterialCardView>(R.id.btn_motor)

        val layoutMieAyam = findViewById<LinearLayout>(R.id.item_mie_ayam)
        val layoutMasJo = findViewById<LinearLayout>(R.id.item_mas_jo)
        val layoutKwarcap = findViewById<LinearLayout>(R.id.item_kwarcap)
        val layoutPnm = findViewById<LinearLayout>(R.id.item_pnm)
        val layoutAston = findViewById<LinearLayout>(R.id.item_aston)
        val layoutStasiun = findViewById<LinearLayout>(R.id.item_stasiun)



        val destinationList = listOf(
            DestinationItem("Mie ayam", layoutMieAyam),
            DestinationItem("Mas Jo", layoutMasJo),
            DestinationItem("Kwarcap", layoutKwarcap),
            DestinationItem("Kampus 1 Poltek Madiun", layoutPnm),
            DestinationItem("Mall Aston", layoutAston),
            DestinationItem("Stasiun Kota Baru", layoutStasiun)

        )

        // Default load HomeFragment
        loadFragment(HomeFragment())
        btnHome.setImageResource(R.drawable.alfian_rumah_main_biru)

        // ---------- BUTTON NAVIGATION ----------
        btnHistoryIcon.setOnClickListener {
            startActivity(Intent(this, RideHistoryActivity::class.java))
        }

        btnMapIcon.setOnClickListener {
            startActivity(Intent(this, LocationPickActivity::class.java))
        }

        btnMotor.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        btnSearchLayout.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        // HOME
        btnHome.setOnClickListener {
            loadFragment(HomeFragment())
            btnHome.setImageResource(R.drawable.alfian_rumah_main_biru)
            btnProfile.setImageResource(R.drawable.alfian_wong_main)
        }

        // PROFILE → menggunakan Fragment
        btnProfile.setOnClickListener {
            loadFragment(UserSettingFragment())
            btnHome.setImageResource(R.drawable.alfian_rumah_main)
            btnProfile.setImageResource(R.drawable.alfian_wong_main_biru)
        }

        // ---------- REALTIME SEARCH ----------
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase().trim()
                filterDestinations(query, destinationList)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // ---------- DESTINATION CLICK ----------
        layoutMieAyam.setOnClickListener { navigateToBooking("Belakang sleko") }
        layoutMasJo.setOnClickListener { navigateToBooking("Jl manyar no 5") }
        layoutKwarcap.setOnClickListener { navigateToBooking("Depan poltek") }
    }

    // FILTER ITEM
    private fun filterDestinations(query: String, list: List<DestinationItem>) {
        for (item in list) {
            item.layout.visibility =
                if (item.name.lowercase().contains(query)) View.VISIBLE else View.GONE
        }
    }

    private fun navigateToBooking(destinationName: String) {
        val intent = Intent(this, BookingActivity::class.java)
        intent.putExtra("DESTINATION_NAME", destinationName)
        startActivity(intent)
    }

    // ---------- LOAD FRAGMENT ----------
    private fun loadFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragmentContainer = findViewById<View>(R.id.fragment_container)
        
        // Show container untuk fragment selain HomeFragment
        // Hide container untuk HomeFragment (supaya konten home terlihat)
        if (fragment is HomeFragment) {
            fragmentContainer.visibility = View.GONE
        } else {
            fragmentContainer.visibility = View.VISIBLE
        }
        
        val transaction = supportFragmentManager.beginTransaction()
            .replace(CONTAINER_ID, fragment)

        if (addToBackStack) transaction.addToBackStack("user_settings")

        transaction.commit()
    }

    // Dipanggil dari UserSettingFragment ketika tombol Home ditekan
    override fun onHomeSelectedFromFragment() {
        val btnHome = findViewById<ImageView>(R.id.btn_home)
        val btnProfile = findViewById<ImageView>(R.id.btn_profile)

        btnHome.setImageResource(R.drawable.alfian_rumah_main_biru)
        btnProfile.setImageResource(R.drawable.alfian_wong_main)
    }
}
