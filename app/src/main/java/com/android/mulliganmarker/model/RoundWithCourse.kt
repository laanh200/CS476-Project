package com.android.mulliganmarker.model

import androidx.room.Embedded
import androidx.room.Relation

data class RoundWithCourse
(
        @Embedded  val roundsList: Round,
        @Relation(
                parentColumn = "course_id",
                entityColumn = "course_id"
        ) val course: Course
        )