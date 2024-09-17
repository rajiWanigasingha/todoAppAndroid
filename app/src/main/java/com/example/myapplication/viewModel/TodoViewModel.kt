package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.MainApplication
import com.example.myapplication.database.Todos
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val db = MainApplication.todoDatabase.getTodoDao()

    val allTodo = db.getAllTodos()

    val allInProgress = db.getAllProgress()

    val allInCompleted = db.getAllCompleted()

    fun addTodo(todos: Todos) {
        viewModelScope.launch {
            db.insertIntoTodo(todos)
        }
    }

    fun deleteTodo(id: Int) {
        viewModelScope.launch {
            db.deleteFormId(id)
        }
    }

    fun updateTodoToProgress(id: Int) {
        viewModelScope.launch {
            db.updateTodo(id)
        }
    }

    fun updateProgressToComplete(id: Int){
        viewModelScope.launch {
            db.updateProgress(id)
        }
    }

}