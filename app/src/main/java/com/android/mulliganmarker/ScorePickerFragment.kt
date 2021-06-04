package com.android.mulliganmarker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.DialogFragment
import com.android.mulliganmarker.databinding.FragmentScorePickerBinding
import javax.security.auth.callback.Callback


class ScorePickerFragment : DialogFragment() {


    interface CallBacks {
        fun onScorePicked(score: Int)
    }

    private val binding: FragmentScorePickerBinding by viewBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score_picker, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.scoreOneBTN.setOnClickListener {
           onScorePicked(1)
        }
        binding.scoreTwoBTN.setOnClickListener {
            onScorePicked(2)
        }
        binding.scoreThreeBTN.setOnClickListener {
            onScorePicked(3)
        }
        binding.scoreFourBTN.setOnClickListener {
            onScorePicked(4)
        }
        binding.scoreFiveBTN.setOnClickListener {
            onScorePicked(5)
        }
        binding.scoreSixBTN.setOnClickListener {
            onScorePicked(6)
        }
        binding.scoreSevenBTN.setOnClickListener {
            onScorePicked(7)
        }
        binding.scoreEightBTN.setOnClickListener {
            onScorePicked(8)
        }
        binding.scoreNineBTN.setOnClickListener {
            onScorePicked(9)
        }
        binding.scoreTenBTN.setOnClickListener {
            onScorePicked(10)
        }
        binding.scoreElevenBTN.setOnClickListener {
            onScorePicked(11)
        }
        binding.scoreTwelveBTN.setOnClickListener {
            onScorePicked(12)
        }
    }

    private fun onScorePicked(score:Int){
        targetFragment?.let {fragment ->
            // Passes the selected course to the fragment
            (fragment as CallBacks).onScorePicked(score)
        }
        this@ScorePickerFragment.dismiss()
    }


    companion object {
        fun newInstance(): ScorePickerFragment {
            return ScorePickerFragment()
        }
    }
}



