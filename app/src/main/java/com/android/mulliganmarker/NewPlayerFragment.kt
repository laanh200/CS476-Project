package com.android.mulliganmarker

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.mulliganmarker.databinding.FragmentNewPlayerBinding
import com.android.mulliganmarker.model.Player
import com.android.mulliganmarker.viewmodel.PlayerViewModel



class NewPlayerFragment : Fragment() {

    private val binding: FragmentNewPlayerBinding by viewBinding()

    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_new_player,container,false)
/*
      playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

     //   playerViewModel = ViewModelProvider.AndroidViewModelFactory(Application()).create(PlayerViewModel::class.java)

        firstName = view.findViewById(R.id.textInputNewPlayerFirstName)
        lastName = view.findViewById(R.id.textInputNewPlayerLastName)
        email = view.findViewById(R.id.textInputNewPlayerEmail)
        phoneNum = view.findViewById(R.id.textInputNewPlayerPhoneNum)
        handicap = view.findViewById(R.id.textInputNewPlayerHandicap)

        saveBTN = view.findViewById(R.id.save_new_player_BTN)

        saveBTN.setOnClickListener {
            addNewPlayer()
        }
        return view
 */
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

        binding.saveNewPlayerBTN.setOnClickListener {
            addNewPlayer()
        }
    }

    private fun addNewPlayer(){
        //Create variables to store the data from the text views and convert to appropriate type
        var firstName = binding.newPlayerFirstName.text.toString()
        var lastName = binding.newPlayerLastName.text.toString()
        var email =  binding.newPlayerEmail.text.toString()
        var phoneNum = binding.newPlayerPhoneNum.text.toString()
        var handicap = binding.newPlayerHandicap.text.toString().toInt()

        //Check if the two important fields are blank
        if(!(firstName).isNullOrBlank() &&  !(lastName).isNullOrBlank()){
            val newPlayer = Player(0,firstName,lastName, email,phoneNum,handicap)
            playerViewModel.insertPlayer(newPlayer)

            Toast.makeText(activity, "Successfully added new player!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activity,"Failure in adding new player. Player needs both first and last name.", Toast.LENGTH_SHORT).show()
        }
    }
}