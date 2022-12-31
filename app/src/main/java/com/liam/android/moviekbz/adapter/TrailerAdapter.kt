package com.liam.android.moviekbz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.liam.android.moviekbz.databinding.ListItemTagBinding
import com.liam.android.moviekbz.model.VideoModel
import com.liam.android.moviekbz.ui.listener.VideoListener

class TrailerAdapter(
    private val videoList: List<VideoModel>,
    private val listener: VideoListener
) :
    RecyclerView.Adapter<TrailerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemTagBinding.inflate(inflater, parent, false)
        return TrailerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val item = videoList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = videoList.size
}

class TrailerViewHolder(private val binding: ListItemTagBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(video: VideoModel, listener: VideoListener) {
        binding.tvVideoTitleItemTag.text = video.name

        Glide.with(binding.root.context)
            .load("https://img.youtube.com/vi/" + video.key + "/default.jpg")
            .centerCrop()
            .placeholder(com.liam.android.moviekbz.R.drawable.ic_launcher_background)
            .into(binding.ivVideoCoverItemTag)

        itemView.setOnClickListener(View.OnClickListener {
            listener.clickVideo(video.key)
        })

    }
}