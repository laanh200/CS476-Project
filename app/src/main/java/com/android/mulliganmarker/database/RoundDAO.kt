package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.mulliganmarker.model.RoundWithCourse
import com.android.mulliganmarker.model.Round

@Dao
interface RoundDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRound(round: Round)

    @Query("SELECT * FROM RoundTable ORDER BY round_id DESC LIMIT 1")
    fun getLatestRound(): LiveData<Round?>

    @Transaction
    @Query("SELECT * FROM RoundTable WHERE round_id=(:roundId)")
    fun getRound(roundId: Int): LiveData<RoundWithCourse>

    @Transaction
    @Query("SELECT * FROM RoundTable ORDER BY date DESC")
    fun getRoundsWithCourse(): LiveData<List<RoundWithCourse>>

    @Update
    fun updateRound(round: Round)

    @Delete
    suspend fun deleteRound(round: Round)
}