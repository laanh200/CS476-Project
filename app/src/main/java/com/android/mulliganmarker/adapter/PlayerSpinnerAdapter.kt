package com.android.mulliganmarker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.SpinnerItemBinding
import com.android.mulliganmarker.model.Player
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import com.skydoves.powerspinner.PowerSpinnerInterface
import com.skydoves.powerspinner.PowerSpinnerView

class PlayerSpinnerAdapter(powerSpinnerView: PowerSpinnerView) : RecyclerView.Adapter<PlayerSpinnerAdapter.PlayerSpinnerViewHolder>(), PowerSpinnerInterface<Player> {
    override var index: Int = powerSpinnerView.selectedIndex

    override val spinnerView: PowerSpinnerView = powerSpinnerView

    override var onSpinnerItemSelectedListener: OnSpinnerItemSelectedListener<Player>? = null

    private val spinnerItems: MutableList<Player> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerSpinnerAdapter.PlayerSpinnerViewHolder {
        val binding = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PlayerSpinnerViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                        ?: return@setOnClickListener
                notifyItemSelected(position)
            }
        }
    }

    override fun onBindViewHolder(holder: PlayerSpinnerAdapter.PlayerSpinnerViewHolder, position: Int) {
        holder.bind(spinnerItems[position], spinnerView)
    }

    override fun setItems(itemList: List<Player>) {
        this.spinnerItems.clear()
        this.spinnerItems.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun notifyItemSelected(index: Int) {
        if (index == RecyclerView.NO_POSITION) return
        val oldIndex = this.index
        this.index = index
        val fullName = this.spinnerItems[index].first_name + " " + this.spinnerItems[index].last_name
        this.spinnerView.notifyItemSelected(index, fullName)
        this.onSpinnerItemSelectedListener?.onItemSelected(
                oldIndex = oldIndex,
                oldItem = oldIndex.takeIf { it != RecyclerView.NO_POSITION }?.let { spinnerItems[oldIndex] },
                newIndex = index,
                newItem = spinnerItems[index]
        )
    }

    override fun getItemCount(): Int = this.spinnerItems.size

    class PlayerSpinnerViewHolder(val binding: SpinnerItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Player, spinnerView: PowerSpinnerView) {
            val fullName = item.first_name + " " + item.last_name
            binding.itemTextView.text = fullName
        }
    }
}