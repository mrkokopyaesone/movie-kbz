package com.liam.android.moviekbz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.liam.android.moviekbz.databinding.ListItemMovieBinding
import com.liam.android.moviekbz.helper.DateTimeHelper
import com.liam.android.moviekbz.model.entity.MovieModel
import com.liam.android.moviekbz.ui.listener.MoviesListener

class MovieListAdapter(private val listener: MoviesListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val data: MutableList<MovieModel> = arrayListOf()

    fun addData(movies: List<MovieModel>) {
        data.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item,listener)
    }

    override fun getItemCount(): Int =data.size

    class MovieViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel, listener: MoviesListener) {
            binding.tvTitleItemMovie.text = movie.title
            binding.tvOverviewItemMovie.text = movie.overview

            if (movie.release_date != null){
                binding.tvDateItemMovie.text =  DateTimeHelper.convertDateFormat(movie.release_date!!,
                    DateTimeHelper.SERVER_DATE_FORMAT,
                    DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT)
            }

            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w780"+movie.backdrop_path)
                .transform(CenterCrop(), RoundedCorners(5))
                .placeholder(com.liam.android.moviekbz.R.drawable.ic_launcher_background)
                .into(binding.ivPosterItemMovie)

            itemView.setOnClickListener(View.OnClickListener {
                listener.clickMovie(movie)
            })
        }
    }
}