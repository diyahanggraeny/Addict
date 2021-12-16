package com.diyahanggraeny.addict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // buka page search
        val search_button = findViewById<Button>(R.id.search_button)
        search_button.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        // buka page random word
        val random_button = findViewById<Button>(R.id.random_button)
        random_button.setOnClickListener{
            val intent = Intent(this, RandomActivity::class.java)
            startActivity(intent)
        }

        // buka page favorites
        val save_button = findViewById<Button>(R.id.save_button)
        save_button.setOnClickListener{
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }

    }
}