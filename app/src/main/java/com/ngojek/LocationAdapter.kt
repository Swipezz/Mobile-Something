package com.ngojek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationAdapter(
    private val historyList: List<TripHistory>, // Ubah tipe data List
    private val onItemClick: (TripHistory) -> Unit // Listener mengirim object TripHistory
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvLocationName)
        val tvAddress: TextView = view.findViewById(R.id.tvLocationAddress)
        val btnDelete: ImageView = view.findViewById(R.id.btnDelete) // Asumsi ada tombol hapus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_saved_location, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = historyList[position]

        // Tampilkan Tujuan sebagai Judul Utama
        holder.tvName.text = item.destination.ifEmpty { "Tanpa Tujuan" }

        // Tampilkan Jemput sebagai Alamat/Sub-judul
        holder.tvAddress.text = "Jemput dari: ${item.origin}"

        // Saat item diklik, kirim seluruh paket (origin & dest) ke Activity
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = historyList.size
}