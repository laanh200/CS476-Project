package com.android.mulliganmarker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.ScorecardCustomRowBinding
import com.android.mulliganmarker.model.Scorecard

class AddScorecardAdapter: RecyclerView.Adapter<AddScorecardAdapter.AddScorecardViewHolder>() {

    private var scorecardList = emptyList<Scorecard>()

    class AddScorecardViewHolder(val binding: ScorecardCustomRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Scorecard) {
            binding.scorecardItemPlayer.text = item.player_id.toString()
            binding.scorecardItemTeeBox.text = item.tee_box_id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddScorecardViewHolder {
        return AddScorecardViewHolder(ScorecardCustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AddScorecardViewHolder, position: Int) {
        val currentScorecard = scorecardList[position]
        holder.bind(currentScorecard)
    }

    override fun getItemCount(): Int {
        return scorecardList.size
    }

    fun setItems(itemList: List<Scorecard>) {
        scorecardList = itemList
    }
}