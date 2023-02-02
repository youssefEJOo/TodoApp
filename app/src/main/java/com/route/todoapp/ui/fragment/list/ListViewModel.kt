package com.route.todoapp.ui.fragment.list




import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.todoapp.database.daos.TodoDao
import com.route.todoapp.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(val dao : TodoDao , val date : Long) : ViewModel() {

    val todoListLiveData = dao.getAllTodos()


//    fun getTodoByDate(){
//        clearTime.value = true
//       todoListLiveData.value =  dao.getTodosByDate(date).value
//    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            dao.deleteTodo(todo)
        }

    }

//    fun getTodos(context: Context){
//      todoListLiveData.value =  MyDataBase.getInstance(context).getTodoDao().getAllTodos().value
//    }


}