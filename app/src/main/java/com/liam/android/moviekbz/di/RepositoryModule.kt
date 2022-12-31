package com.liam.android.moviekbz.di

import com.liam.android.moviekbz.api.TheMovieService
import com.liam.android.moviekbz.repository.TheMovieRepository
import com.liam.android.moviekbz.room.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideTheMovieRepository(
        theMovieService: TheMovieService,
        movieDao: MovieDao
    ): TheMovieRepository {
        return TheMovieRepository(theMovieService,movieDao)
    }
}