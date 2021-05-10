package com.android.mulliganmarker.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PlayerTable")
data class Player(
        @PrimaryKey(autoGenerate = true) val id:Int,
)
