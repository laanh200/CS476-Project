package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.android.mulliganmarker.model.Course
import java.util.*

@Dao
interface CourseDAO {

    @Query("SELECT * FROM CourseTable ORDER BY name ASC")
    fun getAllCourses(): LiveData<List<Course>>

    @Query("Select name From CourseTable where course_id = (:id)")
   fun getName(id:Int): String
}