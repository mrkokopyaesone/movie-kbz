package com.liam.android.moviekbz.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.liam.android.moviekbz.R
import com.liam.android.moviekbz.adapter.MovieListAdapter
import com.liam.android.moviekbz.databinding.FragmentMovieListBinding
import com.liam.android.moviekbz.model.entity.MovieModel
import com.liam.android.moviekbz.ui.listener.MoviesListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment(), MoviesListener {
    private lateinit var binding: FragmentMovieListBinding

    private val vm: MoviesViewModel by viewModels()
    private lateinit var adapter: MovieListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
    }

    private fun showHideUi() {
        binding.rvMovieList.visibility = View.VISIBLE
        binding.relativeLoadingMovieListFragment.visibility = View.GONE
    }

    private fun setupUI() {
        adapter = MovieListAdapter(this)
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
        binding.rvMovieList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        vm.page.value = 1

        binding.rvMovieList.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager =
                        binding.rvMovieList.layoutManager as LinearLayoutManager?
                    if (dy > 0) if (vm.isLoading.value == true) {
                        if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                            vm.page.value++
                        }
                    }
                }
            }
        )
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            vm.movieListFlow.collect {
                adapter.addData(it)
                showHideUi()
            }
        }
    }

    override fun clickMovie(movie: MovieModel) {
        findNavController().navigate(
            R.id.movie_detail,
            bundleOf(MOVIE_DETAIL to Gson().toJson(movie))
        )
    }
}