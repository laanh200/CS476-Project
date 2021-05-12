package com.android.mulliganmarker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "RoundTable",
        foreignKeys = [ForeignKey(
                entity = Course::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("course_id"),
                onDelete = ForeignKey.SET_NULL,
                onUpdate = ForeignKey.CASCADE)]
)
data class Round(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(index = true) val course_id: Int,
        var date: Date
)
