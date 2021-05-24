package com.android.mulliganmarker.model

import androidx.room.Embedded
import androidx.room.Relation

data class CourseWithRound
(
        @Embedded val course: Course,
        @Relation(
                parentColumn = "course_id",
                entityColumn = "course_id"
        )
        val roundsList: List<Round>
        )