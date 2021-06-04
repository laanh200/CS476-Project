package com.android.mulliganmarker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ScorecardTable",
        foreignKeys = [ForeignKey(
                entity = Round::class,
                parentColumns = arrayOf("round_id"),
                childColumns = arrayOf("round_id"),
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE),
        ForeignKey(
                entity = Player::class,
                parentColumns = arrayOf("player_id"),
                childColumns = arrayOf("player_id"),
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE),
        ForeignKey(
                entity = TeeBox::class,
                parentColumns = arrayOf("tee_box_id"),
                childColumns = arrayOf("tee_box_id"),
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)]
)
data class Scorecard(
        @PrimaryKey(autoGenerate = true) val score_card_id: Int,
        @ColumnInfo(index = true) val round_id: Int,
        @ColumnInfo(index = true) val player_id: Int,
        @ColumnInfo(index = true) val tee_box_id: Int,
        var hole1_score: Int?,
        var hole2_score: Int?,
        var hole3_score: Int?,
        var hole4_score: Int?,
        var hole5_score: Int?,
        var hole6_score: Int?,
        var hole7_score: Int?,
        var hole8_score: Int?,
        var hole9_score: Int?,
        var hole10_score: Int?,
        var hole11_score: Int?,
        var hole12_score: Int?,
        var hole13_score: Int?,
        var hole14_score: Int?,
        var hole15_score: Int?,
        var hole16_score: Int?,
        var hole17_score: Int?,
        var hole18_score: Int?
)
