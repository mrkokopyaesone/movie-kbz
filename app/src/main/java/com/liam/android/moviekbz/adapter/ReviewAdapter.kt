package com.liam.android.moviekbz.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.liam.android.moviekbz.R
import com.liam.android.moviekbz.databinding.ListItemReviewBinding
import com.liam.android.moviekbz.model.ReviewModel
import com.ms.square.android.expandabletextview.ExpandableTextView

class ReviewAdapter(private val reviewList: List<ReviewModel>) :
    RecyclerView.Adapter<ReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemReviewBinding.inflate(inflater, parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = reviewList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = reviewList.size
}

class ReviewViewHolder(private val binding: ListItemReviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: ReviewModel) {
        binding.tvAuthorItemReview.text = movie.author
        binding.tvOverviewItemMovie.text = movie.content

        binding.tvOverviewItemMovie.setOnClickListener(View.OnClickListener {
            binding.tvOverviewItemMovie.maxLines = Int.MAX_VALUE
        })

        Log.d("ReviewAdapter", "bind: ${movie.author}")

    }
}