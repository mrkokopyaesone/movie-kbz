package com.liam.android.moviekbz.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.liam.android.moviekbz.R
import com.liam.android.moviekbz.adapter.SearchAdapter
import com.liam.android.moviekbz.databinding.FragmentSearchBinding
import com.liam.android.moviekbz.model.entity.MovieModel
import com.liam.android.moviekbz.ui.listener.SearchMoviesListener
import com.liam.android.moviekbz.ui.movies.MOVIE_DETAIL
import com.liam.android.moviekbz.ui.movies.MoviesViewModel
import com.liam.android.moviekbz.utils.focus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "SearchFragment"

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val vm: MoviesViewModel by viewModels()
    var keyword = "test"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.edtSearchSearchFragment.requestFocus()
        binding.edtSearchSearchFragment.focus(requireContext())

        binding.edtSearchSearchFragment.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                searchMovies(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
        })

    }
    private fun showHideUi(){
        binding.rvSearchMovie.visibility = View.VISIBLE
        binding.relativeLoadingSearch.visibility = View.GONE
    }

    private fun searchMovies(avatar: String) {
        lifecycleScope.launch {
            vm.searchMovies(avatar)
            vm.searchFlow.collect { reviews ->
                showHideUi()
                binding.rvSearchMovie.adapter = reviews.let {
                    SearchAdapter(it, object : SearchMoviesListener{
                        override fun searchMovie(movie: MovieModel) {
                            findNavController().navigate(R.id.movie_detail,
                                bundleOf(MOVIE_DETAIL to Gson().toJson(movie) ))
                        }

                    })
                }
            }

        }


    }
}