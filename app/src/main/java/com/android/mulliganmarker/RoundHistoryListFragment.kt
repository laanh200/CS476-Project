package com.android.mulliganmarker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.FragmentRoundHistoryListBinding
import com.android.mulliganmarker.databinding.RoundCustomRowBinding
import com.android.mulliganmarker.model.Course

import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.viewmodel.CourseViewModel
import com.android.mulliganmarker.viewmodel.RoundViewModel
import java.text.DateFormat


class RoundHistoryListFragment : Fragment() {

    private val binding: FragmentRoundHistoryListBinding by viewBinding()

    private lateinit var roundViewModel: RoundViewModel
    private lateinit var courseViewModel: CourseViewModel
    private var courseName: String?=null

    private var adapter: RoundHistoryListAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_round_history_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.roundHistoryListRecyclerView

        //val adapter = RoundHistoryListAdapter()

        binding.roundHistoryListRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(context)

        //Display orientation set vertical ->display list down
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        //Set layout equal to recycler view layout
        binding.roundHistoryListRecyclerView.layoutManager =  layoutManager

        roundViewModel = ViewModelProvider(this).get(RoundViewModel::class.java)

        /*
        roundViewModel.roundhistoryList.observe(viewLifecycleOwner, Observer {
                adapter.setData(it)
            }
        )*/

        roundViewModel.roundhistoryList.observe(viewLifecycleOwner, Observer {
            adapter = RoundHistoryListAdapter(it)
            binding.roundHistoryListRecyclerView.adapter = adapter
        })
    }

    inner class MyViewHolder(view:View): RecyclerView.ViewHolder(binding.root) {
        private lateinit var roundHistory : Round
        fun bind(round:Round)
        {
            this.roundHistory = round
            courseViewModel = ViewModelProvider(this@RoundHistoryListFragment).get(CourseViewModel::class.java)

            courseName = courseViewModel.getCourseName(roundHistory.course_id)

            itemView.findViewById<TextView>(R.id.course_item_name).text = "Course Name" + courseName
            itemView.findViewById<TextView>(R.id.course_item_date).text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(roundHistory.date)

        }
    }
    inner class RoundHistoryListAdapter(var rounds:List<Round>): RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
           // return MyViewHolder(RoundCustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            val view = layoutInflater.inflate(R.layout.round_custom_row,parent,false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val round = rounds[position]
            holder.bind(round)
/*
            with(holder){
                binding.courseItemDate.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(currentRound.date)
                binding.courseItemName.text = courseName
            }
            */
        }

        override fun getItemCount(): Int {
            return rounds.size
        }

    }
}