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
import com.android.mulliganmarker.adapter.AddScorecardAdapter
import com.android.mulliganmarker.databinding.FragmentNewScorecardBinding
import com.android.mulliganmarker.adapter.PlayerSpinnerAdapter
import com.android.mulliganmarker.adapter.ScorecardListAdapter
import com.android.mulliganmarker.adapter.TeeBoxSpinnerAdapter
import com.android.mulliganmarker.model.Player
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.model.Scorecard
import com.android.mulliganmarker.model.TeeBox
import com.android.mulliganmarker.viewmodel.PlayerViewModel
import com.android.mulliganmarker.viewmodel.ScorecardViewModel
import com.android.mulliganmarker.viewmodel.TeeBoxViewModel
import com.skydoves.powerspinner.PowerSpinnerView

private const val TAG = "NewScorecardFragment"

class NewScorecardFragment(round: Round?):  Fragment() {

    interface Callbacks {
        fun onScorecardsCreated(round: Round?)
    }

    private var callback: Callbacks? = null

    private val binding: FragmentNewScorecardBinding by viewBinding()

    private val playerViewModel: PlayerViewModel by lazy {
        ViewModelProvider(this).get(PlayerViewModel::class.java)
    }

    private val teeBoxViewModel: TeeBoxViewModel by lazy {
        ViewModelProvider(this).get(TeeBoxViewModel::class.java)
    }

    private val scorecardViewModel: ScorecardViewModel by lazy {
        ViewModelProvider(this).get(ScorecardViewModel::class.java)
    }

    private var playerList: List<Player> = emptyList()
    private var teeBoxList: List<TeeBox> = emptyList()
    private var scorecardList: MutableList<Scorecard> = arrayListOf()

    private val newRound = round

    private var adapter = AddScorecardAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teeBoxViewModel.loadTeeBoxes(newRound!!.course_id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_scorecard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scorecardRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.scorecardRecyclerView.adapter = adapter

        // Observes list of players
        playerViewModel.readAllPlayers?.observe(
                viewLifecycleOwner,
                Observer { players ->
                    players?.let {
                        Log.i(TAG, "Got players ${players.size}")
                        playerList = players
                    }
                }
        )

        // Observes list of tee boxes for the course
        teeBoxViewModel.teeBoxesLiveData.observe(
                viewLifecycleOwner,
                Observer { teeBoxes ->
                    teeBoxes?.let {
                        Log.i(TAG, "Got tee boxes ${teeBoxes.size}")
                        teeBoxList = teeBoxes
                    }
                }
        )

        binding.addScorecardBTN.setOnClickListener {
            var playerId = -1
            var teeBoxId = -1

            // Custom dialog to select the player and tee box
            val builder = AlertDialog.Builder(requireContext())

            val inflater = requireActivity().layoutInflater

            val dialogView = inflater.inflate(R.layout.dialog_add_scorecard, null)

            val playerSpinner : PowerSpinnerView = dialogView.findViewById(R.id.playerDropdown)
            val teeBoxSpinner : PowerSpinnerView = dialogView.findViewById(R.id.teeBoxDropdown)

            // Player dropdown
            playerSpinner.apply {
                setSpinnerAdapter(PlayerSpinnerAdapter(this))
                setOnSpinnerItemSelectedListener<Player> {
                    _, _, _, newItem ->
                    playerId = newItem.player_id
                }
                setItems(playerList)
                getSpinnerRecyclerView().layoutManager = LinearLayoutManager(context)
                lifecycleOwner = this@NewScorecardFragment
            }

            // Tee box dropdown
            teeBoxSpinner.apply {
                setSpinnerAdapter(TeeBoxSpinnerAdapter(this))
                setOnSpinnerItemSelectedListener<TeeBox> {
                    _, _, _, newItem ->
                    teeBoxId = newItem.tee_box_id
                }
                setItems(teeBoxList)
                getSpinnerRecyclerView().layoutManager = LinearLayoutManager(context)
                lifecycleOwner = this@NewScorecardFragment
            }

            builder.setView(dialogView)
            builder.setTitle("Add to scorecard")
            builder.setPositiveButton("Ok") { _, _ ->
                // If player and tee box have been selected
                if(playerId != -1 && teeBoxId != -1) {
                    val newScorecard = Scorecard(0, newRound!!.round_id, playerId, teeBoxId,
                            null, null, null, null, null, null, null, null, null,
                            null, null, null, null, null, null, null, null, null)
                    scorecardList.add(newScorecard)

                    // update the recycler view with the new scorecard
                    adapter.setItems(scorecardList)
                    adapter.notifyDataSetChanged()
                    Log.i(TAG, "Got scorecards ${scorecardList.size}")
                    binding.scorecardRecyclerView.adapter = adapter

                    Toast.makeText(activity, "Added player to scorecard", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(activity,"Failure in adding to scorecard. Player and tee box need to be selected.", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("Cancel") { _, _ ->

            }
            builder.create().show()
        }

        binding.confirmScorecardBTN.setOnClickListener {
            // Dialog to confirm creating the scorecards
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Would you like to add these players to the scorecard?")
            builder.setPositiveButton("Yes") { _, _ ->
                for (scorecard in scorecardList) {
                    scorecardViewModel.insertScorecard(scorecard)
                }

                Toast.makeText(activity, "Scorecard created", Toast.LENGTH_SHORT).show()

                callback?.onScorecardsCreated(newRound)
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
}