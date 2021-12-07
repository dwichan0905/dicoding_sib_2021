package com.dicoding.courseschedule.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.TimePickerFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private lateinit var viewModel: AddCourseViewModel

    private lateinit var edCourseName: TextInputEditText
    private lateinit var spinDay: Spinner
    private lateinit var tvStartTime: TextView
    private lateinit var tvEndTime: TextView
    private lateinit var edLecturer: TextInputEditText
    private lateinit var edNote: TextInputEditText

    private var timePickerOutput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        edCourseName = findViewById(R.id.add_ed_course_name)
        spinDay = findViewById(R.id.add_spinner_day)
        val btnStartTime = findViewById<ImageButton>(R.id.add_btn_start_time)
        tvStartTime = findViewById(R.id.add_tv_start_time)
        val btnEndTime = findViewById<ImageButton>(R.id.add_btn_end_time)
        tvEndTime = findViewById(R.id.add_tv_end_time)
        edLecturer = findViewById(R.id.add_ed_lecturer)
        edNote = findViewById(R.id.add_ed_note)

        val factory = AddCourseViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this, factory)[
                AddCourseViewModel::class.java
        ]
        viewModel.saved.observe(this) {
            val state = it.getContentIfNotHandled() ?: return@observe
            if (!state) {
                Snackbar.make(
                    findViewById(R.id.linear_layout),
                    R.string.input_empty_message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        btnStartTime.setOnClickListener { showStartTimePicker() }
        btnEndTime.setOnClickListener { showEndTimePicker() }
    }

    private fun showStartTimePicker() {
        timePickerOutput = START_TIME
        TimePickerFragment().show(supportFragmentManager, "timePicker")
    }

    private fun showEndTimePicker() {
        timePickerOutput = END_TIME
        TimePickerFragment().show(supportFragmentManager, "timePicker")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_insert -> {
                viewModel.insertCourse(
                    courseName = edCourseName.text.toString(),
                    lecturer = edLecturer.text.toString(),
                    day = spinDay.selectedItemPosition,
                    startTime = tvStartTime.text.toString(),
                    endTime = tvEndTime.text.toString(),
                    note = edNote.text.toString()
                )
                finish()
            }
        }
        return true
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (timePickerOutput) {
            START_TIME -> tvStartTime.text = dateFormat.format(calendar.time)
            END_TIME -> tvEndTime.text = dateFormat.format(calendar.time)
        }
    }

    companion object {
        private const val START_TIME = "start_time"
        private const val END_TIME = "end_time"
    }
}