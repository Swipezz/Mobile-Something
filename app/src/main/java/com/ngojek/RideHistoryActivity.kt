package com.ngojek

import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RideHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.history_ride)

        val root: View = findViewById(android.R.id.content)

        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnBack = findViewById<ImageView>(R.id.r3njbh0buxkn)

        btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        val close1 = findViewById<ImageView?>(R.id.rnl6xgq6hcg)
        val close2 = findViewById<ImageView?>(R.id.r02felovhv9mx)
        val close3 = findViewById<ImageView?>(R.id.rrt17t0ls7kp)
        val close4 = findViewById<ImageView?>(R.id.rokyqxqlcf5p)
        val close5 = findViewById<ImageView?>(R.id.rxiun7iwscmb)

        setCloseAction(close1, "Item 1")
        setCloseAction(close2, "Item 2")
        setCloseAction(close3, "Item 3")
        setCloseAction(close4, "Item 4")
        setCloseAction(close5, "Item 5")
    }

    private fun setCloseAction(btn: ImageView?, name: String) {
        btn?.setOnClickListener {
            Toast.makeText(this, "$name ditutup", Toast.LENGTH_SHORT).show()
        }
    }
}
