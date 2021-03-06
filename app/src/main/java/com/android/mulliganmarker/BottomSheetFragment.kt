package com.android.mulliganmarker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.bottomsheetdialogfragment.viewBinding
import android.widget.Button
import com.android.mulliganmarker.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {

    interface Callbacks {
        fun onNewPlayer()
        fun onNewRound()
    }

    private var callbacks: Callbacks? = null

    //private lateinit var newRoundButton: Button
   // private lateinit var newPlayerButton: Button

    private val binding: FragmentBottomSheetBinding by viewBinding()

    override fun onAttach(context: Context) {

        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        /* Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        newPlayerButton = view.findViewById(R.id.create_new_playerBTN)
        newRoundButton = view.findViewById(R.id.create_new_roundBTN)

        return view
*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createNewRoundBTN.setOnClickListener {
            callbacks?.onNewRound()
        }
        binding.createNewPlayerBTN.setOnClickListener {
            callbacks?.onNewPlayer()
        }
    }

    override fun onDetach() {

        super.onDetach()
        callbacks = null
    }

}