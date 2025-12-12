package com.ngojek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// DEMO NOTE: Activity ini adalah "Parent" atau wadah utama
// yang menampung alur otentikasi (Login & Register).
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // LOGIC: Pengecekan agar fragment tidak tumpuk saat layar diputar (re-create).
        // Jika savedInstanceState null, berarti ini pertama kali activity dibuka.
        if (savedInstanceState == null) {

            // DEMO NOTE: "Secara default, saat aplikasi dibuka, kita langsung
            // menempelkan (replace) Fragment SignIn ke dalam container."
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignIn())
                .commit()
        }
    }
}