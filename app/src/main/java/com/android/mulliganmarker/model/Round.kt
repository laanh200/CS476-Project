package com.android.mulliganmarker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "RoundTable",
        foreignKeys = [ForeignKey(
                entity = Course::class,
                parentColumns = arrayOf("course_id"),
                childColumns = arrayOf("course_id"),
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)]
)
data class Round(
        @PrimaryKey(autoGenerate = true) val round_id: Int,
        @ColumnInfo(index = true) val course_id: Int,
        var date: Date,
        var inProgress: Boolean,
        var hasScorecards: Boolean
)
