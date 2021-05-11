package com.android.mulliganmarker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var newRoundButton: Button
    private lateinit var newPlayerButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        newPlayerButton = view.findViewById(R.id.create_new_playerBTN)
        newRoundButton = view.findViewById(R.id.create_new_roundBTN)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newRoundButton.setOnClickListener {

        }
        newPlayerButton.setOnClickListener {

        }
    }

}