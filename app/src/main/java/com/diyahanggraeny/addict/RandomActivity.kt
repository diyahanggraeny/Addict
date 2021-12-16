package com.diyahanggraeny.addict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_definition.*
import kotlinx.android.synthetic.main.activity_random.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        // get random word
        random_button.setOnClickListener{
            RetrofitClient2.instance.getRandom().enqueue(object : Callback<ArrayList<String>> {
                override fun onResponse(
                    call: Call<ArrayList<String>>,
                    response: Response<ArrayList<String>>
                ) {
                    val word = response.body()?.get(0)
                    random_text.text = word
                    find_button.visibility = View.VISIBLE

                    find_button.setOnClickListener{
                        val intent = Intent(this@RandomActivity, DefinitionActivity::class.java)
                        intent.putExtra("Word", word)
                        startActivity(intent)
                    }
                }
                override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                    Toast.makeText(this@RandomActivity, "An Error Occured!", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}