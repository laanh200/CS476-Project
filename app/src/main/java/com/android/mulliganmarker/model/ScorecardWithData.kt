package com.android.mulliganmarker.model

import androidx.room.Embedded
import androidx.room.Relation

data class ScorecardWithData(
    @Embedded val scoreCard: Scorecard,
    @Relation(
            parentColumn = "round_id",
            entityColumn = "round_id"
    )val round: Round,

    @Relation(
            parentColumn = "player_id",
            entityColumn = "player_id"
    )val player: Player,

    @Relation(
            parentColumn = "tee_box_id",
            entityColumn = "tee_box_id"
    )val teeBox: TeeBox

)
