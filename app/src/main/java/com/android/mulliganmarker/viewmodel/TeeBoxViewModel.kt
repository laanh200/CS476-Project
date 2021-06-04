package com.android.mulliganmarker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.mulliganmarker.model.TeeBox
import com.android.mulliganmarker.repository.TeeBoxRepository
import java.util.*

class TeeBoxViewModel(application: Application): AndroidViewModel(application) {

    private val teeBoxRepository = TeeBoxRepository(application)

    private val courseIdLiveData = MutableLiveData<Int>()

    var teeBoxesLiveData: LiveData<List<TeeBox>> =
        Transformations.switchMap(courseIdLiveData) {
            courseId -> teeBoxRepository.getCourseTeeBoxes(courseId)
        }

    fun loadTeeBoxes(courseId: Int) {
        courseIdLiveData.value = courseId
    }
}