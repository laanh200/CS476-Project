package com.android.mulliganmarker.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.android.mulliganmarker.database.MulliganMarkerDatabase
import com.android.mulliganmarker.database.RoundDAO
import com.android.mulliganmarker.model.RoundWithCourse
import com.android.mulliganmarker.model.Round
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoundRepository(application: Application) {

    private var roundDao: RoundDAO = MulliganMarkerDatabase.getDatabase(application).roundDao()

    var allRounds: LiveData<List<RoundWithCourse>>

    init {
        allRounds = roundDao.getRoundsWithCourse()
    }

    fun addRound(round: Round) {
        CoroutineScope(Dispatchers.IO).launch {
            roundDao.addRound(round)
        }
    }

    fun getRound(roundId: Int): LiveData<RoundWithCourse> = roundDao.getRound(roundId)

    fun getLatestRound(): LiveData<Round?> = roundDao.getLatestRound()

    fun getAllRound(): LiveData<List<RoundWithCourse>> {
        return allRounds
    }

    fun updateRound(round: Round) {
        CoroutineScope(Dispatchers.IO).launch {
            roundDao.updateRound(round)
        }
    }

    fun deleteRound(round: Round) {
        CoroutineScope(Dispatchers.IO).launch {
            roundDao.deleteRound(round)
        }
    }
}