package com.android.mulliganmarker.viewmodel


import android.app.Application


import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.android.mulliganmarker.model.Player
import com.android.mulliganmarker.repository.PlayerRepository

class PlayerViewModel(application: Application): AndroidViewModel(application){


    private val playerRepository=PlayerRepository(application)



    val readAllPlayers: LiveData<List<Player>>? = playerRepository.getAllPlayers()


    fun insertPlayer(player: Player){
        playerRepository.addPlayer(player)

    }
/*
    val playerModel: MutableLiveData<Player> by lazy {
        MutableLiveData<Player>()
    }
*/




}