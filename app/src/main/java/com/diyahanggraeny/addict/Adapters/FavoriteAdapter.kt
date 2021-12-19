package com.diyahanggraeny.addict.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diyahanggraeny.addict.R
import com.diyahanggraeny.addict.room.Favorite
import kotlinx.android.synthetic.main.favorite_items.view.*

class FavoriteAdapter (private val favorites: ArrayList<Favorite>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    // private var words = arrayOf("addict","love","let")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.favorite_items,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.view.word_text.text = favorite.word
    }

    override fun getItemCount() = favorites.size

   class ViewHolder(val view: View):RecyclerView.ViewHolder(view)

    fun setData(list: List<Favorite>){
        favorites.clear()
        favorites.addAll(list)
        notifyDataSetChanged()
    }
}