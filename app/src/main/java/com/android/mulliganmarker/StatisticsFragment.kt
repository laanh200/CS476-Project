package com.android.mulliganmarker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mulliganmarker.adapter.CourseSpinnerAdapter
import com.android.mulliganmarker.adapter.PlayerSpinnerAdapter
import com.android.mulliganmarker.databinding.FragmentStatisticsBinding
import com.android.mulliganmarker.model.Course
import com.android.mulliganmarker.model.Player
import com.android.mulliganmarker.model.Scorecard
import com.android.mulliganmarker.model.ScorecardWithData
import com.android.mulliganmarker.viewmodel.CourseViewModel
import com.android.mulliganmarker.viewmodel.PlayerViewModel
import com.android.mulliganmarker.viewmodel.ScorecardViewModel
import com.skydoves.powerspinner.PowerSpinnerView
import java.text.DateFormat
import java.time.LocalDate
import java.util.*
import kotlin.math.roundToInt

private const val TAG = "StatisticsFragment"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0

class StatisticsFragment : Fragment(), DatePickerFragment.Callbacks {

    private val binding: FragmentStatisticsBinding by viewBinding()

    private val playerViewModel: PlayerViewModel by lazy {
        ViewModelProvider(this).get(PlayerViewModel::class.java)
    }

    private val courseViewModel: CourseViewModel by lazy {
        ViewModelProvider(this).get(CourseViewModel::class.java)
    }

    private val scorecardViewModel: ScorecardViewModel by lazy {
        ViewModelProvider(this).get(ScorecardViewModel::class.java)
    }

    private var dateButton = ""

    private var courseId = -1
    private var startDate = Date()
    private var endDate = Date()

    private var scorecardList: List<ScorecardWithData> = arrayListOf()
    private var filteredScorecards: MutableList<ScorecardWithData> = arrayListOf()

    private var totalHoles = 0
    private var totalPars = 0
    private var totalBirdies = 0
    private var totalEagles = 0
    private var averageScoreOverall = 0.0
    private var averageScoreFront9 = 0.0
    private var averageScoreBack9 = 0.0
    private var bestScoreOverall = 0
    private var bestScoreFront9 = 0
    private var bestScoreBack9 = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerViewModel.readAllPlayers?.observe(
            viewLifecycleOwner,
            Observer { players ->
                players.let {
                    Log.i(TAG, "Got ${players.size} players")
                    binding.statsPlayerDropdown.setItems(players)
                }

            }
        )

        courseViewModel.coursesListLiveData.observe(
            viewLifecycleOwner,
            Observer { courses ->
                courses.let {
                    Log.i(TAG, "Got ${courses.size} courses")
                    binding.courseDropdown.setItems(courses)
                }
            }
        )

        scorecardViewModel.playerScorecardsLiveData.observe(
            viewLifecycleOwner,
            Observer { scorecards ->
                scorecards.let {
                    Log.i(TAG, "Got ${scorecards.size} player scorecards")
                    scorecardList = scorecards
                    filterScorecards(courseId, startDate, endDate)
                }
            }
        )

        // Player dropdown
        binding.statsPlayerDropdown.apply {
            setSpinnerAdapter(PlayerSpinnerAdapter(this))
            setOnSpinnerItemSelectedListener<Player> {
                    _, _, _, newItem ->
                scorecardViewModel.getPlayerScorecards(newItem.player_id)
            }
            getSpinnerRecyclerView().layoutManager = LinearLayoutManager(context)
            lifecycleOwner = this@StatisticsFragment
        }

        binding.courseDropdown.apply {
            setSpinnerAdapter(CourseSpinnerAdapter(this))
            setOnSpinnerItemSelectedListener<Course> {
                    _, _, _, newItem ->
                courseId = newItem.course_id
                filterScorecards(courseId, startDate, endDate)
            }
            getSpinnerRecyclerView().layoutManager = LinearLayoutManager(context)
            lifecycleOwner = this@StatisticsFragment
        }

        binding.startDate.setOnClickListener {
            DatePickerFragment.newInstance(startDate).apply {
                setTargetFragment(this@StatisticsFragment, REQUEST_DATE)
                show(this@StatisticsFragment.requireFragmentManager(), DIALOG_DATE)
            }

            dateButton = "startDate"
        }

        binding.endDate.setOnClickListener {
            DatePickerFragment.newInstance(endDate).apply {
                setTargetFragment(this@StatisticsFragment, REQUEST_DATE)
                show(this@StatisticsFragment.requireFragmentManager(), DIALOG_DATE)
            }

            dateButton = "endDate"
        }
    }

    // Receives the selected date from the DatePicker
    override fun onDateSelected(date: Date) {

        if(dateButton == "startDate")
        {
            startDate = date
            binding.startDate.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(startDate)
        }

        if(dateButton == "endDate")
        {
            endDate = date
            binding.endDate.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(endDate)
        }

        filterScorecards(courseId, startDate, endDate)
    }

    private fun filterScorecards(courseId: Int, fromDate: Date, toDate: Date) {
        resetValues()

        for(scorecard : ScorecardWithData in scorecardList)
        {
            if((scorecard.round.course_id == courseId) && (scorecard.round.date >= fromDate) && (scorecard.round.date <= toDate))
            {
                filteredScorecards.add(scorecard)
            }
        }

        Log.i(TAG, "Player scorecards filtered to ${filteredScorecards.size}")
        if(filteredScorecards.isNotEmpty())
            calculateStatistics()
        updateUI()
    }

    private fun calculateStatistics() {

        var front9Scores : MutableList<Int> = arrayListOf()
        var back9Scores : MutableList<Int> = arrayListOf()
        var overallScores : MutableList<Int> = arrayListOf()

        for(scorecard : ScorecardWithData in filteredScorecards)
        {
            //Calculate totals
            totalScores(scorecard)

            front9Scores.add(getScoreFront9(scorecard))
            back9Scores.add(getScoreBack9(scorecard))
            overallScores.add(getScoreFront9(scorecard) + getScoreBack9(scorecard))
        }

        //Calculate average scores
        averageScoreFront9 = front9Scores.average()
        averageScoreBack9 = back9Scores.average()
        averageScoreOverall = overallScores.average()

        //Calculate best scores
        bestScoreFront9 = front9Scores.minOrNull() ?: 0
        bestScoreBack9 = back9Scores.minOrNull() ?: 0
        bestScoreOverall = overallScores.minOrNull() ?: 0
    }

    private fun updateUI() {
        val placeholder = "--"

        if(filteredScorecards.isEmpty())
        {
            binding.totalHoles.text = placeholder
            binding.totalPars.text = placeholder
            binding.totalBirdies.text = placeholder
            binding.totalEagles.text = placeholder
            binding.avgScoreOverall.text = placeholder
            binding.avgScoreFront.text = placeholder
            binding.avgScoreBack.text = placeholder
            binding.bestScoreOverall.text = placeholder
            binding.bestScoreFront.text = placeholder
            binding.bestScoreBack.text = placeholder
        }
        else
        {
            binding.totalHoles.text = totalHoles.toString()
            binding.totalPars.text = totalPars.toString()
            binding.totalBirdies.text = totalBirdies.toString()
            binding.totalEagles.text = totalEagles.toString()
            binding.avgScoreOverall.text = averageScoreOverall.roundToInt().toString() + " (" + (averageScoreOverall.roundToInt() - (getParFront9() + getParBack9())).toString() + ")"
            binding.avgScoreFront.text = averageScoreFront9.roundToInt().toString() + " (" + (averageScoreFront9.roundToInt() - getParFront9()).toString() + ")"
            binding.avgScoreBack.text = averageScoreBack9.roundToInt().toString() + " (" + (averageScoreBack9.roundToInt() - getParBack9()).toString() + ")"
            binding.bestScoreOverall.text = bestScoreOverall.toString() + " (" + (bestScoreOverall - (getParFront9() + getParBack9())).toString() + ")"
            binding.bestScoreFront.text = bestScoreFront9.toString() + " (" + (bestScoreFront9 - getParFront9()).toString() + ")"
            binding.bestScoreBack.text = bestScoreBack9.toString() + " (" + (bestScoreBack9 - getParBack9()).toString() + ")"
        }

    }

    private fun resetValues() {
        filteredScorecards.clear()
        totalHoles = 0
        totalPars = 0
        totalBirdies = 0
        totalEagles = 0
    }

    private fun parConversion(yardage: Int): Int {

        var par: Int = when (yardage) {
            in 0..220 -> {
                3
            }
            in 221..450 -> {
                4
            }
            in 451..690 -> {
                5
            }
            else -> {
                6
            }
        }

        return par
    }

    private fun determineScore(score: Int) {

        when (score) {
            0 -> totalPars++
            -1 -> totalBirdies++
            -2 -> totalEagles++
        }
    }

    private fun totalScores(scorecard: ScorecardWithData) {
        if(scorecard.scoreCard.hole1_score != null)
        {
            totalHoles++
            determineScore(scorecard.scoreCard.hole1_score!! - parConversion(scorecard.teeBox.hole1_yardage))
        }
        if(scorecard.scoreCard.hole2_score != null)
        {
            totalHoles++
            determineScore(scorecard.scoreCard.hole2_score!! - parConversion(scorecard.teeBox.hole2_yardage))
        }
        if(scorecard.scoreCard.hole3_score != null)
        {
            totalHoles++
            determineScore(scorecard.scoreCard.hole3_score!! - parConversion(scorecard.teeBox.hole3_yardage))
        }
        if(scorecard.scoreCard.hole4_score != null)
        {
            totalHoles++
            determineScore(scorecard.scoreCard.hole4_score!! - parConversion(scorecard.teeBox.hole4_yardage))
        }
        if(scorecard.scoreCard.hole5_score != null)
        {
            totalHoles++
            determineScore(scorecard.scoreCard.hole5_score!! - parConversion(scorecard.teeBox.hole5_yardage))
        }
        if(scorecard.scoreCard.hole6_score != null)
        {
            totalHoles++
            determineScore(scorecard.scoreCard.hole6_score!! - parConversion(scorecard.teeBox.hole6_yardage))
        }
        if(scorecard.scoreCard.hole7_score != null)
        {
            totalHoles++
            determineScore(scorecard.scoreCard.hole7_score!! - parConversion(scorecard.teeBox.hole7_yardage))
        }
        if(scorecard.scoreCard.hole8_score != null)
        {
            totalHoles++
            determineScore(scorecard.scoreCard.hole8_score!! - parConversion(scorecard.teeBox.hole8_yardage))
        }
        if(scorecard.scoreCard.hole9_score != null)
        {
            totalHoles++
            determineScore(scorecard.scoreCard.hole9_score!! - parConversion(scorecard.teeBox.hole9_yardage))
        }
        if(scorecard.scoreCard.hole10_score != null)
        {
            totalHoles++
            scorecard.teeBox.hole10_yardage?.let { determineScore(scorecard.scoreCard.hole10_score!! - parConversion(it)) }
        }
        if(scorecard.scoreCard.hole11_score != null)
        {
            totalHoles++
            scorecard.teeBox.hole11_yardage?.let { determineScore(scorecard.scoreCard.hole11_score!! - parConversion(it)) }
        }
        if(scorecard.scoreCard.hole12_score != null)
        {
            totalHoles++
            scorecard.teeBox.hole12_yardage?.let { determineScore(scorecard.scoreCard.hole12_score!! - parConversion(it)) }
        }
        if(scorecard.scoreCard.hole13_score != null)
        {
            totalHoles++
            scorecard.teeBox.hole13_yardage?.let { determineScore(scorecard.scoreCard.hole13_score!! - parConversion(it)) }
        }
        if(scorecard.scoreCard.hole14_score != null)
        {
            totalHoles++
            scorecard.teeBox.hole14_yardage?.let { determineScore(scorecard.scoreCard.hole14_score!! - parConversion(it)) }
        }
        if(scorecard.scoreCard.hole15_score != null)
        {
            totalHoles++
            scorecard.teeBox.hole15_yardage?.let { determineScore(scorecard.scoreCard.hole15_score!! - parConversion(it)) }
        }
        if(scorecard.scoreCard.hole16_score != null)
        {
            totalHoles++
            scorecard.teeBox.hole16_yardage?.let { determineScore(scorecard.scoreCard.hole16_score!! - parConversion(it)) }
        }
        if(scorecard.scoreCard.hole17_score != null)
        {
            totalHoles++
            scorecard.teeBox.hole17_yardage?.let { determineScore(scorecard.scoreCard.hole17_score!! - parConversion(it)) }
        }
        if(scorecard.scoreCard.hole18_score != null)
        {
            totalHoles++
            scorecard.teeBox.hole18_yardage?.let { determineScore(scorecard.scoreCard.hole18_score!! - parConversion(it)) }
        }
    }

    private fun getParFront9(): Int {

        var par = 0

        par += parConversion(filteredScorecards[0].teeBox.hole1_yardage)
        par += parConversion(filteredScorecards[0].teeBox.hole2_yardage)
        par += parConversion(filteredScorecards[0].teeBox.hole3_yardage)
        par += parConversion(filteredScorecards[0].teeBox.hole4_yardage)
        par += parConversion(filteredScorecards[0].teeBox.hole5_yardage)
        par += parConversion(filteredScorecards[0].teeBox.hole6_yardage)
        par += parConversion(filteredScorecards[0].teeBox.hole7_yardage)
        par += parConversion(filteredScorecards[0].teeBox.hole8_yardage)
        par += parConversion(filteredScorecards[0].teeBox.hole9_yardage)

        return par
    }

    private fun getParBack9(): Int {

        var par = 0

        filteredScorecards[0].teeBox.hole10_yardage?.let { par += parConversion(it) }
        filteredScorecards[0].teeBox.hole11_yardage?.let { par += parConversion(it) }
        filteredScorecards[0].teeBox.hole12_yardage?.let { par += parConversion(it) }
        filteredScorecards[0].teeBox.hole13_yardage?.let { par += parConversion(it) }
        filteredScorecards[0].teeBox.hole14_yardage?.let { par += parConversion(it) }
        filteredScorecards[0].teeBox.hole15_yardage?.let { par += parConversion(it) }
        filteredScorecards[0].teeBox.hole16_yardage?.let { par += parConversion(it) }
        filteredScorecards[0].teeBox.hole17_yardage?.let { par += parConversion(it) }
        filteredScorecards[0].teeBox.hole18_yardage?.let { par += parConversion(it) }

        return par
    }

    private fun getScoreFront9(scorecard: ScorecardWithData): Int {

        var score = 0

        scorecard.scoreCard.hole1_score?.let { score += it }
        scorecard.scoreCard.hole2_score?.let { score += it }
        scorecard.scoreCard.hole3_score?.let { score += it }
        scorecard.scoreCard.hole4_score?.let { score += it }
        scorecard.scoreCard.hole5_score?.let { score += it }
        scorecard.scoreCard.hole6_score?.let { score += it }
        scorecard.scoreCard.hole7_score?.let { score += it }
        scorecard.scoreCard.hole8_score?.let { score += it }
        scorecard.scoreCard.hole9_score?.let { score += it }

        return score
    }

    private fun getScoreBack9(scorecard: ScorecardWithData): Int {

        var score = 0

        scorecard.scoreCard.hole10_score?.let { score += it }
        scorecard.scoreCard.hole11_score?.let { score += it }
        scorecard.scoreCard.hole12_score?.let { score += it }
        scorecard.scoreCard.hole13_score?.let { score += it }
        scorecard.scoreCard.hole14_score?.let { score += it }
        scorecard.scoreCard.hole15_score?.let { score += it }
        scorecard.scoreCard.hole16_score?.let { score += it }
        scorecard.scoreCard.hole17_score?.let { score += it }
        scorecard.scoreCard.hole18_score?.let { score += it }

        return score
    }

    private fun validRound(scorecard: ScorecardWithData): Boolean {
        // if the round was 9 holes
        if((scorecard.scoreCard.hole1_score != null && scorecard.scoreCard.hole2_score != null && scorecard.scoreCard.hole3_score != null
            && scorecard.scoreCard.hole4_score != null && scorecard.scoreCard.hole5_score != null && scorecard.scoreCard.hole6_score != null
            && scorecard.scoreCard.hole7_score != null && scorecard.scoreCard.hole8_score != null && scorecard.scoreCard.hole9_score != null) ||
            (scorecard.scoreCard.hole10_score != null && scorecard.scoreCard.hole11_score != null && scorecard.scoreCard.hole12_score != null
                    && scorecard.scoreCard.hole13_score != null && scorecard.scoreCard.hole14_score != null && scorecard.scoreCard.hole15_score != null
                    && scorecard.scoreCard.hole16_score != null && scorecard.scoreCard.hole17_score != null && scorecard.scoreCard.hole18_score != null))
        {
            return true
        }

        // if the round was 18 holes
        if(scorecard.scoreCard.hole1_score != null && scorecard.scoreCard.hole2_score != null && scorecard.scoreCard.hole3_score != null
            && scorecard.scoreCard.hole4_score != null && scorecard.scoreCard.hole5_score != null && scorecard.scoreCard.hole6_score != null
            && scorecard.scoreCard.hole7_score != null && scorecard.scoreCard.hole8_score != null && scorecard.scoreCard.hole9_score != null
            && scorecard.scoreCard.hole10_score != null && scorecard.scoreCard.hole11_score != null && scorecard.scoreCard.hole12_score != null
            && scorecard.scoreCard.hole13_score != null && scorecard.scoreCard.hole14_score != null && scorecard.scoreCard.hole15_score != null
            && scorecard.scoreCard.hole16_score != null && scorecard.scoreCard.hole17_score != null && scorecard.scoreCard.hole18_score != null)
        {
            return true
        }

        return false
    }
}