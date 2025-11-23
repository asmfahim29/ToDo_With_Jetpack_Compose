package com.example.todoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.data.Task
import com.example.todoapp.ui.components.TskItem
import com.example.todoapp.viewmodel.TaskViewModel


@Composable
fun TaskListScreen (
    taskViewModel: TaskViewModel = viewModel()
) {
    val tasks =  taskViewModel.tasks.collectAsState()
    var showDialog = rememberSaveable { mutableStateOf(false) }

    Scaffold (
        topBar = {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .background(MaterialTheme.colorScheme.primary)
                    .statusBarsPadding(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Task List",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog.value = true
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn {
                item (tasks) {
                    tasks.value.forEach { task ->
                        TskItem(
                            task = task,
                            onComplete = { taskViewModel.toggleTask(it) },
                            onDelete = { taskViewModel.deleteTask(it) },
                            onEdit = { taskViewModel.updateTask(it) }
                        )
                    }
                }
            }
        }

    }

    if(showDialog.value) {
        TaskInputDialog(
            onDismiss = { showDialog.value = false },
            onConfirm = {
                title, description ->
                taskViewModel.addTask(
                    Task(
                        title = title,
                        description = description,
                        id = tasks.value.size + 1
                    )
                )
            }
        )
    }
}