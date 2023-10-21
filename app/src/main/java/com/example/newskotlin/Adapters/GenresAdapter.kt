package com.example.newskotlin.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.Models.Articles
import com.example.newskotlin.Models.Genres
import com.example.newskotlin.R

class GenresAdapter(private val items: List<Genres>, private val listener: OnItemClickListener) : RecyclerView.Adapter<GenresAdapter.Viewholder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Genres)
    }

    inner class Viewholder(view: View) : RecyclerView.ViewHolder(view) {
        val tvGenre: TextView = view.findViewById(R.id.tv_genre)

        init {
            view.setOnClickListener {
                val item = items[adapterPosition]
                listener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_genres, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: GenresAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.tvGenre.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }


}