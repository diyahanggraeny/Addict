package com.diyahanggraeny.addict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // tambah back button
        setSupportActionBar(findViewById(R.id.toolbar2))
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.arrow_back)
            setDisplayShowHomeEnabled(true)
        }

        // pass data
        val word = findViewById<EditText>(R.id.search_bar)
        search_button.setOnClickListener{
            if (word.text.isNotEmpty()) {
                val word = word.text.toString()
                val intent = Intent(this, DefinitionActivity::class.java)
                intent.putExtra("Word", word)
                startActivity(intent)
            } else {
                Toast.makeText(this, "You haven't enter a word!", Toast.LENGTH_SHORT).show()
            }
        }

    }

}