package com.liam.android.moviekbz.api

import com.liam.android.moviekbz.model.PopularResponse
import com.liam.android.moviekbz.model.ReviewResponse
import com.liam.android.moviekbz.model.UpComingResponse
import com.liam.android.moviekbz.model.VideoResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieService {

    @GET(TheMovieApi.UPCOMING_MOVIE)
    suspend fun fetchUpcoming(@Query("page") page: Int): ApiResponse<UpComingResponse>

    @GET(TheMovieApi.SEARCH_MOVIE)
    suspend fun fetchSearchMovies(@Query("query") query: String): ApiResponse<PopularResponse>

    @GET(TheMovieApi.POPULAR_MOVIE)
    suspend fun fetchPopular(): ApiResponse<PopularResponse>

    @GET(TheMovieApi.MOVIE_DETAIL)
    suspend fun fetchMovieDetail(@Path("movie_id") id: Int): ApiResponse<PopularResponse>

    @GET(TheMovieApi.VIDEOS)
    suspend fun fetchVideos(@Path("movie_id") id: Int): ApiResponse<VideoResponse>

    @GET(TheMovieApi.REVIEWS)
    suspend fun fetchReviews(@Path("movie_id") id: Int): ApiResponse<ReviewResponse>
}