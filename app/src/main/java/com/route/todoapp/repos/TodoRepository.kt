package com.route.todoapp.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.route.todoapp.model.Todo

interface TodoRepository {
     fun getAllTodos():LiveData<MutableList<Todo>>
    suspend fun insertTodo(todo: Todo)
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}
