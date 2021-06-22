package com.android.mulliganmarker.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.R

import com.android.mulliganmarker.databinding.ScorecardItemRowBinding
import com.android.mulliganmarker.model.ScorecardWithData


class ScorecardListAdapter:RecyclerView.Adapter<ScorecardListAdapter.MyViewHolder>() {


    private var scoreCardsWithData = emptyList<ScorecardWithData>()
    private var totalPar:Int = 0
    private lateinit var context:Context
    private lateinit var fragmentManager: FragmentManager
    var playersTotalScore = IntArray(scoreCardsWithData.size)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScorecardListAdapter.MyViewHolder {
        return MyViewHolder(
            ScorecardItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    class MyViewHolder(val binding: ScorecardItemRowBinding):RecyclerView.ViewHolder(binding.root) {
    }

    override fun onBindViewHolder(holder: ScorecardListAdapter.MyViewHolder, position: Int) {

        var currentScoreCard = scoreCardsWithData[position]

        with(holder) {
            binding.playerInitial.text = currentScoreCard.player.first_name + " " + currentScoreCard.player.last_name
            binding.hole1Par.text = parConversion(currentScoreCard.teeBox.hole1_yardage).toString()
            binding.hole2Par.text = parConversion(currentScoreCard.teeBox.hole2_yardage).toString()
            binding.hole3Par.text = parConversion(currentScoreCard.teeBox.hole3_yardage).toString()
            binding.hole4Par.text = parConversion(currentScoreCard.teeBox.hole4_yardage).toString()
            binding.hole5Par.text = parConversion(currentScoreCard.teeBox.hole5_yardage).toString()
            binding.hole6Par.text = parConversion(currentScoreCard.teeBox.hole6_yardage).toString()
            binding.hole7Par.text = parConversion(currentScoreCard.teeBox.hole7_yardage).toString()
            binding.hole8Par.text = parConversion(currentScoreCard.teeBox.hole8_yardage).toString()
            binding.hole9Par.text = parConversion(currentScoreCard.teeBox.hole9_yardage).toString()

            if(currentScoreCard.teeBox.hole10_yardage != null){
                binding.hole10Par.text= parConversion(currentScoreCard.teeBox.hole10_yardage!!).toString()
            }else{
                binding.hole10Par.visibility = View.GONE
                binding.hole10Label.visibility = View.GONE
                binding.hole10Score.visibility = View.GONE
            }

            if(currentScoreCard.teeBox.hole11_yardage != null){
                binding.hole11Par.text= parConversion(currentScoreCard.teeBox.hole11_yardage!!).toString()
            }else{
                binding.hole11Par.visibility = View.GONE
                binding.hole11Label.visibility = View.GONE
                binding.hole11Score.visibility = View.GONE
            }

            if(currentScoreCard.teeBox.hole12_yardage != null){
                binding.hole12Par.text= parConversion(currentScoreCard.teeBox.hole12_yardage!!).toString()
            }else{
                binding.hole12Par.visibility = View.GONE
                binding.hole12Label.visibility = View.GONE
                binding.hole12Score.visibility = View.GONE
            }

            if(currentScoreCard.teeBox.hole13_yardage != null){
                binding.hole13Par.text= parConversion(currentScoreCard.teeBox.hole13_yardage!!).toString()
            }else{
                binding.hole13Par.visibility = View.GONE
                binding.hole13Label.visibility = View.GONE
                binding.hole13Score.visibility = View.GONE
            }

            if(currentScoreCard.teeBox.hole14_yardage != null){
                binding.hole14Par.text= parConversion(currentScoreCard.teeBox.hole14_yardage!!).toString()
            }else{
                binding.hole14Par.visibility = View.GONE
                binding.hole14Label.visibility = View.GONE
                binding.hole14Score.visibility = View.GONE
            }

            if(currentScoreCard.teeBox.hole15_yardage != null){
                binding.hole15Par.text= parConversion(currentScoreCard.teeBox.hole15_yardage!!).toString()
            }else{
                binding.hole15Par.visibility = View.GONE
                binding.hole15Label.visibility = View.GONE
                binding.hole15Score.visibility = View.GONE
            }

            if(currentScoreCard.teeBox.hole16_yardage != null){
                binding.hole16Par.text= parConversion(currentScoreCard.teeBox.hole16_yardage!!).toString()
            }else{
                binding.hole16Par.visibility = View.GONE
                binding.hole16Label.visibility = View.GONE
                binding.hole16Score.visibility = View.GONE
            }

            if(currentScoreCard.teeBox.hole17_yardage != null){
                binding.hole17Par.text= parConversion(currentScoreCard.teeBox.hole17_yardage!!).toString()
            }else{
                binding.hole17Par.visibility = View.GONE
                binding.hole17Label.visibility = View.GONE
                binding.hole17Score.visibility = View.GONE
            }

            if(currentScoreCard.teeBox.hole18_yardage != null){
                binding.hole18Par.text= parConversion(currentScoreCard.teeBox.hole18_yardage!!).toString()
            }else{
                binding.hole18Par.visibility = View.GONE
                binding.hole18Label.visibility = View.GONE
                binding.hole18Score.visibility = View.GONE
            }

            if(currentScoreCard.scoreCard.hole1_score != null) {
                binding.hole1Score.text = currentScoreCard.scoreCard.hole1_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole1_score!!
            }
            else {
                binding.hole1Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole2_score != null) {
                binding.hole2Score.text = currentScoreCard.scoreCard.hole2_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole2_score!!
            }
            else {
                binding.hole2Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole3_score != null) {
                binding.hole3Score.text = currentScoreCard.scoreCard.hole3_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole3_score!!
            }
            else {
                binding.hole3Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole4_score != null) {
                binding.hole4Score.text = currentScoreCard.scoreCard.hole4_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole4_score!!
            }
            else {
                binding.hole4Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole5_score != null) {
                binding.hole5Score.text = currentScoreCard.scoreCard.hole5_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole5_score!!
            }
            else {
                binding.hole5Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole6_score != null) {
                binding.hole6Score.text = currentScoreCard.scoreCard.hole6_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole6_score!!
            }
            else {
                binding.hole6Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole7_score != null) {
                binding.hole7Score.text = currentScoreCard.scoreCard.hole7_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole7_score!!
            }
            else {
                binding.hole7Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole8_score != null) {
                binding.hole8Score.text = currentScoreCard.scoreCard.hole8_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole8_score!!
            }
            else {
                binding.hole8Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole9_score != null) {
                binding.hole9Score.text = currentScoreCard.scoreCard.hole9_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole9_score!!
            }
            else {
                binding.hole9Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole10_score != null) {
                binding.hole10Score.text = currentScoreCard.scoreCard.hole10_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole10_score!!
            }
            else {
                binding.hole10Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole11_score != null) {
                binding.hole11Score.text = currentScoreCard.scoreCard.hole11_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole11_score!!
            }
            else {
                binding.hole11Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole12_score != null) {
                binding.hole12Score.text = currentScoreCard.scoreCard.hole12_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole12_score!!
            }
            else {
                binding.hole12Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole13_score != null) {
                binding.hole13Score.text = currentScoreCard.scoreCard.hole13_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole13_score!!
            }
            else {
                binding.hole13Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole14_score != null) {
                binding.hole14Score.text = currentScoreCard.scoreCard.hole14_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole14_score!!
            }
            else {
                binding.hole14Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole15_score != null) {
                binding.hole15Score.text = currentScoreCard.scoreCard.hole15_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole15_score!!
            }
            else {
                binding.hole15Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole16_score != null) {
                binding.hole16Score.text = currentScoreCard.scoreCard.hole16_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole16_score!!
            }
            else {
                binding.hole16Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole17_score != null) {
                binding.hole17Score.text = currentScoreCard.scoreCard.hole17_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole17_score!!
            }
            else {
                binding.hole17Score.text = "Enter score"
            }

            if(currentScoreCard.scoreCard.hole18_score != null) {
                binding.hole18Score.text = currentScoreCard.scoreCard.hole18_score.toString()
                playersTotalScore[position] += currentScoreCard.scoreCard.hole18_score!!
            }
            else {
                binding.hole18Score.text = "Enter score"
            }

            binding.playerTotal.text = playersTotalScore[position].toString()

            // If the round is in progress allow the user to enter scores
            if(currentScoreCard.round.inProgress) {
                binding.hole1Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole1_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole1_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole1_score!!
                                }
                                currentScoreCard.scoreCard.hole1_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog
                                if (buttonValueFromDialog != 0) {
                                    binding.hole1Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }

                binding.hole2Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole2_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole2_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole2_score!!
                                }
                                currentScoreCard.scoreCard.hole2_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog
                                if (buttonValueFromDialog != 0) {
                                    binding.hole2Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole3Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole3_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole3_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole3_score!!
                                }
                                currentScoreCard.scoreCard.hole3_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole3Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole4Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole4_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole4_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole4_score!!
                                }
                                currentScoreCard.scoreCard.hole4_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole4Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole5Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole5_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole5_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole5_score!!
                                }
                                currentScoreCard.scoreCard.hole5_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole5Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole6Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole6_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole6_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole6_score!!
                                }
                                currentScoreCard.scoreCard.hole6_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole6Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole7Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole7_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole7_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole7_score!!
                                }
                                currentScoreCard.scoreCard.hole7_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole7Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole8Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole8_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole8_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole8_score!!
                                }
                                currentScoreCard.scoreCard.hole8_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole8Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole9Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole9_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole9_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole9_score!!
                                }
                                currentScoreCard.scoreCard.hole9_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole9Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole10Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole10_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole10_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole10_score!!
                                }
                                currentScoreCard.scoreCard.hole10_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole10Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole11Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole11_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole11_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole11_score!!
                                }
                                currentScoreCard.scoreCard.hole11_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole11Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole12Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole12_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole12_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole12_score!!
                                }
                                currentScoreCard.scoreCard.hole12_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole12Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole13Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole13_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole13_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole13_score!!
                                }
                                currentScoreCard.scoreCard.hole13_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole13Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole14Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole14_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole14_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole14_score!!
                                }
                                currentScoreCard.scoreCard.hole14_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole14Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole15Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole15_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole15_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole15_score!!
                                }
                                currentScoreCard.scoreCard.hole15_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole15Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole16Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole16_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole16_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole16_score!!
                                }
                                currentScoreCard.scoreCard.hole16_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole16Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole17Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole17_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole17_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole17_score!!
                                }
                                currentScoreCard.scoreCard.hole17_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole17Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
                binding.hole18Score.setOnClickListener {
                    //Call the dialog fragment
                    val score = SelectScoreDialog()
                    //set custom listener trigger
                    score.customListerTrig(object : SelectScoreDialog.CustomListener {
                        override fun closeEvent(id: String, buttonValueFromDialog: Int) {

                            Log.i("PlayerTotal Score", "This is player ${position}'s score ${buttonValueFromDialog}")
                            if (buttonValueFromDialog != currentScoreCard.scoreCard.hole18_score) {

                                Log.i("Testing Adapter", "This is score ${buttonValueFromDialog}")
                                if (currentScoreCard.scoreCard.hole18_score != null) {
                                    playersTotalScore[position] = playersTotalScore[position] - currentScoreCard.scoreCard.hole18_score!!
                                }
                                currentScoreCard.scoreCard.hole18_score = buttonValueFromDialog
                                playersTotalScore[position] += buttonValueFromDialog!!
                                if (buttonValueFromDialog != 0) {
                                    binding.hole18Score.text = buttonValueFromDialog.toString()
                                }
                                binding.playerTotal.text = playersTotalScore[position].toString()
                            }
                        }
                    })
                    score.show(fragmentManager, "golfDiag")
                }
            }
            else {
                binding.hole1Score.isClickable = false
                binding.hole2Score.isClickable = false
                binding.hole3Score.isClickable = false
                binding.hole4Score.isClickable = false
                binding.hole5Score.isClickable = false
                binding.hole6Score.isClickable = false
                binding.hole7Score.isClickable = false
                binding.hole8Score.isClickable = false
                binding.hole9Score.isClickable = false
                binding.hole10Score.isClickable = false
                binding.hole11Score.isClickable = false
                binding.hole12Score.isClickable = false
                binding.hole13Score.isClickable = false
                binding.hole14Score.isClickable = false
                binding.hole15Score.isClickable = false
                binding.hole16Score.isClickable = false
                binding.hole17Score.isClickable = false
                binding.hole18Score.isClickable = false
            }

            binding.parTotal.text = totalPar.toString()
            totalPar = 0
        }
    }



    override fun getItemCount(): Int {
        return scoreCardsWithData.size
    }

    fun setData(scoreCards: List<ScorecardWithData>) {
        this.scoreCardsWithData = scoreCards
        playersTotalScore = IntArray(itemCount)
        notifyDataSetChanged()
    }

    fun getData(): List<ScorecardWithData> {
        return scoreCardsWithData
    }

    fun setContext(context: Context) {
        this.context = context
    }

    fun setChildFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    private fun parConversion(yardage: Int): Int {

        var par: Int = when (yardage) {
            in 0..250 -> {
                3
            }
            in 251..470 -> {
                4
            }
            in 471..690 -> {
                5
            }
            else -> {
                6
            }
        }
        totalPar += par
        return par
    }

    //score for current hole dialog
    class SelectScoreDialog: DialogFragment() {

        private var dl: CustomListener? = null

        // interface to detect dialog close event
        interface CustomListener {
            fun closeEvent(id: String, valueToPass: Int)
        }

        fun customListerTrig(l: ScorecardListAdapter.SelectScoreDialog.CustomListener) {
            dl = l
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        }

        override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            val view = inflater.inflate(R.layout.fragment_score_picker, container, false)

            view.findViewById<Button>(R.id.scoreOne_BTN).setOnClickListener{
                dl?.closeEvent("golfDiag", 1)
                this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreTwo_BTN).setOnClickListener{
                dl?.closeEvent("golfDiag", 2)
                this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreThree_BTN).setOnClickListener{
                dl?.closeEvent("golfDiag", 3)
                this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreFour_BTN).setOnClickListener{
                        dl?.closeEvent("golfDiag", 4)
                        this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreFive_BTN).setOnClickListener{
                        dl?.closeEvent("golfDiag", 5)
                        this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreSix_BTN).setOnClickListener{
                        dl?.closeEvent("golfDiag", 6)
                        this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreSeven_BTN).setOnClickListener{
                        dl?.closeEvent("golfDiag", 7)
                        this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreEight_BTN).setOnClickListener{
                        dl?.closeEvent("golfDiag", 8)
                        this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreNine_BTN).setOnClickListener{
                        dl?.closeEvent("golfDiag", 9)
                        this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreTen_BTN).setOnClickListener{
                        dl?.closeEvent("golfDiag", 10)
                        this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreEleven_BTN).setOnClickListener{
                        dl?.closeEvent("golfDiag", 11)
                        this.dismiss()
            }
            view.findViewById<Button>(R.id.scoreTwelve_BTN).setOnClickListener{
                        dl?.closeEvent("golfDiag", 12)
                        this.dismiss()
            }
            view.findViewById<Button>(R.id.cancelScore_BTN).setOnClickListener{
                        dl?.closeEvent("golfDiag", 0)
                        this.dismiss()
            }
            return view
        }
    }
}