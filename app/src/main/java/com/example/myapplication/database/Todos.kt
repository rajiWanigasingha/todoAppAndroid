package com.example.myapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey


enum class StateOfTodo {
    TODO,
    PROGRESS,
    COMPLETE
}

@Entity
data class Todos(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val state: StateOfTodo
)
