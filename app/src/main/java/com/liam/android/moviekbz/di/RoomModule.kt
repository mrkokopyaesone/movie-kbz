package com.liam.android.moviekbz.di

import android.content.Context
import androidx.room.Room
import com.liam.android.moviekbz.room.AppDatabase
import com.liam.android.moviekbz.room.MovieDao
import com.liam.android.moviekbz.room.VideoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "KbzMovie.db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideVideoDao(appDatabase: AppDatabase): VideoDao {
        return appDatabase.videoDao()
    }
}