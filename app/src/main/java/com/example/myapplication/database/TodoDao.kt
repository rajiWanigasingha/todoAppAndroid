package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insertIntoTodo(todos: Todos)

    @Query("DELETE FROM todos WHERE id=:id")
    suspend fun deleteFormId(id: Int)

    @Query("SELECT * FROM todos WHERE state = 'TODO'")
    fun getAllTodos(): Flow<List<Todos>>

    @Query("SELECT * FROM todos WHERE state = 'PROGRESS'")
    fun getAllProgress(): Flow<List<Todos>>

    @Query("SELECT * FROM todos WHERE state = 'COMPLETE'")
    fun getAllCompleted(): Flow<List<Todos>>

    @Query("UPDATE todos SET state= 'PROGRESS' WHERE id=:id")
    suspend fun updateTodo(id: Int)

    @Query("UPDATE todos SET state= 'COMPLETE' WHERE id=:id")
    suspend fun updateProgress(id: Int)
}