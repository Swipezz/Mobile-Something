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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView


data class RecentTrip(
    val name: String,
    val address: String
)

class DestinationAdapter(
    private val allItems: List<RecentTrip>,
    private val onClick: (RecentTrip) -> Unit
) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    private var filteredItems: List<RecentTrip> = allItems

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tvLocationName)

        init {
            view.setOnClickListener {
                onClick(filteredItems[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_saved_location, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = filteredItems[position].name
    }

    override fun getItemCount() = filteredItems.size

    fun filter(query: String) {
        filteredItems = if (query.isBlank()) {
            allItems
        } else {
            allItems.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}


class HomeActivity : AppCompatActivity(), BottomNavCallback {

    private lateinit var adapter: DestinationAdapter
    private lateinit var allDestinations: List<RecentTrip>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.bottom_bar, NavBar())
                .commit()
            loadFragment(HomeFragment())
        }

        val rv = findViewById<RecyclerView>(R.id.recentLocation)
        val etSearch = findViewById<EditText>(R.id.text_search_placeholder)
        val btnMapIcon = findViewById<ImageView>(R.id.img_map_preview)
        val btnHistoryIcon = findViewById<ImageView>(R.id.btn_history_icon)
        val btnSearchLayout = findViewById<LinearLayout>(R.id.btn_search_destination)

        // DATA
        allDestinations = loadRecentTrips()

        adapter = DestinationAdapter(allDestinations) {
            navigateToBooking(it.address)
        }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        // SEARCH
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString().lowercase())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        btnHistoryIcon.setOnClickListener {
            startActivity(Intent(this, RideHistoryActivity::class.java))
        }

        btnMapIcon.setOnClickListener {
            startActivity(Intent(this, LocationPickActivity::class.java))
        }

        btnSearchLayout.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }
    }

    private fun navigateToBooking(destination: String) {
        val intent = Intent(this, BookingActivity::class.java)
        intent.putExtra("DESTINATION_NAME", destination)
        startActivity(intent)
    }

    private fun loadFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val container = findViewById<View>(R.id.fragment_container)
        container.visibility = if (fragment is HomeFragment) View.GONE else View.VISIBLE

        val tx = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)

        if (addToBackStack) tx.addToBackStack(null)
        tx.commit()
    }

    private fun loadRecentTrips(): List<RecentTrip> {
        val prefs = getSharedPreferences("recent_trips", MODE_PRIVATE)
        val set = prefs.getStringSet("trips", emptySet()) ?: emptySet()

        return set.map {
            val split = it.split("|")
            RecentTrip(
                name = split.getOrNull(0) ?: "",
                address = split.getOrNull(1) ?: ""
            )
        }.reversed()
    }

    override fun onResume() {
        super.onResume()
        adapter = DestinationAdapter(loadRecentTrips()) {
            navigateToBooking(it.address)
        }
        findViewById<RecyclerView>(R.id.recentLocation).adapter = adapter
    }


    override fun onHomeSelected() = loadFragment(HomeFragment())
    override fun onProfileSelected() = loadFragment(UserSettingFragment(), true)
    override fun onMotorSelected() {
        startActivity(Intent(this, BookingActivity::class.java))
    }
}

