package com.dicoding.todoapp.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.data.TaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    private val taskLiveData = MutableLiveData<Task>()

    fun setTask(task: Task) {
        taskLiveData.value = task
    }

    fun insertTask() {
        viewModelScope.launch {
            taskLiveData.value?.let { taskRepository.insertTask(it) }
        }
    }
}