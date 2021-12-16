package com.diyahanggraeny.addict.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diyahanggraeny.addict.R

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private var words = arrayOf("addict","let","love")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.favorite_items,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        holder.itemWord.text = words[position]
    }

    override fun getItemCount(): Int {
        return words.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemWord: TextView

        init{
            itemWord = itemView.findViewById(R.id.word_text)
        }
    }


}