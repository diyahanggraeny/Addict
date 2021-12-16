package com.diyahanggraeny.addict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RandomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        // tambah back button
        setSupportActionBar(findViewById(R.id.toolbar2))
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.arrow_back)
            setDisplayShowHomeEnabled(true)
        }
    }
}