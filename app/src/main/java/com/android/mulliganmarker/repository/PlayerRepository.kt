package com.android.mulliganmarker.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mulliganmarker.database.MulliganMarkerDatabase
import com.android.mulliganmarker.database.PlayerDAO
import com.android.mulliganmarker.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlayerRepository(application: Application) {

    private var playerDao:PlayerDAO = MulliganMarkerDatabase.getDatabase(application).playerDao()

    private var allPlayers: LiveData<List<Player>>

    init {
        allPlayers = playerDao.getAllPlayers()
    }

    fun addPlayer(player: Player){
        CoroutineScope(Dispatchers.IO).launch {
            playerDao?.addPlayer(player)
        }
    }

    fun getAllPlayers():LiveData<List<Player>> {
        return allPlayers
    }

    fun deletePlayer(player: Player){
        CoroutineScope(Dispatchers.IO).launch {
            playerDao.deletePlayer(player)
        }
    }




}