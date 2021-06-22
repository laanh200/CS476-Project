package com.android.mulliganmarker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.RoundCustomRowBinding
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.model.RoundWithCourse
import java.text.DateFormat

class RoundHistoryListAdapter(private val onItemClicked: (RoundWithCourse) -> Unit): RecyclerView.Adapter<RoundHistoryListAdapter.RoundViewHolder>() {
    private var roundHistoryList = emptyList<RoundWithCourse>()

    class RoundViewHolder(val binding: RoundCustomRowBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(round: RoundWithCourse) {
            binding.courseItemDate.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(round.roundsList.date)
            binding.courseItemName.text = round.course.name
            binding.courseItemLocation.text = round.course.location
            if(round.roundsList.inProgress) {
                binding.courseItemStatus.text = "In Progress"
            }
            else {
                binding.courseItemStatus.text = "Finished"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundViewHolder {
        return RoundViewHolder(RoundCustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RoundViewHolder, position: Int) {
        val currentRound = roundHistoryList[position]

        holder.bind(currentRound)
        holder.itemView.setOnClickListener { onItemClicked(currentRound) }
    }

    override fun getItemCount(): Int {
        return roundHistoryList.size
    }

    fun setData(newRoundWithCourseList: List<RoundWithCourse>) {
        this.roundHistoryList = newRoundWithCourseList
        notifyDataSetChanged()
    }

    fun getRoundAtPosition(position: Int): Round {
        return roundHistoryList[position].roundsList
    }
}