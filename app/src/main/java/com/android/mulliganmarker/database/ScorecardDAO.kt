package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.model.Scorecard
import com.android.mulliganmarker.model.ScorecardWithData
import java.util.*

@Dao
interface ScorecardDAO {

    @Transaction
    @Query("SELECT * FROM ScorecardTable WHERE round_id = (:targetRoundID) ORDER BY score_card_id DESC")
    fun getRoundScorecards(targetRoundID: Int): LiveData<List<ScorecardWithData>>

    @Transaction
    @Query("SELECT * FROM ScorecardTable WHERE player_id = (:playerId)")
    fun getPlayerScorecards(playerId: Int): LiveData<List<ScorecardWithData>>

    @Update
    fun updateScorecard(scorecard: Scorecard)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addScorecard(scorecard: Scorecard)
}