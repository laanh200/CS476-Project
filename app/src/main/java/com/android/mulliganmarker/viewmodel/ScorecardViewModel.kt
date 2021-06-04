package com.android.mulliganmarker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.mulliganmarker.model.Scorecard
import com.android.mulliganmarker.repository.ScorecardRepository

class ScorecardViewModel(application: Application): AndroidViewModel(application) {

    private val scorecardRepository = ScorecardRepository(application)

    fun insertScorecard(scorecard: Scorecard) {
        scorecardRepository.addScorecard(scorecard)
    }
}