package com.route.todoapp.ui.fragment.list




import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.todoapp.database.MyDataBase
import com.route.todoapp.database.daos.TodoDao
import com.route.todoapp.model.Todo
import com.route.todoapp.repos.TodoRepository
import com.route.todoapp.repos.TodoRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel() : ViewModel() {

    lateinit var todoRepository: TodoRepository

    init {
        todoRepository = TodoRepositoryImpl(MyDataBase.getInstance())
    }

    var getTodoLiveData = todoRepository.getAllTodos()

    fun deleteTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            todoRepository.deleteTodo(todo)
        }

    }



}

