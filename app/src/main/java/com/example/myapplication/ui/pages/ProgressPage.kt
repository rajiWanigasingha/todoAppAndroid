package com.example.myapplication.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.database.Todos
import com.example.myapplication.viewModel.TodoViewModel

class ProgressPage {
    @Composable
    fun Content(viewModel: TodoViewModel = viewModel()) {

        val progress = viewModel.allInProgress.collectAsState(initial = emptyList())

        fun deleteAction(id: Int) {
            viewModel.deleteTodo(id)
        }

        fun updateProgress(id: Int) {
            viewModel.updateProgressToComplete(id)
        }

        Box() {
            Column(
                Modifier.padding(20.dp)
            ) {
                Row {
                    Text("In Progress", style = MaterialTheme.typography.headlineSmall)
                }

                if (progress.value.isNotEmpty()) {
                    progress.value.forEach { todos: Todos ->
                        Spacer(Modifier.size(15.dp))
                        Row {
                            ElevatedCard(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Row {
                                        Text(
                                            todos.title,
                                            style = MaterialTheme.typography.headlineSmall
                                        )
                                    }
                                    Spacer(Modifier.size(4.dp))
                                    Row(
                                        Modifier.padding(horizontal = 4.dp)
                                    ) {
                                        Text(
                                            todos.description,
                                            style = MaterialTheme.typography.labelLarge,
                                            color = MaterialTheme.colorScheme.secondary
                                        )
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Column {
                                            TextButton(onClick = {
                                                deleteAction(todos.id)
                                            }) {
                                                Text(
                                                    "Delete",
                                                    color = MaterialTheme.colorScheme.error,
                                                    style = MaterialTheme.typography.bodyLarge
                                                )
                                            }
                                        }
                                        Spacer(Modifier.size(8.dp))
                                        Column {
                                            Button(
                                                onClick = {
                                                    updateProgress(todos.id)
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                                                )
                                            ) {
                                                Row {
                                                    Column {
                                                        Icon(
                                                            painter = painterResource(R.drawable.complete),
                                                            contentDescription = "progress",
                                                            Modifier.size(20.dp)
                                                        )
                                                    }
                                                    Spacer(Modifier.size(4.dp))
                                                    Column {
                                                        Text(
                                                            "Complete",
                                                            style = MaterialTheme.typography.bodyMedium
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(R.drawable.nodata),
                                contentDescription = "No data",
                                Modifier.size(100.dp),
                                tint = Color.Unspecified
                            )
                        }
                        Spacer(Modifier.size(20.dp))
                        Row {
                            Text(
                                "No Progress Had Made",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }


            }
        }
    }
}