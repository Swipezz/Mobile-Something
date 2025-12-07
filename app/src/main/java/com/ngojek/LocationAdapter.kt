package com.ngojek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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

        holder.tvName.text = locationName
        holder.tvAddress.text = "Riwayat lokasi yang kamu simpan"

        holder.itemView.setOnClickListener {
            onItemClick(locationName)
        }
    }

    override fun getItemCount() = locationList.size
}