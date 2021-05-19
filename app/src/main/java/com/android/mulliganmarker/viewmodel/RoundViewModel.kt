package com.android.mulliganmarker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.repository.RoundRepository

class RoundViewModel(application: Application): AndroidViewModel(application) {

    private val roundRepository = RoundRepository(application)

    fun insertRound(round: Round) {
        roundRepository.addRound(round)
    }
}