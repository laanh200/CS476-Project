package com.android.mulliganmarker.viewmodel


import android.app.Application
import android.provider.ContactsContract
import androidx.databinding.Bindable

import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.android.mulliganmarker.model.Player
import com.android.mulliganmarker.repository.PlayerRepository

class PlayerViewModel(application: Application) : AndroidViewModel(application){


    private val playerRepository=PlayerRepository(application)
    val playerFirstName= MutableLiveData<String>()
    val playerLastName= MutableLiveData<String>()
    val playerEmail= MutableLiveData<String>()
    val playerPhoneNumber= MutableLiveData<String>()
    val playerHandicap= MutableLiveData<Int>()

    val readAllPlayers: LiveData<List<Player>>? = playerRepository.getAllPlayers()


    fun insertPlayer(player: Player){
        playerRepository.addPlayer(player)
    }
/*
    val playerModel: MutableLiveData<Player> by lazy {
        MutableLiveData<Player>()
    }
*/
    fun addNewPlayer(){

        /*
        var firstNAME= playerModel.value?.first_name.toString()
        var lastNAME = playerModel.value?.last_name.toString()
        var email = playerModel.value?.email.toString()
        var phoneNum = playerModel.value?.phone_number.toString()
        var handicap = playerModel.value?.handicap!!.toInt()
*/
        var firstNAME = playerFirstName.value!!
        var lastNAME = playerLastName.value!!
        var email = playerEmail.value!!
        var phoneNum = playerPhoneNumber.value!!
        var handicap = playerHandicap.value!!


        val newPlayer = Player(0,firstNAME,lastNAME, email,phoneNum,handicap)
        insertPlayer(newPlayer)
    }

}