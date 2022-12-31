package com.liam.android.moviekbz.room
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.liam.android.moviekbz.model.VideoModel
import com.liam.android.moviekbz.model.entity.MovieModel
import com.liam.android.moviekbz.room.converters.*

@Database(
  entities = [(MovieModel::class), (VideoModel::class)],
  version = 1, exportSchema = false
)
@TypeConverters(
  value = [
    (StringListConverter::class), (IntegerListConverter::class),
    (KeywordListConverter::class), (VideoListConverter::class), (ReviewListConverter::class)
  ]
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun videoDao(): VideoDao
}
