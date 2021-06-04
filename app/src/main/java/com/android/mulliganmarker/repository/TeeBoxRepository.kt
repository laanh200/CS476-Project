package com.android.mulliganmarker.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.android.mulliganmarker.database.MulliganMarkerDatabase
import com.android.mulliganmarker.database.TeeBoxDAO
import com.android.mulliganmarker.model.TeeBox

class TeeBoxRepository(application: Application) {

    private var teeBoxDao: TeeBoxDAO = MulliganMarkerDatabase.getDatabase(application).teeBoxDao()

    // database functions

    fun getCourseTeeBoxes(course_id: Int): LiveData<List<TeeBox>> = teeBoxDao.getCourseTeeBoxes(course_id)
}