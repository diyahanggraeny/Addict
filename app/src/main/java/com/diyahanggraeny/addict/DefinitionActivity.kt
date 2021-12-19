package com.diyahanggraeny.addict

import android.app.ProgressDialog
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.diyahanggraeny.addict.Adapters.MeaningAdapter
import com.diyahanggraeny.addict.Models.APIResponseItem
import com.diyahanggraeny.addict.Models.Meaning
import com.diyahanggraeny.addict.room.Favorite
import com.diyahanggraeny.addict.room.FavoriteDatabase
import kotlinx.android.synthetic.main.activity_definition.*
import kotlinx.android.synthetic.main.activity_definition.meaning_recycler
import kotlinx.android.synthetic.main.meaning_items.*
import kotlinx.android.synthetic.main.meaning_items.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class DefinitionActivity : AppCompatActivity() {

    private val list = ArrayList<Meaning>()
    lateinit var db: FavoriteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definition)

        // recyclerview
        meaning_recycler.setHasFixedSize(true)
        meaning_recycler.layoutManager = LinearLayoutManager(this)

        // tambah back button
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.arrow_back)
        }

        // get data from intent page
        val intent = intent
        val word = intent.getStringExtra("Word")

        // retrofit
        if (word != null) {
            // progress bar
            val progressDialog = ProgressDialog(this@DefinitionActivity)
            progressDialog.setMessage("Fetching response, please wait")
            progressDialog.show()

            RetrofitClient.instance.getPosts(word).enqueue(object : Callback<List<APIResponseItem>>{
                override fun onResponse(
                    call: Call<List<APIResponseItem>>,
                    response: Response<List<APIResponseItem>>
                ) {
                    val title = response.body()?.get(0)?.word
                    val phonetics_text = response.body()?.get(0)?.phonetics?.get(0)?.text
                    val phonetics_audio = response.body()?.get(0)?.phonetics?.get(0)?.audio

                    title_text.text = title
                    text_1.text = phonetics_text

                    // list meaning
                    response.body()?.get(0)?.meanings?.let{list.addAll(it)}
                    val adapter = MeaningAdapter(list)
                    meaning_recycler.adapter = adapter

                    progressDialog.dismiss()

                    //inisialisasi Database
                    db = Room.databaseBuilder(applicationContext, FavoriteDatabase::class.java, "favlist-db").build()

                    //insert data
                    favorite_button.setOnClickListener{
                        GlobalScope.launch {
                            val fav = Favorite(word)
                            //insert data ke database
                            db.favoriteDao().insert(fav)
                        }
                        Toast.makeText(this@DefinitionActivity, "Added to favorite list", Toast.LENGTH_SHORT).show()
                    }

                    // play sound
                    val sound = "https://" + phonetics_audio
                    sound_button.setOnClickListener{
                        playAudio(sound)
                    }

                }
                override fun onFailure(call: Call<List<APIResponseItem>>, t: Throwable) {
                    Toast.makeText(this@DefinitionActivity, "An Error Occured!", Toast.LENGTH_SHORT).show()
                }

            })
        }else {
            Toast.makeText(this@DefinitionActivity, "You haven't enter a word!", Toast.LENGTH_SHORT).show()
        }


    }

    private fun playAudio(sound:String){
        val mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        if(!mediaPlayer!!.isPlaying){
            try{
                mediaPlayer!!.setDataSource(sound)
                mediaPlayer.prepare()
                mediaPlayer.start()
            }
            catch(e:IOException){
                e.printStackTrace()
            }
        }else{
            try{
                mediaPlayer.pause()
                mediaPlayer.stop()
                mediaPlayer.reset()
            }
            catch (e:IOException){
                e.printStackTrace()
            }
        }
    }

    // back button to previous page
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}