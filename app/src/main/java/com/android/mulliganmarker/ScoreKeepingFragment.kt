package com.android.mulliganmarker

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.adapter.ScorecardListAdapter
import com.android.mulliganmarker.databinding.FragmentScoreKeepingBinding
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.model.ScorecardWithData
import com.android.mulliganmarker.viewmodel.RoundViewModel
import com.android.mulliganmarker.viewmodel.ScorecardViewModel
import com.google.android.material.tabs.TabLayout
import java.text.DateFormat
import javax.security.auth.callback.Callback
import kotlin.math.round

private const val TAG = "ScoreKeepingFragment"

class ScoreKeepingFragment(round: Round?) : Fragment() {

    interface Callbacks {
        fun onRoundCompleted()
    }

    private val binding: FragmentScoreKeepingBinding by viewBinding()

    private val scorecardViewModel: ScorecardViewModel by lazy {
        ViewModelProvider(this).get(ScorecardViewModel::class.java)
    }

    private val roundViewModel: RoundViewModel by lazy {
        ViewModelProvider(this).get(RoundViewModel::class.java)
    }

    private var callback: Callbacks? = null

    private val currentRound = round

    val adapter = ScorecardListAdapter()

    private lateinit var roundScorecards: List<ScorecardWithData>
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score_keeping, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        binding.scorecardRecyclerView.layoutManager = layoutManager

        Log.i(TAG,"Round ID: ${currentRound!!.round_id}")

        roundViewModel.getRound(currentRound.round_id).observe(
                viewLifecycleOwner,
                Observer { round ->
                    binding.scorecardCourseName.text = round.course.name
                    binding.scorecardCourseLocation.text = round.course.location
                    binding.scorecardDate.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(round.roundsList.date)
                }
        )

        //Need to insert the round as the parameter for the get Target Score Cards function
        scorecardViewModel.getTargetScoreCards(currentRound.round_id).observe(
                viewLifecycleOwner,
                Observer {
                    Log.i(TAG, "This number of scorecards: ${it.size}")
                    adapter.setData(it)
                    adapter.setChildFragmentManager(parentFragmentManager)
                    binding.scorecardRecyclerView.adapter = adapter
                    context?.let { it1 -> adapter.setContext(it1) }
                }
        )

        if (currentRound.inProgress) {
            //
        }
        else {
            binding.saveScoreCardBTN.visibility = View.GONE
        }

        binding.saveScoreCardBTN.setOnClickListener {
            // Dialog to confirm finishing the round
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Are you sure this round is finished? You won't be able to make any further changes afterward.")
            builder.setPositiveButton("Yes") { _, _ ->
                currentRound.inProgress = false
                roundViewModel.saveRound(currentRound)

                callback?.onRoundCompleted()
            }
            builder.setNegativeButton("No") { _, _ ->

            }
            builder.create().show()
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun onStop() {
        super.onStop()
        roundScorecards = adapter.getData()

        // Save any changes to the scorecards in the database
        for (scorecard in roundScorecards) {
            scorecardViewModel.saveScorecard(scorecard.scoreCard)
        }
    }
}