package com.android.mulliganmarker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CourseTable")
data class Course (
        @PrimaryKey(autoGenerate = true) val course_id: Int,
        var name: String,
        var location: String,
        var phone_number: String?
)