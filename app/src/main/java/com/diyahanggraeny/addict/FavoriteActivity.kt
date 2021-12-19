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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FavoriteActivity : AppCompatActivity() {

    private  var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<FavoriteAdapter.ViewHolder>? = null
    lateinit var db: FavoriteDatabase
    lateinit var textView: TextView

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


        // recyclerview
        layoutManager = LinearLayoutManager(this)
        favorite_recycler.layoutManager = layoutManager
        adapter = FavoriteAdapter()
        favorite_recycler.adapter = adapter

        // setupRecyclerView()

        //inisialisasi TextView
        textView = findViewById(R.id.textView)

        //inisialisasi Database
        db = Room.databaseBuilder(applicationContext, FavoriteDatabase::class.java, "favlist-db").build()

        //menggunakan coroutine
        GlobalScope.launch {
            val favorites: List<Favorite> = db.favoriteDao().getAllFavorites()
            var displayText = ""
            for (fav in favorites) {
                displayText += "${fav.word} "
            }
            textView.text = displayText
        }

    }

    // back button to previous page
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // get data
    /*
    override fun onStart(){
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch{
            val favorites = db.favoriteDao().getFavorite()
            Log.d("FavoriteActivity", "dbResponse: $favorites")
        }
    } */

    /*

    private fun setupRecyclerView(){
        favoriteAdapter = FavoriteAdapter(arrayListOf())
        favorite_recycler.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = favoriteAdapter
        }

    } */
    /*
    private fun displayData() {
        val favorites: List<Favorite> = db.favoriteDao().getAllFavorites()
        var displayText = ""
        for (fav in favorites) {
            displayText += "${fav.word}"
        }
        textView.text = displayText
    } */
}