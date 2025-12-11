package com.ngojek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// DEMO CONCEPT: "Adapter Pattern".
// Jelaskan: "Class ini berfungsi sebagai JEMBATAN penghubung.
// Ia mengambil data mentah (List TripHistory) dan mengubahnya menjadi tampilan visual (View) di layar."
class LocationAdapter(
    private val historyList: List<TripHistory>, // Sumber Data
    private val onItemClick: (TripHistory) -> Unit // Callback klik
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    // DEMO CONCEPT: "ViewHolder Pattern".
    // Jelaskan: "Class ini bertugas 'memegang' komponen UI (TextView, dll) supaya
    // aplikasi tidak perlu mencari ID berulang-ulang saat scrolling. Ini membuat aplikasi RINGAN dan CEPAT."
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvLocationName)
        val tvAddress: TextView = view.findViewById(R.id.tvLocationAddress)
        val btnDelete: ImageView = view.findViewById(R.id.btnDelete)
    }

    // Tahap 1: Membuat "Wadah" Tampilan (Inflating)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_saved_location, parent, false)
        return ViewHolder(view)
    }

    // Tahap 2: "Binding Data" (Menempelkan Data ke Wadah)
    // DEMO CONCEPT: "Di sinilah logika utama Adapter bekerja.
    // Kita mengambil data pada posisi tertentu, lalu 'menempelkannya' ke TextView yang sudah disiapkan."
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = historyList[position]

        // Mapping Data: Tujuan -> Judul, Asal -> Sub-judul
        holder.tvName.text = item.destination.ifEmpty { "Tanpa Tujuan" }
        holder.tvAddress.text = "Jemput dari: ${item.origin}"

        // Interaksi: Menghubungkan klik user dengan logika di Activity
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    // Memberitahu RecyclerView ada berapa banyak data yang harus ditampilkan
    override fun getItemCount() = historyList.size
}