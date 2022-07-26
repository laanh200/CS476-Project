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
    fun getTargetScoreCards(targetRoundID: Int): LiveData<List<ScorecardWithData>>

    //SELECT * FROM ScorecardTable
// INNER JOIN RoundTable ON RoundTable.round_id = round_id
// WHERE player_id = (:playerId)
// AND RoundTable.course_id = (:courseId)
// AND RoundTable.date BETWEEN (:startDate) AND (:endDate)

    /*
    @Transaction
    @Query("SELECT * FROM ScorecardTable " +
            "INNER JOIN RoundTable ON RoundTable.round_id = round_id " +
            "WHERE player_id = (:playerId) AND RoundTable.course_id = (:courseId) AND RoundTable.date BETWEEN (:startDate) AND (:endDate)")
    fun filterScorecards(playerId: Int, courseId: Int, startDate: Date, endDate: Date): LiveData<List<ScorecardWithData>>*/

    @Update
    fun updateScorecard(scorecard: Scorecard)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addScorecard(scorecard: Scorecard)
}