package com.android.mulliganmarker.viewmodel


import android.app.Application
import androidx.databinding.Bindable

import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.android.mulliganmarker.model.Player
import com.android.mulliganmarker.repository.PlayerRepository

class PlayerViewModel(application: Application) : AndroidViewModel(application){


    private val playerRepository=PlayerRepository(application)
    /*
    private val playerLastName: MutableLiveData<String>?= null
    private val playerEmail: MutableLiveData<String>?= null
    private val playerNumber: MutableLiveData<Int>?= null
    private val playerHandicap: MutableLiveData<Int>?= null
*/

    val readAllPlayers: LiveData<List<Player>>? = playerRepository.getAllPlayers()

    fun insertPlayer(player: Player){
        playerRepository.addPlayer(player)
    }

    val playerModel: MutableLiveData<Player> by lazy {
        MutableLiveData<Player>()
    }

    fun addNewPlayer(){

        var firstNAME= playerModel.value?.first_name.toString()
        var lastNAME = playerModel.value?.last_name.toString()
        var playerEmail = playerModel.value?.email.toString()
        var playerPhoneNum = playerModel.value?.phone_number.toString()
        var playerHandicap = playerModel.value?.handicap!!.toInt()

        val newPlayer = Player(0,firstNAME,lastNAME,playerEmail,playerPhoneNum,playerHandicap)
        insertPlayer(newPlayer)
    }

}