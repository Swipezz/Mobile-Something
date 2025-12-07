package com.ngojek

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RatingActivity : AppCompatActivity() {

    private var currentRating = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_driver)
        supportActionBar?.hide()


        val star1 = findViewById<ImageView>(R.id.star1)
        val star2 = findViewById<ImageView>(R.id.star2)
        val star3 = findViewById<ImageView>(R.id.star3)
        val star4 = findViewById<ImageView>(R.id.star4)
        val star5 = findViewById<ImageView>(R.id.star5)

        val stars = listOf(star1, star2, star3, star4, star5)

        fun updateStarColors(rating: Int) {
            currentRating = rating

            for (i in stars.indices) {
                if (i < rating) {
                    stars[i].setColorFilter(Color.parseColor("#FFD700"))
                } else {

                    stars[i].setColorFilter(Color.parseColor("#E0E0E0"))
                }
            }
        }

        star1.setOnClickListener {
            updateStarColors(1)
            Toast.makeText(this, "Rating: 1 Bintang", Toast.LENGTH_SHORT).show()
            finishRating()
        }

        star2.setOnClickListener {
            updateStarColors(2)
            Toast.makeText(this, "Rating: 2 Bintang", Toast.LENGTH_SHORT).show()
            finishRating()
        }

        star3.setOnClickListener {
            updateStarColors(3)
            Toast.makeText(this, "Rating: 3 Bintang", Toast.LENGTH_SHORT).show()
            finishRating()
        }

        star4.setOnClickListener {
            updateStarColors(4)
            Toast.makeText(this, "Rating: 4 Bintang", Toast.LENGTH_SHORT).show()
            finishRating()
        }

        star5.setOnClickListener {
            updateStarColors(5)
            Toast.makeText(this, "Rating: 5 Bintang! Mantap!", Toast.LENGTH_SHORT).show()

            finishRating()
        }
    }

    private fun finishRating() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
         startActivity(intent)
         finish()
    }
}