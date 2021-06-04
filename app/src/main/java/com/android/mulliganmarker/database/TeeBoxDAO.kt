package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.android.mulliganmarker.model.TeeBox

@Dao
interface TeeBoxDAO {

    @Query("Select * From TeeBoxTable Where course_id=(:course_id)")
    fun getCourseTeeBoxes(course_id: Int): LiveData<List<TeeBox>>
}