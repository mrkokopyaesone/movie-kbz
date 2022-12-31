package com.liam.android.moviekbz.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.gson.Gson
import com.liam.android.moviekbz.R
import com.liam.android.moviekbz.adapter.ReviewAdapter
import com.liam.android.moviekbz.adapter.TrailerAdapter
import com.liam.android.moviekbz.databinding.FragmentDetailBinding
import com.liam.android.moviekbz.helper.DateTimeHelper
import com.liam.android.moviekbz.model.entity.MovieModel
import com.liam.android.moviekbz.ui.listener.VideoListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

val MOVIE_DETAIL = "param1"
private const val TAG = "DetailFragment"
@AndroidEntryPoint
class DetailFragment : Fragment(){

    private lateinit var binding: FragmentDetailBinding
    private val vm: MoviesViewModel by viewModels()
    private lateinit var movie: MovieModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            vm.loadReviews(movie.id)
            vm.reviewsFlow.collect{ reviews ->
                binding.rvReviewMovieDetail.adapter = reviews?.let { ReviewAdapter(it) }
            }
        }
        lifecycleScope.launch{
            vm.loadVideos(movie.id)
            vm.videosFlow.collect{ videos ->
                binding.rvTagMovieDetail.adapter = videos?.let {
                    TrailerAdapter(it, object : VideoListener {
                        override fun clickVideo(key: String) {
                            findNavController().navigate(R.id.videoFragment, bundleOf("Video_key" to key ))
                        }
                    })
                }
            }
        }
    }

    private fun setupUI() {
        binding.tvTitleMovieDetail.text = movie.title
        binding.tvOverviewMovieDetail.text = movie.overview
        binding.rbTagMovieDetail.rating = (movie.vote_count/2).toFloat()
        if (movie.release_date != null){
            binding.tvDateMovieDetail.text =  DateTimeHelper.convertDateFormat(movie.release_date!!,
                DateTimeHelper.SERVER_DATE_FORMAT,
                DateTimeHelper.LOCAL_DATE_DISPLAY_FORMAT)
        }

        Glide.with(binding.root.context)
            .load("https://image.tmdb.org/t/p/w780"+ movie.poster_path)
            .transform(CenterCrop(), RoundedCorners(4))
            .placeholder(com.liam.android.moviekbz.R.color.colorPrimary)
            .into(binding.movieDetailPoster)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = Gson().fromJson(it.getString(MOVIE_DETAIL).toString(), MovieModel::class.java)
        }
    }
}