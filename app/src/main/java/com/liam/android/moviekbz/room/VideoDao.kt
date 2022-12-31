package com.liam.android.moviekbz.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.liam.android.moviekbz.model.VideoModel

@Dao
interface VideoDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertVideo(tvs: List<VideoModel>)

  @Update
  fun updateVideo(tv: VideoModel)

  @Query("SELECT * FROM video WHERE id = :id_")
  fun getVideo(id_: Int): VideoModel?

  @Query("SELECT * FROM video WHERE page = :page_")
  fun getVideoList(page_: Int): List<VideoModel>
}
