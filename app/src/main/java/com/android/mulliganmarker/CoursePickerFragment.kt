package com.android.mulliganmarker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mulliganmarker.databinding.FragmentCoursePickerBinding
import com.android.mulliganmarker.adapter.CourseListAdapter
import com.android.mulliganmarker.viewmodel.CourseViewModel

private const val TAG = "CoursePickerFragment"

class CoursePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onCoursePicked(courseId: Int, courseName: String)
    }

    private val binding: FragmentCoursePickerBinding by viewBinding()

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

        // LiveData observer
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

    companion object {
        fun newInstance(): CoursePickerFragment {
            return CoursePickerFragment()
        }

    }
}