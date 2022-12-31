package com.liam.android.moviekbz.ui.listener

import com.liam.android.moviekbz.model.entity.MovieModel

interface MoviesListener {
    fun clickMovie(movie: MovieModel)
}

interface SearchMoviesListener {
    fun searchMovie(movie: MovieModel)
}

interface VideoListener{
    fun clickVideo(key: String)
}