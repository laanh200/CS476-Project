package com.android.mulliganmarker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.ScorecardCustomRowBinding
import com.android.mulliganmarker.model.Scorecard

class AddScorecardAdapter: RecyclerView.Adapter<AddScorecardAdapter.AddScorecardViewHolder>() {

    private var scorecardList = emptyList<Scorecard>()
    private var playerList = emptyList<String>()
    private var teeBoxList = emptyList<String>()

    class AddScorecardViewHolder(val binding: ScorecardCustomRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Scorecard, player: String, teeBox: String) {
            binding.scorecardItemPlayer.text = player
            binding.scorecardItemTeeBox.text = teeBox
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddScorecardViewHolder {
        return AddScorecardViewHolder(ScorecardCustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AddScorecardViewHolder, position: Int) {
        val currentScorecard = scorecardList[position]
        val player = playerList[position]
        val teeBox = teeBoxList[position]
        holder.bind(currentScorecard, player, teeBox)
    }

    override fun getItemCount(): Int {
        return scorecardList.size
    }

    fun setItems(itemList: List<Scorecard>, playerList: List<String>, teeBoxList: List<String>) {
        scorecardList = itemList
        this.playerList = playerList
        this.teeBoxList = teeBoxList
    }
}