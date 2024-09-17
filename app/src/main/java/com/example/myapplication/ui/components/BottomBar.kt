package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ScreenComplete
import com.example.myapplication.ScreenProgress
import com.example.myapplication.ScreenTodo

@Composable
fun BottomBar(openDrawer: MutableState<Boolean>, navController: NavController) {

    var activeBtn by remember { mutableIntStateOf(1) }

    @Composable
    fun activeBtn(index: Int): Color {
        return if (index == activeBtn) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            Color.Unspecified
        }
    }

    BottomAppBar(
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(ScreenTodo)
                    activeBtn = 1
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = activeBtn(1)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.todo),
                    contentDescription = "todo",
                    modifier = Modifier.size(25.dp),
                )
            }
            Spacer(Modifier.size(8.dp))
            IconButton(
                onClick = {
                    navController.navigate(ScreenProgress)
                    activeBtn = 2
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = activeBtn(2)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.progress),
                    contentDescription = "progress",
                    modifier = Modifier.size(25.dp),
                )
            }
            Spacer(Modifier.size(8.dp))
            IconButton(
                onClick = {
                    navController.navigate(ScreenComplete)
                    activeBtn = 3
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = activeBtn(3)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.complete),
                    contentDescription = "complete",
                    modifier = Modifier.size(25.dp),
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                openDrawer.value = !openDrawer.value
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Create", Modifier.size(30.dp))
            }
        },
    )
}
