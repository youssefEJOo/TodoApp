package com.route.todoapp.ui.fragment.update

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.todoapp.database.MyDataBase
import com.route.todoapp.database.daos.TodoDao
import com.route.todoapp.model.Todo
import com.route.todoapp.repos.TodoRepository
import com.route.todoapp.repos.TodoRepositoryImpl
import kotlinx.coroutines.launch

class UpdateTodoViewModel(val dao: TodoDao , val todo : Todo) : ViewModel() {

    lateinit var todoRepository: TodoRepository

    init {
        todoRepository = TodoRepositoryImpl(MyDataBase.getInstance())
    }



    fun updateDataBase( todo : Todo){
        viewModelScope.launch {
            try {
                todoRepository.updateTodo(todo)
            }catch (ex: Exception){
                throw ex
            }

        }

    }

}