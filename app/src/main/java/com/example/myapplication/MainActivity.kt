package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.myapplication.database.TodoDatabase
import com.example.myapplication.ui.components.BottomBar
import com.example.myapplication.ui.components.Form
import com.example.myapplication.ui.components.TopBar
import com.example.myapplication.ui.pages.CompletePage
import com.example.myapplication.ui.pages.ProgressPage
import com.example.myapplication.ui.pages.TodoPage
import com.example.myapplication.ui.theme.AppTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var changeTheme by remember { mutableStateOf(true) }

            AppTheme(
                darkTheme = changeTheme
            ) {
                App(
                    changeTheTheme = {
                        changeTheme = !changeTheme
                    },
                    changeTheme
                )
            }
        }
    }
}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    changeTheTheme: () -> Unit,
    themeState: Boolean
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val openDrawer = remember { mutableStateOf(false) }

    val navController = rememberNavController()

    Scaffold(

        topBar = {
            TopBar(changeTheTheme, themeState)
        },

        bottomBar = {
            BottomBar(openDrawer ,navController)
        }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            if (openDrawer.value) {
                ModalBottomSheet(
                    onDismissRequest = {
                        openDrawer.value = false
                    }
                ) {
                    Form(dismissRequest = {
                        openDrawer.value = false
                    })
                }
            }

            NavHost(
                navController = navController,
                startDestination = ScreenTodo
            ){
                composable<ScreenTodo>{
                    TodoPage().Content()
                }
                composable<ScreenProgress> {
                    ProgressPage().Content()
                }
                composable<ScreenComplete> {
                    CompletePage().Content()
                }
            }

        }
    }
}


@Serializable
object ScreenTodo

@Serializable
object ScreenProgress

@Serializable
object ScreenComplete