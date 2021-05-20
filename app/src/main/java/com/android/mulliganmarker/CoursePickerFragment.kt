package com.android.mulliganmarker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.mulliganmarker.databinding.FragmentCoursePickerBinding
import com.android.mulliganmarker.list.CourseListAdapter
import com.android.mulliganmarker.model.Course
import com.android.mulliganmarker.viewmodel.CourseViewModel
import com.android.mulliganmarker.viewmodel.PlayerViewModel

private const val TAG = "CoursePickerFragment"

class CoursePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onCoursePicked(courseId: Int, courseName: String)
    }

    private val binding: FragmentCoursePickerBinding by viewBinding()
  //  private var adapter: CourseAdapter? = CourseAdapter(emptyList())
    private var adapter: CourseListAdapter?= null
    private lateinit var courseViewModel : CourseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_course_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseViewModel = ViewModelProvider(this).get(CourseViewModel::class.java)

        binding.coursePickerRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.coursePickerRecyclerview.adapter = adapter

        /* livedata observer
        courseViewModel.coursesListLiveData.observe(
                viewLifecycleOwner,
                Observer { courses ->
                    courses?.let {
                        Log.i(TAG, "Got courses ${courses.size}")
                        adapter = CourseAdapter(courses)
                        binding.coursePickerRecyclerview.adapter = adapter
                    }
                }
        )
*/
        courseViewModel.coursesListLiveData.observe(
                viewLifecycleOwner,
                Observer { courses ->
                    courses?.let {
                        Log.i(TAG, "Got courses ${courses.size}")
                        adapter = CourseListAdapter(courses){
                            // TargetFragment stores the fragment instance that started the CoursePickerFragment
                            targetFragment?.let {fragment ->
                                // Passes the selected course to the fragment
                                (fragment as Callbacks).onCoursePicked(it.course_id, it.name)
                            }
                            this@CoursePickerFragment.dismiss()
                        }
                        binding.coursePickerRecyclerview.adapter = adapter
                    }
                }
        )
    }

    /* Wraps Item View for the RecyclerView
    private inner class CourseHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var course: Course

        init {
            itemView.setOnClickListener(this)
        }

        // Binds data from the model layer
        fun bind(course: Course) {
            this.course = course
            itemView.findViewById<TextView>(R.id.course_name).text = "Name: " + course.name
            itemView.findViewById<TextView>(R.id.course_location).text = "Location: " + course.location

        }

        override fun onClick(v: View?) {
            // TargetFragment stores the fragment instance that started the CoursePickerFragment
            targetFragment?.let {fragment ->
                // Passes the selected course to the fragment
                (fragment as Callbacks).onCoursePicked(course.course_id, course.name)
            }
            this@CoursePickerFragment.dismiss()
        }
    }

    // Creates CourseHolders when asked and binds CourseHolders to data from the model layer
    private inner class CourseAdapter(var courses: List<Course>) : RecyclerView.Adapter<CourseHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
            val view = layoutInflater.inflate(R.layout.course_picker_row, parent, false)
            return CourseHolder(view)
        }

        override fun getItemCount() = courses.size

        override fun onBindViewHolder(holder: CourseHolder, position: Int) {
            val course = courses[position]
            holder.bind(course)
        }
    }
*/
    companion object {
        fun newInstance(): CoursePickerFragment {
            return CoursePickerFragment()
        }

    }
}