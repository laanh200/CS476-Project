package com.android.mulliganmarker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "RoundTable",
        foreignKeys = [
                ForeignKey(entity = Scorecard::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("scorecard1_id"),
                        onDelete = ForeignKey.SET_NULL,
                        onUpdate = ForeignKey.CASCADE),
                ForeignKey(entity = Scorecard::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("scorecard2_id"),
                        onDelete = ForeignKey.SET_NULL,
                        onUpdate = ForeignKey.CASCADE),
                ForeignKey(
                        entity = Scorecard::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("scorecard3_id"),
                        onDelete = ForeignKey.SET_NULL,
                        onUpdate = ForeignKey.CASCADE),
                ForeignKey(
                        entity = Scorecard::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("scorecard4_id"),
                        onDelete = ForeignKey.SET_NULL,
                        onUpdate = ForeignKey.CASCADE)]
)
data class Round(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val course_id: Int,
        @ColumnInfo(index = true) val scorecard1_id: Int,
        @ColumnInfo(index = true)val scorecard2_id: Int,
        @ColumnInfo(index = true)val scorecard3_id: Int,
        @ColumnInfo(index = true)val scorecard4_id: Int,
        var date: Date
)
