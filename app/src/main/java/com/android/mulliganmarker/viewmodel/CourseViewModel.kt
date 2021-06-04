package com.android.mulliganmarker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.mulliganmarker.model.Course
import com.android.mulliganmarker.repository.CourseRepository
import java.util.*

class CourseViewModel(application: Application): AndroidViewModel(application) {

    private val courseRepository = CourseRepository(application)

    val coursesListLiveData = courseRepository.getAllCourses()
}