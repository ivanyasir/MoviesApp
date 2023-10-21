package com.example.newskotlin.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.Models.Articles
import com.example.newskotlin.R

class ListTitleAdapter(private val items: List<Articles>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ListTitleAdapter.Viewholder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Articles)
    }

    inner class Viewholder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvDatePublised: TextView = view.findViewById(R.id.tv_date)
        val tvAuthor: TextView = view.findViewById(R.id.tv_author)

        init {
            // Set an OnClickListener for the item view
            view.setOnClickListener {
                val item = items[adapterPosition]
                listener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_list_news, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.title
        holder.tvDatePublised.text = item.publishedAt
        holder.tvAuthor.text = item.author
    }

    override fun getItemCount(): Int {
        return items.size
    }
}