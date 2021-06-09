package com.android.mulliganmarker

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.adapter.ScorecardListAdapter
import com.android.mulliganmarker.databinding.FragmentScoreKeepingBinding
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.viewmodel.ScorecardViewModel
import com.google.android.material.tabs.TabLayout
import javax.security.auth.callback.Callback

private const val TAG = "ScoreKeepingFragment"

class ScoreKeepingFragment(round: Round?) : Fragment() {


    private val binding: FragmentScoreKeepingBinding by viewBinding()

    private lateinit var scorecardViewModel: ScorecardViewModel

    private var callback: Callback? = null

    private val currentRound = round
    

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

        val adapter = ScorecardListAdapter()

       // binding.scorecardRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        binding.scorecardRecyclerView.layoutManager = layoutManager

        scorecardViewModel = ViewModelProvider(this).get(ScorecardViewModel::class.java)

        Log.i(TAG,"Round ID: ${currentRound!!.round_id}")
        //Need to insert the round as the parameter for the get Target Score Cards function

        scorecardViewModel.getTargetScoreCards(currentRound!!.round_id).observe(viewLifecycleOwner, Observer {
                Log.i(TAG, "This number of scorecards: ${it.size}")
                adapter.setData(it)
                adapter.setChildFragmentManager(parentFragmentManager)
                binding.scorecardRecyclerView.adapter = adapter
                context?.let { it1 -> adapter.setContext(it1) }

            }
        )

        binding.saveScoreCardBTN.setOnClickListener {
          //  scorecardViewModel.finishRound()
        }


    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }
    override fun onStop() {
        super.onStop()
       // scorecardViewModel.saveScoreCards()
    }


}