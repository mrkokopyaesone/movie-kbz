package com.liam.android.moviekbz.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.liam.android.moviekbz.databinding.ListItemSearchBinding
import com.liam.android.moviekbz.helper.DateTimeHelper
import com.liam.android.moviekbz.model.entity.MovieModel
import com.liam.android.moviekbz.ui.listener.SearchMoviesListener
import com.liam.android.moviekbz.ui.listener.VideoListener

class SearchAdapter(
    private val searchList: List<MovieModel>,
    private val listener: SearchMoviesListener
) :
    RecyclerView.Adapter<SearchAdapter.SearchMovieViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemSearchBinding.inflate(inflater, parent, false)
        return SearchMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        val item = searchList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = searchList.size

    class SearchMovieViewHolder(private val binding: ListItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel, listener: SearchMoviesListener) {
            binding.tvTitleItemSearchMovie.text = movie.title
            binding.tvOverviewItemSearchMovie.text = movie.overview
            Log.d("SearchAdapter", "bind: ${movie.title}")

            if (movie.release_date != null) {
                binding.tvDateItemSearchMovie.text = DateTimeHelper.convertDateFormat(
                    movie.release_date,
                    DateTimeHelper.SERVER_DATE_FORMAT,
                    DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT
                )
            }

            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w780" + movie.backdrop_path)
                .transform(CenterCrop(), RoundedCorners(5))
                .placeholder(com.liam.android.moviekbz.R.drawable.ic_launcher_background)
                .into(binding.ivPosterItemSearchMovie)

            itemView.setOnClickListener(View.OnClickListener {
                listener.searchMovie(movie)
            })

        }
    }
}

