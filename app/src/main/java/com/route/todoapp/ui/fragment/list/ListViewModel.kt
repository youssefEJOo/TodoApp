package com.route.todoapp.ui.fragment.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.todoapp.database.MyDataBase
import com.route.todoapp.database.daos.TodoDao
import com.route.todoapp.model.Todo
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ListViewModel(val dao : TodoDao) : ViewModel() {

    var todoListLiveData = dao.getAllTodos()


//    fun getTodo(){
//        viewModelScope.launch {
//            async {
//                val todo = dao.getAllTodos()
//                todoListLiveData = dao.getAllTodos()
//            }
//
//        }
//
//    }
}