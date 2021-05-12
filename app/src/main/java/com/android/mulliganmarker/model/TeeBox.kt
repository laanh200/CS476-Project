package com.android.mulliganmarker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "TeeBoxTable",
        foreignKeys = [ForeignKey(
                entity = Course::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("course_id"),
                onDelete = ForeignKey.SET_NULL,
                onUpdate = ForeignKey.CASCADE)]
)
data class TeeBox (
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(index = true) val course_id: Int,
        var name: String,
        var color: String,
        var hole1_yardage: Int,
        var hole2_yardage: Int,
        var hole3_yardage: Int,
        var hole4_yardage: Int,
        var hole5_yardage: Int,
        var hole6_yardage: Int,
        var hole7_yardage: Int,
        var hole8_yardage: Int,
        var hole9_yardage: Int,
        var hole10_yardage: Int,
        var hole11_yardage: Int,
        var hole12_yardage: Int,
        var hole13_yardage: Int,
        var hole14_yardage: Int,
        var hole15_yardage: Int,
        var hole16_yardage: Int,
        var hole17_yardage: Int,
        var hole18_yardage: Int
)