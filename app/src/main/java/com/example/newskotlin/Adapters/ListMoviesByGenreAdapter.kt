package com.example.newskotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.newskotlin.Models.Genres
import com.example.newskotlin.Models.Results
import com.example.newskotlin.R

class ListMoviesByGenreAdapter(private val context: Context, private val items: List<Results>, private val listener: OnItemClickListener, private val genres: Genres
) : RecyclerView.Adapter<ListMoviesByGenreAdapter.Viewholder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Results)
    }

    inner class Viewholder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvRating: TextView = view.findViewById(R.id.tv_rating)
        val tvDesc: TextView = view.findViewById(R.id.tv_desc)
        val tvDate: TextView = view.findViewById(R.id.tv_date)
        val img: ImageView = view.findViewById(R.id.img)
        val tvGEnres: TextView = view.findViewById(R.id.tv_genres)

        init {
            view.setOnClickListener {
                val item = items[adapterPosition]
                listener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movies, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: ListMoviesByGenreAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.original_title
        holder.tvRating.text = "Rating ${item.vote_average}"
        holder.tvDesc.text = item.overview
        holder.tvDate.text = "Release Date ${item.release_date}"

        val myList = mutableListOf<Int>()

        for (genre in item.genre_ids!!){
            myList.add(genre)
        }
        val myArray = myList.toTypedArray()
        holder.tvGEnres.text = "Genre IDs : ${myArray.joinToString(", ")}"

        // create loading bar inside of image
        val circularProgressDrawable = CircularProgressDrawable(holder.img.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        // placing poster
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/original/${item.poster_path}")
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .into(holder.img)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}