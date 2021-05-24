package com.android.mulliganmarker.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.android.mulliganmarker.database.MulliganMarkerDatabase
import com.android.mulliganmarker.database.RoundDAO
import com.android.mulliganmarker.model.CourseWithRound
import com.android.mulliganmarker.model.Round
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoundRepository(application: Application) {

    private var roundDao: RoundDAO = MulliganMarkerDatabase.getDatabase(application).roundDao()

    var allRounds:LiveData<List<CourseWithRound>>

    init{
        allRounds = roundDao.getCourseWithRound()
    }
    fun addRound(round: Round) {
        CoroutineScope(Dispatchers.IO).launch {
            roundDao.addRound(round)
        }
    }
    fun getAllRound():LiveData<List<CourseWithRound>>{
        return allRounds
    }
/*
   fun getCourseWithRound():LiveData<List<CourseWithRound>>{
        return roundDao.getCourseWithRound()
    }

 */
}