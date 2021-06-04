package com.android.mulliganmarker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.RoundCustomRowBinding
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.model.RoundWithCourse
import java.text.DateFormat

class RoundHistoryListAdapter:RecyclerView.Adapter<RoundHistoryListAdapter.MyViewHolder>() {
    private var roundHistoryList = emptyList<RoundWithCourse>()

    class MyViewHolder(val binding: RoundCustomRowBinding):RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RoundCustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentCourse = roundHistoryList[position]

            with(holder) {
                binding.courseItemDate.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(currentCourse.roundsList.date)
                binding.courseItemName.text = currentCourse.course.name
                binding.courseItemLocation.text = currentCourse.course.location
        }
    }

    override fun getItemCount(): Int {
        return roundHistoryList.size
    }
    fun setData(newRoundWithCourseList: List<RoundWithCourse>){
        this.roundHistoryList = newRoundWithCourseList
        notifyDataSetChanged()
    }
    fun getRoundAtPosition(position: Int): Round {
        return roundHistoryList[position].roundsList
    }
}