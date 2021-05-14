package com.android.mulliganmarker.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.android.mulliganmarker.model.Player

@Dao
interface PlayerDAO {

    @Query("Select * from PlayerTable ORDER BY player_id ASC")
    fun getAllPlayers(): LiveData<List<Player>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)
}