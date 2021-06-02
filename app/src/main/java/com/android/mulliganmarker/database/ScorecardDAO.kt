package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.model.Scorecard
import com.android.mulliganmarker.model.ScorecardWithData

@Dao
interface ScorecardDAO {

    @Query("SELECT * FROM ScorecardTable WHERE round_id = :targetRoundID ORDER BY score_card_id DESC")
    fun getTargetScoreCards(targetRoundID: Int): LiveData<List<ScorecardWithData>>

    @Update
    suspend fun saveScoreCards(scorecardsWithData: List<ScorecardWithData>)

    @Update
    suspend fun finishRound(scorecard: ScorecardWithData)
}