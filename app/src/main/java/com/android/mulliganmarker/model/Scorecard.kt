package com.android.mulliganmarker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ScorecardTable",
        foreignKeys = [ForeignKey(
                entity = Player::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("player_id"),
                onDelete = ForeignKey.SET_NULL,
                onUpdate = ForeignKey.CASCADE)]
)
data class Scorecard(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(index = true) val player_id: Int,
        var score_hole1: Int,
        var score_hole2: Int,
        var score_hole3: Int,
        var score_hole4: Int,
        var score_hole5: Int,
        var score_hole6: Int,
        var score_hole7: Int,
        var score_hole8: Int,
        var score_hole9: Int,
        var score_hole10: Int,
        var score_hole11: Int,
        var score_hole12: Int,
        var score_hole13: Int,
        var score_hole14: Int,
        var score_hole15: Int,
        var score_hole16: Int,
        var score_hole17: Int,
        var score_hole18: Int,
)
