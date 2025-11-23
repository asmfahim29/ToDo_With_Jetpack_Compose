package com.example.todoapp.data

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)

data class TaskList(
    val tasks: List<Task>
)
