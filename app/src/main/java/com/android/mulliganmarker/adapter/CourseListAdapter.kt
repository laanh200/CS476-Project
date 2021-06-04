package com.android.mulliganmarker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.R

import com.android.mulliganmarker.databinding.CoursePickerRowBinding
import com.android.mulliganmarker.model.Course


class CourseListAdapter ( var courses: List<Course>, private val onItemClicked: (Course) -> Unit): RecyclerView.Adapter<CourseListAdapter.CourseViewHolder>() {

    class CourseViewHolder(val binding: CoursePickerRowBinding):RecyclerView.ViewHolder(binding.root) {
        private lateinit var course: Course

        fun bind(course: Course) {
            this.course = course
            itemView.findViewById<TextView>(R.id.course_name).text = "Name: " + course.name
            itemView.findViewById<TextView>(R.id.course_location).text = "Location: " + course.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(CoursePickerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentCourse = courses[position]

        holder.itemView.setOnClickListener { onItemClicked (currentCourse) }
        holder.bind(currentCourse)
    }

    override fun getItemCount(): Int {
        return courses.size
    }
}
