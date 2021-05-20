package com.android.mulliganmarker.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mulliganmarker.NewPlayerFragment
import com.android.mulliganmarker.database.MulliganMarkerDatabase
import com.android.mulliganmarker.database.PlayerDAO
import com.android.mulliganmarker.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlayerRepository(application: Application) {

    //Create player DAO variable
    private var playerDao:PlayerDAO = MulliganMarkerDatabase.getDatabase(application).playerDao()

    //Create a live data list of players
    private var allPlayers: LiveData<List<Player>>

    //Assign the result of get all player function of Player Dao to live data list
    init {
        allPlayers = playerDao.getAllPlayers()
    }

    //Function to add a player by calling player dao function add player
    fun addPlayer(player: Player){
        CoroutineScope(Dispatchers.IO).launch {
            playerDao.addPlayer(player)
        }
    }

    //function to return live data list of players
    fun getAllPlayers():LiveData<List<Player>> {
        return allPlayers
    }

    //Function to delete a player by calling player dao function delete player
    fun deletePlayer(player: Player){
        CoroutineScope(Dispatchers.IO).launch {
            playerDao.deletePlayer(player)
        }
    }




}