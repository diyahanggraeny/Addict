package com.diyahanggraeny.addict.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diyahanggraeny.addict.Models.Definition
import com.diyahanggraeny.addict.Models.Meaning
import com.diyahanggraeny.addict.R
import kotlinx.android.synthetic.main.definitions_item.view.*
import kotlinx.android.synthetic.main.meaning_items.view.*

class DefinitionAdapter (private val list2:List<Definition>): RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder>() {

    inner class DefinitionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(DefinitionItem : Definition){
            with(itemView){
                val definition = "${DefinitionItem.definition}"
                val example = "${DefinitionItem.example}"
                val synonym = "${DefinitionItem.synonyms}"
                val antonym = "${DefinitionItem.antonyms}"
                meaning_definition.text = definition
                example_text.text = example
                synonym_example.text = synonym
                antonym_example.text = antonym
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.definitions_item, parent, false)
        return DefinitionViewHolder(view)
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        holder.bind(list2[position])
    }

    override fun getItemCount(): Int = list2.size


}