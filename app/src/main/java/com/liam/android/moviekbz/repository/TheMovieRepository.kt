package com.liam.android.moviekbz.repository

import androidx.annotation.WorkerThread
import com.liam.android.moviekbz.api.TheMovieService
import com.liam.android.moviekbz.room.MovieDao
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion

class TheMovieRepository constructor(
    private val service: TheMovieService,
    private val movieDao: MovieDao
) : Repository {

    @WorkerThread
    fun loadMovies(page: Int, success: () -> Unit) = flow {
        var movies = movieDao.getMovieList(page)
        if (movies.isEmpty()) {
            val response = service.fetchUpcoming(page)
            response.suspendOnSuccess {
                movies = data.results
                movies.forEach { it.page = page }
                movieDao.insertMovieList(movies)
                emit(movies)
            }
        } else {
            emit(movies)
        }
    }.onCompletion {success()}.flowOn(Dispatchers.IO)

    @WorkerThread
    fun searchMovies(query: String) = flow {
            val response = service.fetchSearchMovies(query)
            response.suspendOnSuccess {
               val searchMovies = data.results
                movieDao.insertMovieList(searchMovies)
                emit(searchMovies)
            }

    }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun loadVideoList(id: Int) = flow {
        val movie = movieDao.getMovie(id)
        var videos = movie.videos
        if (videos.isNullOrEmpty()) {
            service.fetchVideos(id)
                .suspendOnSuccess {
                    videos = data.results
                    movie.videos = videos
                    movieDao.updateMovie(movie)
                    emit(videos)
                }
        } else {
            emit(videos)
        }
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun loadReviewsList(id: Int) = flow {
        val movie = movieDao.getMovie(id)
        var reviews = movie.reviews
        if (reviews.isNullOrEmpty()) {
            service.fetchReviews(id)
                .suspendOnSuccess {
                    reviews = data.results
                    movie.reviews = reviews
                    movieDao.updateMovie(movie)
                    emit(reviews)
                }
        } else {
            emit(reviews)
        }
    }.flowOn(Dispatchers.IO)
}