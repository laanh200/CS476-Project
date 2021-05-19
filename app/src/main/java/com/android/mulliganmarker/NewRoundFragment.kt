package com.android.mulliganmarker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.mulliganmarker.databinding.FragmentNewRoundBinding
import com.android.mulliganmarker.model.Player
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.viewmodel.PlayerViewModel
import com.android.mulliganmarker.viewmodel.RoundViewModel
import java.text.DateFormat
import java.util.*

private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0

class NewRoundFragment : Fragment(), CoursePickerFragment.Callbacks, DatePickerFragment.Callbacks {

    interface Callbacks {
        fun onRoundStarted()
    }

    private var callback: Callbacks? = null

    private val binding: FragmentNewRoundBinding by viewBinding()

    private lateinit var roundViewModel: RoundViewModel

    private var courseId = 0
    private var displayDate = Date()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callbacks
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_round, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roundViewModel = ViewModelProvider(this).get(RoundViewModel::class.java)

        binding.newRoundCourse.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                // Dialog with list of Courses to pick from
                CoursePickerFragment.newInstance().apply {
                    setTargetFragment(this@NewRoundFragment, 0)
                    show(this@NewRoundFragment.requireFragmentManager(), "CoursePickerDialog")
                }
            }
        }

        binding.newRoundDate.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                // Date Dialog picker
                DatePickerFragment.newInstance(displayDate).apply {
                    setTargetFragment(this@NewRoundFragment, REQUEST_DATE)
                    show(this@NewRoundFragment.requireFragmentManager(), DIALOG_DATE)
                }
            }
        }

        binding.startNewRoundBTN.setOnClickListener {
            startNewRound()
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    // Receives the course id and name from the CoursePicker
    override fun onCoursePicked(courseId: Int, courseName: String) {
        this.courseId = courseId
        binding.newRoundCourse.setText(courseName)
    }

    // Receives the selected date from the DatePicker
    override fun onDateSelected(date: Date) {
        displayDate = date
        binding.newRoundDate.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(displayDate))
    }

    private fun startNewRound() {
        //Create variables to store the data from the text views and convert to appropriate type
        var courseNotSelected = (binding.newRoundCourse.text.toString()).isNullOrBlank()
        var dateNotSelected = (binding.newRoundDate.text.toString()).isNullOrBlank()
        var courseId = courseId
        var date = displayDate

        //Check if the two important fields are blank
        if(!courseNotSelected && !dateNotSelected) {
            val newRound = Round(0, courseId, date)
            roundViewModel.insertRound(newRound)

            Toast.makeText(activity, "Successfully started round!", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(activity,"Failure in starting round. Course and date need to be selected.", Toast.LENGTH_SHORT).show()
        }

        callback?.onRoundStarted()
    }
}