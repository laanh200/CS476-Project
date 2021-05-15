package com.android.mulliganmarker.model

import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PlayerTable")
data class Player(
    @PrimaryKey(autoGenerate = true) val player_id: Int,
    var first_name: String,
    var last_name: String,
    var email: String,
    var phone_number: String,
    var handicap: Int
)
