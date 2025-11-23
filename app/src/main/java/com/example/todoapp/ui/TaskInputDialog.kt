package com.example.todoapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TaskInputDialog (
    onDismiss: ()-> Unit,
    onConfirm: (title: String, description: String) -> Unit
) {
    var taskTitleInput by rememberSaveable { mutableStateOf("") }
    var taskDescriptionInput by rememberSaveable { mutableStateOf("") }

    AlertDialog (
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Add Task")
        },
        text = {
            Column {
                TextField(
                    value = taskTitleInput,
                    onValueChange = { taskTitleInput = it },
                    label = { Text(text = "Title") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = taskDescriptionInput,
                    onValueChange = { taskDescriptionInput = it },
                    label = { Text(text = "Description") }
                )
            }
        },
        confirmButton = {
            OutlinedButton (
                onClick = {
                    if(taskTitleInput.isNotBlank() && taskDescriptionInput.isNotBlank()){
                        onConfirm(taskTitleInput, taskDescriptionInput)
                        taskTitleInput = ""
                        taskDescriptionInput = ""
                    }
                }
            ) {
                Text(text = "Add")
            }
        },
        dismissButton = {
            TextButton (
                onClick = onDismiss
            ) {
                Text(text = "Cancel")
            }
        }
    )
}


@Preview
@Composable
fun TaskInputDialogPreview() {
    TaskInputDialog(
        onDismiss = {},
        onConfirm = { _, _ -> }
    )
}