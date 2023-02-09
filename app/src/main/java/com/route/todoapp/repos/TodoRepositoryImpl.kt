package com.route.todoapp.repos

import androidx.lifecycle.LiveData
import com.route.todoapp.database.MyDataBase
import com.route.todoapp.model.Todo

class TodoRepositoryImpl(private val myDataBase: MyDataBase) : TodoRepository {
    override  fun getAllTodos():LiveData<MutableList<Todo>>{
       val result =  myDataBase.getTodoDao().getAllTodos()
        return result
    }

    override suspend fun insertTodo(todo: Todo) {
        myDataBase.getTodoDao().insertTodo(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        myDataBase.getTodoDao().updateTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        myDataBase.getTodoDao().deleteTodo(todo)
    }
}