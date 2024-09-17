package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Todos::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {

    companion object {
        const val NAME = "todo_db"
    }

    abstract fun getTodoDao(): TodoDao

}