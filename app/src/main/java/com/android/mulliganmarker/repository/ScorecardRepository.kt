package com.android.mulliganmarker.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.android.mulliganmarker.database.MulliganMarkerDatabase
import com.android.mulliganmarker.database.ScorecardDAO
import com.android.mulliganmarker.model.Scorecard

class ScorecardRepository(application: Application) {

    private var scorecardDAO:ScorecardDAO = MulliganMarkerDatabase.getDatabase(application).scorecardDao()

    fun getAllScoreCards(): LiveData<List<Scorecard>> = scorecardDAO.getAllScoreCards()
}