package com.android.mulliganmarker.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RoundTable")
data class Round(
        @PrimaryKey(autoGenerate = true) val id:Int,

        )
