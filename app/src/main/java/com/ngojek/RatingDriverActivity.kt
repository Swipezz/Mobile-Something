package com.ngojek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat // Diperlukan untuk warna, jika ColorFilter diaktifkan

/**
 * Fragment untuk menampilkan dan mengelola rating driver.
 * Menggunakan layout activity_rating_driver.xml yang telah diubah namanya menjadi fragment_rating_driver.xml
 */
class RatingDriverFragment : Fragment() {

    // Daftar ImageView untuk bintang rating
    private lateinit var starViews: List<ImageView>

    // Warna Tint untuk bintang terisi (emas)
    private val COLOR_FILLED_STAR = 0xFFFFD700.toInt() // Emas
    // Warna Tint untuk bintang kosong (abu-abu/default)
    private val COLOR_EMPTY_STAR = 0xFFCCCCCC.toInt() // Abu-abu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Asumsi: layout activity_rating_driver.xml kini diakses sebagai fragment_rating_driver.xml
        return inflater.inflate(R.layout.activity_rating_driver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi daftar ImageView bintang berdasarkan ID unik
        starViews = listOf(
            view.findViewById(R.id.star1),
            view.findViewById(R.id.star2),
            view.findViewById(R.id.star3),
            view.findViewById(R.id.star4),
            view.findViewById(R.id.star5)
        )

        // Atur rating awal (misalnya 5 bintang terisi penuh saat layar dimuat)
        updateStarRating(5)

        // 2. Tetapkan OnClickListener untuk setiap bintang
        starViews.forEachIndexed { index, starView ->
            // Rating 1, 2, 3, 4, 5 (index + 1)
            val ratingValue = index + 1

            starView.setOnClickListener {
                // Perbarui tampilan bintang
                updateStarRating(ratingValue)

                // Tampilkan notifikasi Toast
                Toast.makeText(
                    requireContext(),
                    "Rating Driver $ratingValue",
                    Toast.LENGTH_SHORT
                ).show()

                // LOGIKA BARU: Tunda sebentar lalu kembali ke SignInFragment
                // Ini mensimulasikan proses submit rating ke server yang membutuhkan waktu
                view.postDelayed({
                    // Menghapus semua fragment dari back stack (jika ada), lalu mengganti
                    // dengan SignInFragment. Asumsi: R.id.fragment_container adalah ID container
                    parentFragmentManager.popBackStack(null, 0)
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SignIn())
                        .commit()
                }, 1500) // Tunda 1.5 detik agar Toast sempat terlihat
            }
        }
    }

    /**
     * Memperbarui tampilan bintang berdasarkan rating yang dipilih.
     */
    private fun updateStarRating(rating: Int) {
        // Konteks harus tersedia dalam Fragment
        val context = context ?: return

        starViews.forEachIndexed { index, starView ->
            // Jika index (0-4) kurang dari rating (1-5), maka bintang terisi.
            val color = if (index < rating) {
                COLOR_FILLED_STAR
            } else {
                COLOR_EMPTY_STAR
            }

            // Mengatur tint warna secara programatik
            starView.setColorFilter(color)
        }
    }
}