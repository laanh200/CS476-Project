package com.android.mulliganmarker.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class User(
        @PrimaryKey(autoGenerate = true) val id:Int,
)
