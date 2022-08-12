package com.android.mulliganmarker.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.android.mulliganmarker.database.MulliganMarkerDatabase
import com.android.mulliganmarker.database.ScorecardDAO
import com.android.mulliganmarker.model.Scorecard
import com.android.mulliganmarker.model.ScorecardWithData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScorecardRepository(application: Application) {

    private var scorecardDAO:ScorecardDAO = MulliganMarkerDatabase.getDatabase(application).scorecardDao()

    fun getRoundScorecards(targetRoundId: Int): LiveData<List<ScorecardWithData>> = scorecardDAO.getRoundScorecards(targetRoundId)

    fun getPlayerScorecards(playerId: Int): LiveData<List<ScorecardWithData>> = scorecardDAO.getPlayerScorecards(playerId)

    fun updateScorecard(scorecard: Scorecard) {
        CoroutineScope(Dispatchers.IO).launch {
            scorecardDAO.updateScorecard(scorecard)
        }
    }

    fun addScorecard(scorecard: Scorecard) {
        CoroutineScope(Dispatchers.IO).launch {
            scorecardDAO.addScorecard(scorecard)
        }
    }
}