package com.android.mulliganmarker.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.android.mulliganmarker.database.MulliganMarkerDatabase
import com.android.mulliganmarker.database.RoundDAO
import com.android.mulliganmarker.model.Round
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoundRepository(application: Application) {

    private var roundDao: RoundDAO = MulliganMarkerDatabase.getDatabase(application).roundDao()

    var allRounds:LiveData<List<Round>>

    init{
        allRounds = roundDao.getAllRounds()
    }
    fun addRound(round: Round) {
        CoroutineScope(Dispatchers.IO).launch {
            roundDao.addRound(round)
        }
    }
    fun getAllRound():LiveData<List<Round>>{
        return allRounds
    }

}