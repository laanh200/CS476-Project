package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.mulliganmarker.model.Course
import com.android.mulliganmarker.model.CourseWithRound
import com.android.mulliganmarker.model.Round

@Dao
interface RoundDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRound(round: Round)

    @Query("SELECT * FROM RoundTable ORDER BY round_id DESC")
    fun getAllRounds():LiveData<List<Round>>

    @Transaction
    @Query("Select * FROM CourseTable")
    fun getCourseWithRound():LiveData<List<CourseWithRound>>
}