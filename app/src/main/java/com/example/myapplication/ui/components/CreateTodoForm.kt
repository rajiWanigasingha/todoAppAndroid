package com.example.myapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.database.StateOfTodo
import com.example.myapplication.database.Todos
import com.example.myapplication.viewModel.TodoViewModel

@Composable
fun Form(dismissRequest: () -> Unit ,viewModel: TodoViewModel = viewModel()) {

    var titleInput by remember { mutableStateOf("") }
    var descriptionInput by remember { mutableStateOf("") }

    var titleInputError by remember { mutableStateOf(false) }
    var descriptionInputError by remember { mutableStateOf(false) }

    fun validateInput() {
        titleInputError = titleInput.isEmpty()
        descriptionInputError = descriptionInput.isEmpty()
    }

    fun createTodoObject(): Todos {
        return Todos(
            title = titleInput,
            description = descriptionInput,
            state = StateOfTodo.TODO
        )
    }

    fun createNewRecord(todos: Todos) {
        viewModel.addTodo(todos = todos)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(35.dp, 10.dp, 35.dp, 20.dp)
    ) {
        Row {
            Column {
                Icon(
                    painter = painterResource(R.drawable.baseline_title_24),
                    contentDescription = "title",
                    Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.size(4.dp))
            Column {
                OutlinedTextField(
                    value = titleInput,
                    onValueChange = {
                        titleInput = it
                    },
                    label = {
                        Text("Title")
                    },
                    placeholder = {
                        Text("School At 8.00 A.M")
                    },
                    supportingText = {
                        if (!titleInputError) {
                            Text("Add Todo Title")
                        } else {
                            Text("Title Must Not Be Empty")
                        }
                    },
                    trailingIcon = {
                        if (titleInput.isNotEmpty()) {
                            Icon(Icons.Rounded.Clear , contentDescription = "Clear" , modifier = Modifier.clickable {
                                titleInput = ""
                            })
                        }
                    },
                    isError = titleInputError,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(Modifier.size(10.dp))

        Row {
            Column {
                Icon(
                    painter = painterResource(R.drawable.baseline_description_24),
                    contentDescription = "description",
                    Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.size(4.dp))
            Column {
                OutlinedTextField(
                    value = descriptionInput,
                    onValueChange = {
                        descriptionInput = it
                    },
                    label = {
                        Text("Description")
                    },
                    placeholder = {
                        Text("New School Season Begin")
                    },
                    supportingText = {
                        if (!descriptionInputError) {
                            Text("Add Todo description")
                        } else {
                            Text("Description Must Not Be Empty")
                        }
                    },
                    trailingIcon = {
                        if (descriptionInput.isNotEmpty()) {
                            Icon(Icons.Rounded.Clear , contentDescription = "Clear" , modifier = Modifier.clickable {
                                descriptionInput = ""
                            })
                        }
                    },
                    isError = descriptionInputError,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(Modifier.size(15.dp))
        Row {
            ElevatedButton(
                onClick = {
                    validateInput()
                    val todos = createTodoObject()
                    createNewRecord(todos)
                    dismissRequest()
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Todo")
            }
        }
    }
}