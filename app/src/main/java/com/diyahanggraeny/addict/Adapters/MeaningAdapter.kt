package com.diyahanggraeny.addict.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diyahanggraeny.addict.Models.Definition
import com.diyahanggraeny.addict.Models.Meaning
import com.diyahanggraeny.addict.R
import kotlinx.android.synthetic.main.activity_definition.*
import kotlinx.android.synthetic.main.definitions_item.view.*
import kotlinx.android.synthetic.main.meaning_items.*
import kotlinx.android.synthetic.main.meaning_items.view.*

class MeaningAdapter(private val list:List<Meaning>): RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {

    private val list2 = ArrayList<Definition>()

    inner class MeaningViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(ResponseItem : Meaning){
            with(itemView){
                val partofspeech = "${ResponseItem.partOfSpeech}"
                val definitions = ResponseItem.definitions
                partofspeech_label.text = partofspeech
                definitions.let{list2.addAll(it)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meaning_items, parent, false)
        return MeaningViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(list[position])

        val childLayoutManager = LinearLayoutManager(holder.itemView.definition_recycler.context)

        holder.itemView.definition_recycler.apply {
            layoutManager = childLayoutManager
            val adapter2 = DefinitionAdapter(list2)
            definition_recycler.adapter = adapter2
        }

    }

    override fun getItemCount(): Int = list.size


}