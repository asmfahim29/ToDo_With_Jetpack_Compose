package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.Task
import com.example.todoapp.data.repo.TaskRepo

class TaskViewModel: ViewModel() {
    private val repo = TaskRepo()
    val tasks = repo.tasks

    init {
        if(tasks.value.isEmpty()){
            repo.addTask(Task(1, "Islamic Reminder", "Pray five times in every single day", isCompleted = false))
            repo.addTask(Task(2, "Daily Task", "Coding for 1 hour", isCompleted = false))
        }
    }

    fun addTask(task: Task) {
        if  (task.title.isEmpty()) return
        repo.addTask(task)
    }

    fun updateTask(task: Task) {
        repo.updateTask(task)
    }

    fun deleteTask(task: Task) {
        repo.deleteTask(task)
    }

    fun toggleTask(task: Task) {
        repo.toggleTask(task)
    }

}