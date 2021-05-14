package com.android.mulliganmarker

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import com.android.mulliganmarker.databinding.FragmentNewPlayerBinding
import com.android.mulliganmarker.model.Player

import com.android.mulliganmarker.viewmodel.PlayerViewModel



class NewPlayerFragment : Fragment() {

    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,R.layout.fragment_new_player,container,false)
        val binding = FragmentNewPlayerBinding.inflate(inflater,container,false)
        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)


        binding.lifecycleOwner = viewLifecycleOwner
        binding.playerViewModel = playerViewModel



        return binding.root
    }

}