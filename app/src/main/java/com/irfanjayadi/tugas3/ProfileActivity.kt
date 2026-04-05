package com.irfanjayadi.tugas3

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvNama = findViewById<TextView>(R.id.tvResultNama)
        val tvNim = findViewById<TextView>(R.id.tvResultNim)
        val tvProdi = findViewById<TextView>(R.id.tvResultProdi)
        val tvGender = findViewById<TextView>(R.id.tvResultGender)
        val tvHobi = findViewById<TextView>(R.id.tvResultHobi)
        val tvAlasan = findViewById<TextView>(R.id.tvResultAlasan)

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_USER", User::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_USER")
        }

        user?.let {
            tvNama.text = it.nama
            tvNim.text = it.nim
            tvProdi.text = it.prodi
            tvGender.text = it.gender
            tvHobi.text = it.hobi
            tvAlasan.text = it.alasanJoining
        }
    }
}