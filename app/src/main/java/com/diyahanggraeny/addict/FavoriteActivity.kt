package com.diyahanggraeny.addict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.diyahanggraeny.addict.Adapters.FavoriteAdapter
import com.diyahanggraeny.addict.room.Favorite
import com.diyahanggraeny.addict.room.FavoriteDatabase
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteActivity : AppCompatActivity() {

    lateinit var db: FavoriteDatabase
    lateinit var favoriteAdapter : FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        // tambah back button
        setSupportActionBar(findViewById(R.id.toolbar2))
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.arrow_back)
        }

        setupRecyclerView()

        //inisialisasi Database
        db = Room.databaseBuilder(applicationContext, FavoriteDatabase::class.java, "favlist-db").build()

        GlobalScope.launch {
            val favorites: List<Favorite> = db.favoriteDao().getAllFavorites()
            withContext(Dispatchers.Main) {
                favoriteAdapter.setData(favorites)
            }
        }


    }

    // back button to previous page
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupRecyclerView(){
        favoriteAdapter = FavoriteAdapter(arrayListOf())
        favorite_recycler.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = favoriteAdapter
        }

    }
}