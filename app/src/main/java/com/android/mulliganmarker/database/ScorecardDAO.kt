package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.mulliganmarker.model.Scorecard
import com.android.mulliganmarker.model.ScorecardWithData

@Dao
interface ScorecardDAO {

    @Transaction
    @Query("SELECT * FROM ScorecardTable WHERE round_id = (:targetRoundID) ORDER BY score_card_id DESC")
    fun getTargetScoreCards(targetRoundID: Int): LiveData<List<ScorecardWithData>>

    /*@Update
    suspend fun saveScoreCards(scorecardsWithData: List<ScorecardWithData>)

    @Update
    suspend fun finishRound(scorecard: ScorecardWithData)*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addScorecard(scorecard: Scorecard)
}