package com.android.mulliganmarker.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.android.mulliganmarker.database.CourseDAO
import com.android.mulliganmarker.database.MulliganMarkerDatabase
import com.android.mulliganmarker.database.PlayerDAO
import com.android.mulliganmarker.model.Course
import com.android.mulliganmarker.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CourseRepository(application: Application) {

    private var courseDao: CourseDAO = MulliganMarkerDatabase.getDatabase(application).courseDao()

    // database functions

    fun getAllCourses(): LiveData<List<Course>> = courseDao.getAllCourses()

}