package com.android.mulliganmarker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.ScorecardItemRowBinding
import com.android.mulliganmarker.model.ScorecardWithData


class ScorecardListAdapter:RecyclerView.Adapter<ScorecardListAdapter.MyViewHolder>() {

    private var scoreCardsWithData = emptyList<ScorecardWithData>()
    private var totalPar:Int = 0

    interface OnItemClickListener {
        fun onItemClick(item: ScorecardWithData)
    }
    private val listener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScorecardListAdapter.MyViewHolder {
        return MyViewHolder(
            ScorecardItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    }

    class MyViewHolder(val binding: ScorecardItemRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onBindViewHolder(holder: ScorecardListAdapter.MyViewHolder, position: Int) {
        var currentScoreCard = scoreCardsWithData[position]

        with(holder){
            binding.playerInitial.text = currentScoreCard.player.first_name + " " + currentScoreCard.player.last_name
            binding.hole1Par.text = parConversion(currentScoreCard.teeBox.hole1_yardage).toString()
            binding.hole2Par.text = parConversion(currentScoreCard.teeBox.hole2_yardage).toString()
            binding.hole3Par.text = parConversion(currentScoreCard.teeBox.hole3_yardage).toString()
            binding.hole4Par.text = parConversion(currentScoreCard.teeBox.hole4_yardage).toString()
            binding.hole5Par.text = parConversion(currentScoreCard.teeBox.hole5_yardage).toString()
            binding.hole6Par.text = parConversion(currentScoreCard.teeBox.hole6_yardage).toString()
            binding.hole7Par.text = parConversion(currentScoreCard.teeBox.hole7_yardage).toString()
            binding.hole8Par.text = parConversion(currentScoreCard.teeBox.hole8_yardage).toString()
            binding.hole9Par.text = parConversion(currentScoreCard.teeBox.hole9_yardage).toString()
            binding.hole10Par.text = currentScoreCard.teeBox.hole10_yardage?.let { parConversion(it).toString() }
            binding.hole11Par.text = currentScoreCard.teeBox.hole11_yardage?.let { parConversion(it).toString() }
            binding.hole12Par.text = currentScoreCard.teeBox.hole12_yardage?.let { parConversion(it).toString() }
            binding.hole13Par.text = currentScoreCard.teeBox.hole13_yardage?.let { parConversion(it).toString() }
            binding.hole14Par.text = currentScoreCard.teeBox.hole14_yardage?.let { parConversion(it).toString() }
            binding.hole15Par.text = currentScoreCard.teeBox.hole15_yardage?.let { parConversion(it).toString() }
            binding.hole16Par.text = currentScoreCard.teeBox.hole16_yardage?.let { parConversion(it).toString() }
            binding.hole17Par.text = currentScoreCard.teeBox.hole17_yardage?.let { parConversion(it).toString() }
            binding.hole18Par.text = currentScoreCard.teeBox.hole18_yardage?.let { parConversion(it).toString() }


        }
    }

    override fun getItemCount(): Int {
        return scoreCardsWithData.size
    }
    fun setData(scoreCards: List<ScorecardWithData>){
        this.scoreCardsWithData = scoreCards
    }

    private fun parConversion(yardage: Int):Int{

        var par: Int = when (yardage) {
            in 0..250 -> {
                3
            }
            in 251..470 -> {
                4
            }
            in 471..690 -> {
                5
            }
            else -> {
                6
            }
        }
        totalPar += par
        return par
    }
}