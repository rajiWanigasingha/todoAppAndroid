package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(changeThemes: () -> Unit, themeState: Boolean) {
    TopAppBar(
        title = {
            Row {
                Column {
                    Icon(Icons.Filled.Edit, contentDescription = "Logo")
                }
                Spacer(Modifier.size(4.dp))
                Column {
                    Text("ToDoApp")
                }
            }
        },
        actions = {
            IconButton(onClick = {
                changeThemes()
            }) {
                if (themeState) {
                    Icon(
                        painter = painterResource(R.drawable.sun),
                        contentDescription = "Theme Changer",
                        Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                } else {

                    Icon(
                        painter = painterResource(R.drawable.moon),
                        contentDescription = "Theme Changer",
                        Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}