package com.android.mulliganmarker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.android.mulliganmarker.model.Scorecard
import com.android.mulliganmarker.model.ScorecardWithData
import com.android.mulliganmarker.repository.ScorecardRepository

class ScorecardViewModel(application: Application): AndroidViewModel(application) {

    private val scorecardRepository= ScorecardRepository(application)

    fun getTargetScoreCards (roundID: Int): LiveData<List<ScorecardWithData>> = scorecardRepository.getTargetScoreCards(roundID)

    fun saveScorecard(scorecard: Scorecard) {
        scorecardRepository.updateScorecard(scorecard)
    }

    fun insertScorecard(scorecard: Scorecard) {
        scorecardRepository.addScorecard(scorecard)
    }
}