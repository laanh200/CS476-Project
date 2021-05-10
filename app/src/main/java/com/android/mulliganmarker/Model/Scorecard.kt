package com.android.mulliganmarker.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ScorecardTable")
data class Scorecard(
        @PrimaryKey(autoGenerate = true) val id:Int,
)
