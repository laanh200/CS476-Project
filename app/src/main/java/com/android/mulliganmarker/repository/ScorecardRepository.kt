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

    fun getTargetScoreCards(targetRoundId: Int): LiveData<List<ScorecardWithData>> = scorecardDAO.getTargetScoreCards(targetRoundId)

     /*fun saveScoreCards(scorecards: List<ScorecardWithData>){
        CoroutineScope(Dispatchers.IO).launch {
            scorecardDAO.saveScoreCards(scorecards)
        }
    }
    fun finishRound(scorecard: ScorecardWithData){
        CoroutineScope(Dispatchers.IO).launch {
            scorecardDAO.finishRound(scorecard)
        }
    }*/

    fun addScorecard(scorecard: Scorecard) {
        CoroutineScope(Dispatchers.IO).launch {
            scorecardDAO.addScorecard(scorecard)
        }
    }
}