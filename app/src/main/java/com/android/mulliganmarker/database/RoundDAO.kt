package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mulliganmarker.model.Course
import com.android.mulliganmarker.model.Round

@Dao
interface RoundDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRound(round: Round)

    @Query("SELECT * FROM RoundTable ORDER BY round_id DESC")
    fun getAllRounds():LiveData<List<Round>>
}