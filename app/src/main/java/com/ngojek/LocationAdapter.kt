package com.ngojek // Sesuaikan package

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter ini menerima list data (String) dan fungsi 'onClick'
class LocationAdapter(
    private val locationList: MutableList<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvLocationName)
        val tvAddress: TextView = view.findViewById(R.id.tvLocationAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_saved_location, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val locationName = locationList[position]

        // Set teks ke layout item
        holder.tvName.text = locationName
        holder.tvAddress.text = "Riwayat lokasi yang kamu simpan"

        // Saat item list diklik -> panggil fungsi onClick (kirim nama lokasi)
        holder.itemView.setOnClickListener {
            onItemClick(locationName)
        }
    }

    override fun getItemCount() = locationList.size
}