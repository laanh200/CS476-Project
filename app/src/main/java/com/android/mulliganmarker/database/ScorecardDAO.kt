package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.android.mulliganmarker.model.Scorecard

@Dao
interface ScorecardDAO {

    @Query("SELECT * FROM ScorecardTable ORDER BY score_card_id DESC")
    fun getAllScoreCards(): LiveData<List<Scorecard>>

}