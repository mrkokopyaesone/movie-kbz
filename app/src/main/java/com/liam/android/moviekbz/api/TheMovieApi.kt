package com.liam.android.moviekbz.api

import com.liam.android.moviekbz.helper.Constant

object TheMovieApi{
    const val BASE_URL = Constant.BASE_URL

    const val POPULAR_MOVIE = "${Constant.BASE_URL}/movie/popular"
    const val UPCOMING_MOVIE = "${Constant.BASE_URL}/movie/upcoming"
    const val SEARCH_MOVIE = "${Constant.BASE_URL}/search/movie"
    const val MOVIE_DETAIL = "${Constant.BASE_URL}/movie/{movie_id}"
    const val VIDEOS = "${Constant.BASE_URL}/movie/{movie_id}/videos"
    const val REVIEWS = "${Constant.BASE_URL}/movie/{movie_id}/reviews"
}
