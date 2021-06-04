package com.android.mulliganmarker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.SpinnerItemBinding
import com.android.mulliganmarker.model.TeeBox
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import com.skydoves.powerspinner.PowerSpinnerInterface
import com.skydoves.powerspinner.PowerSpinnerView

class TeeBoxSpinnerAdapter(powerSpinnerView: PowerSpinnerView) : RecyclerView.Adapter<TeeBoxSpinnerAdapter.TeeBoxSpinnerViewHolder>(), PowerSpinnerInterface<TeeBox> {
    override var index: Int = powerSpinnerView.selectedIndex

    override val spinnerView: PowerSpinnerView = powerSpinnerView

    override var onSpinnerItemSelectedListener: OnSpinnerItemSelectedListener<TeeBox>? = null

    private val spinnerItems: MutableList<TeeBox> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeeBoxSpinnerAdapter.TeeBoxSpinnerViewHolder {
        val binding = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TeeBoxSpinnerViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                        ?: return@setOnClickListener
                notifyItemSelected(position)
            }
        }
    }

    override fun onBindViewHolder(holder: TeeBoxSpinnerViewHolder, position: Int) {
        holder.bind(spinnerItems[position], spinnerView)
    }

    override fun setItems(itemList: List<TeeBox>) {
        this.spinnerItems.clear()
        this.spinnerItems.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun notifyItemSelected(index: Int) {
        if (index == RecyclerView.NO_POSITION) return
        val oldIndex = this.index
        this.index = index
        this.spinnerView.notifyItemSelected(index, this.spinnerItems[index].name)
        this.onSpinnerItemSelectedListener?.onItemSelected(
                oldIndex = oldIndex,
                oldItem = oldIndex.takeIf { it != RecyclerView.NO_POSITION }?.let { spinnerItems[oldIndex] },
                newIndex = index,
                newItem = spinnerItems[index]
        )
    }

    override fun getItemCount(): Int = this.spinnerItems.size

    class TeeBoxSpinnerViewHolder(val binding: SpinnerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeeBox, spinnerView: PowerSpinnerView) {
            binding.itemTextView.text = item.name
        }
    }
}