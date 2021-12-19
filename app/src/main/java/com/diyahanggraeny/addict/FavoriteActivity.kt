package com.diyahanggraeny.addict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
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

        loadData()
    }

    fun loadData(){
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
        favoriteAdapter = FavoriteAdapter(arrayListOf(), object : FavoriteAdapter.OnAdapterListener{
            override fun onDelete(favorite: Favorite) {
                GlobalScope.launch {
                    db.favoriteDao().delete(favorite)
                    loadData()
                }
                Toast.makeText(this@FavoriteActivity, "Removed from favorite list", Toast.LENGTH_SHORT).show()
            }

            override fun onClick(favorite: Favorite) {
                val intent = Intent(this@FavoriteActivity, DefinitionActivity::class.java)
                intent.putExtra("Word", favorite.word)
                startActivity(intent)
            }

        })
        favorite_recycler.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = favoriteAdapter
        }

    }
}