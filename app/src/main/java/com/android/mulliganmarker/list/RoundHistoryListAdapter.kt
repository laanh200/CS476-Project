package com.android.mulliganmarker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.RoundCustomRowBinding
import com.android.mulliganmarker.model.Course
import com.android.mulliganmarker.model.CourseWithRound
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.viewmodel.CourseViewModel
import java.text.DateFormat

class RoundHistoryListAdapter:RecyclerView.Adapter<RoundHistoryListAdapter.MyViewHolder>() {
    private var roundHistoryList = emptyList<CourseWithRound>()

    class MyViewHolder(val binding: RoundCustomRowBinding):RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RoundCustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentRound = roundHistoryList[position]

        with(holder){
            binding.courseItemDate.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(currentRound.roundsList[position].date)
            binding.courseItemName.text = currentRound.course.name
        }
    }

    override fun getItemCount(): Int {
        return roundHistoryList.size
    }
    fun setData(newRoundList: List<CourseWithRound>){
        this.roundHistoryList = newRoundList
        notifyDataSetChanged()
    }
}