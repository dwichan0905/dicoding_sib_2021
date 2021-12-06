package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class DetailTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val edTitle = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val edDescription = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val edDueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        val btnDelete = findViewById<Button>(R.id.btn_delete_task)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[
                DetailTaskViewModel::class.java
        ]

        val bundle = intent.extras
        if (bundle != null) {
            val taskId = bundle.getInt(TASK_ID)

            viewModel.setTaskId(taskId)
            viewModel.task.observe(this) { task ->
                if (task != null) {
                    edTitle.setText(task.title)
                    edDescription.setText(task.description)

                    val date = Date(task.dueDateMillis)
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    edDueDate.setText(dateFormat.format(date))
                }
            }

            btnDelete.setOnClickListener {
                viewModel.deleteTask()
                finish()
            }
        }
    }
}