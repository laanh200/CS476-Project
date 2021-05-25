package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.mulliganmarker.model.RoundWithCourse
import com.android.mulliganmarker.model.Round

@Dao
interface RoundDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRound(round: Round)


    @Transaction
    @Query("Select * FROM RoundTable ORDER BY round_id DESC")
    fun getCourseWithRound():LiveData<List<RoundWithCourse>>

    @Delete
    suspend fun deleteRound(round: Round)
}