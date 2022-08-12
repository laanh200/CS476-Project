package com.android.mulliganmarker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.SpinnerItemBinding
import com.android.mulliganmarker.model.Course
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import com.skydoves.powerspinner.PowerSpinnerInterface
import com.skydoves.powerspinner.PowerSpinnerView

class CourseSpinnerAdapter(powerSpinnerView: PowerSpinnerView) : RecyclerView.Adapter<CourseSpinnerAdapter.CourseSpinnerViewHolder>(), PowerSpinnerInterface<Course> {

    override var index: Int = powerSpinnerView.selectedIndex

    override val spinnerView: PowerSpinnerView = powerSpinnerView

    override var onSpinnerItemSelectedListener: OnSpinnerItemSelectedListener<Course>? = null

    private val spinnerItems: MutableList<Course> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseSpinnerViewHolder {
        val binding = SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CourseSpinnerViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION}
                    ?: return@setOnClickListener
                notifyItemSelected(position)
            }
        }
    }

    override fun onBindViewHolder(holder: CourseSpinnerViewHolder, position: Int) {
        holder.bind(spinnerItems[position], spinnerView)
    }

    override fun setItems(itemList: List<Course>) {
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

    class CourseSpinnerViewHolder(val binding: SpinnerItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Course, spinnerView: PowerSpinnerView) {
            binding.itemTextView.text = item.name
        }
    }
}