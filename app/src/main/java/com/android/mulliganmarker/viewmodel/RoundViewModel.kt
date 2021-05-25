package com.android.mulliganmarker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.android.mulliganmarker.model.RoundWithCourse
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.repository.RoundRepository

class RoundViewModel(application: Application): AndroidViewModel(application) {

    private val roundRepository = RoundRepository(application)

    fun insertRound(round: Round) {
        roundRepository.addRound(round)
    }

    val roundhistoryList:LiveData<List<RoundWithCourse>> = roundRepository.getAllRound()

    fun deleteRound(round: Round){
        roundRepository.deleteRound(round)
    }
}