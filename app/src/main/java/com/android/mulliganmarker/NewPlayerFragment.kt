package com.android.mulliganmarker

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.mulliganmarker.databinding.FragmentNewPlayerBinding
import com.android.mulliganmarker.model.Player
import com.android.mulliganmarker.viewmodel.PlayerViewModel


class NewPlayerFragment : Fragment() {

    interface CallBacks{
        fun onPlayerAdded()
    }

    private var callback: CallBacks? = null

    private val binding: FragmentNewPlayerBinding by viewBinding()

    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_new_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

        binding.saveNewPlayerBTN.setOnClickListener {
            addNewPlayer()
        }
    }

    private fun addNewPlayer() {
        //Create variables to store the data from the text views and convert to appropriate type
        var firstName = binding.newPlayerFirstName.text.toString()
        var lastName = binding.newPlayerLastName.text.toString()
        var email =  binding.newPlayerEmail.text.toString()
        var phoneNum = binding.newPlayerPhoneNum.text.toString()
        var handicap = convertToInt()

        //Check for valid email
        if(!isEmailValid(email)){
            email = ""
        }
        //Check for valid phone number
        if (!isValidPhoneNumber(phoneNum)){
            phoneNum= ""
        }

        //Check if the two important fields are blank
        if(!(firstName).isNullOrBlank() &&  !(lastName).isNullOrBlank()) {
            val newPlayer = Player(0, firstName, lastName, email, phoneNum, handicap)
            playerViewModel.insertPlayer(newPlayer)
            Toast.makeText(activity, "Successfully added new player!", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(
                activity,
                "Failure in adding new player. Player needs both first and last name.",
                Toast.LENGTH_SHORT
            ).show()
        }
        callback?.onPlayerAdded()
    }
    private fun convertToInt():Int{
        var handicapString: String = binding.newPlayerHandicap.text.toString()

        var handicap:Int = 0

        println("This is the handicap $handicapString")
        if (!TextUtils.isEmpty(handicapString)){
            handicap = handicapString.toInt()
        }
        return handicap
    }
    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPhoneNumber(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
    }

    override fun onAttach(context: Context) {

        super.onAttach(context)
        callback = context as CallBacks
    }

    override fun onDetach() {

        super.onDetach()
        callback = null
    }
}