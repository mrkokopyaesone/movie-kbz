package com.liam.android.moviekbz.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.liam.android.moviekbz.model.entity.MovieModel

@Dao
interface MovieDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovieList(movies: List<MovieModel>)

  @Update
  fun updateMovie(movie: MovieModel)

  @Query("SELECT * FROM movie WHERE id = :id_")
  fun getMovie(id_: Int): MovieModel

  @Query("SELECT * FROM movie WHERE page = :page_")
  fun getMovieList(page_: Int): List<MovieModel>
}
