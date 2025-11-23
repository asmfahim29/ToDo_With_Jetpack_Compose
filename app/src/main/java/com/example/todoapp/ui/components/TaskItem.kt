package com.example.todoapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.Task
import com.example.todoapp.ui.theme.ToDoAppTheme


@Composable
fun TskItem(
    task: Task,
    onComplete: (Task) -> Unit = {},
    onDelete: (Task) -> Unit = {},
    onEdit: (Task) -> Unit = {},
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{
                onComplete(task)
            },
        shape = RectangleShape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Row (
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TaskTitleRow(task = task, isCompleted = task.isCompleted, onComplete = {onComplete(task)})
            }

            IconButton(
                onClick = {
                    onEdit(task)
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(color = 0xFF3F51B5),
                    contentColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Check")
            }

            IconButton(
                onClick = {
                    onDelete(task)
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
                ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Check",
                )
            }
        }

    }

}

@Composable
fun TaskTitleRow(
    task: Task,
    isCompleted: Boolean,
    onComplete: (Task) -> Unit
) {
    var customColor = Color(color = 0xFF3F51B5)
    var customTextDecoration = TextDecoration.None

    if(isCompleted) {
        customColor = Color(color = 0xFF10E218)
        customTextDecoration = TextDecoration.LineThrough
    }

    IconButton(onClick = {
        onComplete(task)
    }){
        Icon(Icons.Default.CheckCircle, contentDescription = "Check", tint = customColor)
    }
    Column (
        modifier = Modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = task.title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            ),
            textDecoration =  customTextDecoration
        )
        Text(
            text = task.description,
            style = MaterialTheme.typography.titleMedium,
            textDecoration =  customTextDecoration
        )
    }

}



@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    ToDoAppTheme {
        TskItem(Task(title = "Task Title", description = "Task Description", id = 1))
    }
}