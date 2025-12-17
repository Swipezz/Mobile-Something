package com.ngojek

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import com.google.android.material.card.MaterialCardView

class NavBar : Fragment(R.layout.fragment_nav_bar) {

    private lateinit var callback: BottomNavCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as BottomNavCallback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnHome = view.findViewById<ImageView>(R.id.btn_home)
        val btnProfile = view.findViewById<ImageView>(R.id.btn_profile)
        val btnMotor = view.findViewById<MaterialCardView>(R.id.btn_motor)

        btnHome.setOnClickListener {
            callback.onHomeSelected()
        }

        btnProfile.setOnClickListener {
            callback.onProfileSelected()
        }

        btnMotor.setOnClickListener {
            callback.onMotorSelected()
        }
    }
}
