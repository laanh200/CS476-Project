package com.android.mulliganmarker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

import com.android.mulliganmarker.databinding.FragmentScoreKeepingBinding
import com.android.mulliganmarker.list.ScorecardListAdapter
import com.android.mulliganmarker.viewmodel.ScorecardViewModel

class ScoreKeepingFragment : Fragment() {

    private val binding: FragmentScoreKeepingBinding by viewBinding()

    private lateinit var scorecardViewModel: ScorecardViewModel

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

        binding.scorecardRecyclerView

        val adapter = ScorecardListAdapter()

        binding.scorecardRecyclerView.adapter = adapter

        binding.scorecardRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)

        scorecardViewModel = ViewModelProvider(this).get(ScorecardViewModel::class.java)

        /*Need to insert the round as the parameter for the get Target Score Cards function
        scorecardViewModel.getTargetScoreCards().observe(viewLifecycleOwner, Observer {
                 adapter.setData(it)
             }
        )
        */
        binding.saveScoreCardBTN.setOnClickListener {

        }
    }

    override fun onStop() {
        super.onStop()
       // scorecardViewModel.saveScoreCards()
    }

}