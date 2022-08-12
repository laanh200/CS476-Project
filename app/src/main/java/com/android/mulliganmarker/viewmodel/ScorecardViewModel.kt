package com.android.mulliganmarker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.mulliganmarker.model.Scorecard
import com.android.mulliganmarker.model.ScorecardWithData
import com.android.mulliganmarker.repository.ScorecardRepository

class ScorecardViewModel(application: Application): AndroidViewModel(application) {

    private val scorecardRepository= ScorecardRepository(application)
    private var playerIdLiveData = MutableLiveData<Int>()

    fun getRoundScorecards (roundID: Int): LiveData<List<ScorecardWithData>> = scorecardRepository.getRoundScorecards(roundID)

    var playerScorecardsLiveData: LiveData<List<ScorecardWithData>> =
        Transformations.switchMap(playerIdLiveData) {
            playerId -> scorecardRepository.getPlayerScorecards(playerId)
        }

    fun getPlayerScorecards(playerId: Int) {
        playerIdLiveData.value = playerId
    }

    fun saveScorecard(scorecard: Scorecard) {
        scorecardRepository.updateScorecard(scorecard)
    }

    fun insertScorecard(scorecard: Scorecard) {
        scorecardRepository.addScorecard(scorecard)
    }
}