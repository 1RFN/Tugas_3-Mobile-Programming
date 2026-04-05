package com.irfanjayadi.tugas3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etNim = findViewById<EditText>(R.id.etNim)
        val spProdi = findViewById<Spinner>(R.id.spProdi)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)

        val cbCoding = findViewById<CheckBox>(R.id.cbCoding)
        val cbDesign = findViewById<CheckBox>(R.id.cbDesign)
        val cbAudio = findViewById<CheckBox>(R.id.cbAudio) // Tambahan
        val cbLainnya = findViewById<CheckBox>(R.id.cbLainnya)
        val etHobiLainnya = findViewById<EditText>(R.id.etHobiLainnya)

        val etAlasan = findViewById<EditText>(R.id.etAlasan)
        val btnTampilkan = findViewById<Button>(R.id.btnTampilkan)

        val listProdi = arrayOf("Teknik Informatika", "Teknik Elektro", "Teknik Mesin", "Teknik Sipil", "Arsitektur")
        val adapter = ArrayAdapter(this, R.layout.spinner_item, listProdi)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spProdi.adapter = adapter

        cbLainnya.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                etHobiLainnya.visibility = View.VISIBLE
            } else {
                etHobiLainnya.visibility = View.GONE
                etHobiLainnya.text.clear()
            }
        }

        btnTampilkan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val nim = etNim.text.toString().trim()
            val prodi = spProdi.selectedItem.toString()
            val alasan = etAlasan.text.toString().trim()

            if (nama.isEmpty() || nim.isEmpty() || alasan.isEmpty()) {
                Toast.makeText(this, "Nama, NIM, dan Alasan wajib diisi, Player!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedGenderId = rgGender.checkedRadioButtonId
            if (selectedGenderId == -1) {
                Toast.makeText(this, "Pilih Jenis Kelamin terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val gender = findViewById<RadioButton>(selectedGenderId).text.toString()

            val hobbies = mutableListOf<String>()
            if (cbCoding.isChecked) hobbies.add("Game Programming")
            if (cbDesign.isChecked) hobbies.add("3D/2D Art & Design")
            if (cbAudio.isChecked) hobbies.add("Audio & Music Production") // Tambahan
            if (cbLainnya.isChecked && etHobiLainnya.text.isNotEmpty()) {
                hobbies.add(etHobiLainnya.text.toString().trim())
            }

            val hobiString = if (hobbies.isNotEmpty()) hobbies.joinToString(", ") else "Belum menentukan hobi"

            val user = User(nama, nim, prodi, gender, hobiString, alasan)

            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("EXTRA_USER", user)
            startActivity(intent)
        }
    }
}