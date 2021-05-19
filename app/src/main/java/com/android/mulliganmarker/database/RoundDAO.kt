package com.android.mulliganmarker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.mulliganmarker.model.Round

@Dao
interface RoundDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRound(round: Round)

}