package com.liam.android.moviekbz.ui.movies

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liam.android.moviekbz.repository.TheMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel   @Inject constructor(
    private val repository: TheMovieRepository
): ViewModel() {

    var isLoading = MutableLiveData(false)
    var page = MutableStateFlow(1)
    var query = MutableStateFlow(1)

    var movieListFlow = page.flatMapLatest {
        isLoading.value = true
        repository.loadMovies(it){}
    }

    @MainThread
    fun searchMovies(text: String) = searchSharedFlow.tryEmit(text)
    private val searchSharedFlow: MutableSharedFlow<String> = MutableSharedFlow(replay = 1)
    var searchFlow = searchSharedFlow.flatMapLatest {
        repository.searchMovies(it)
    }


    @MainThread
    fun loadReviews(id: Int) = reviewsSharedFlow.tryEmit(id)
    private val reviewsSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)
    var reviewsFlow = reviewsSharedFlow.flatMapLatest {
        repository.loadReviewsList(it)
    }

    @MainThread
    fun loadVideos(id: Int) = videosSharedFlow.tryEmit(id)
    private val videosSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)
    var videosFlow = videosSharedFlow.flatMapLatest {
        repository.loadVideoList(it)
    }
}
