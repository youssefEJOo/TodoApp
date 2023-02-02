package com.route.todoapp.ui.fragment.update

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.todoapp.database.daos.TodoDao
import com.route.todoapp.model.Todo
import kotlinx.coroutines.launch

class UpdateTodoViewModel(val dao: TodoDao , val todo : Todo) : ViewModel() {

    val titleMutableLiveData = ObservableField<String?>()
    val titleErrorMutableLiveData = ObservableField<String?>()
    val descriptionMutableLiveData = ObservableField<String?>()
    val descriptionErrorMutableLiveData = ObservableField<String?>()
    val dateMutableLiveData = ObservableField<String?>()
    val finishActivityMutableList = MutableLiveData<Boolean>()


    fun setData(){
        titleMutableLiveData.set(todo.todoTitle)
        descriptionMutableLiveData.set(todo.todoDescription)
        dateMutableLiveData.set(todo.todoDate.toString())
    }

    fun updateData(){
        viewModelScope.launch {
            try {
                if(valid()){
                        todo.todoTitle = titleMutableLiveData.get()
                        todo.todoDescription = descriptionMutableLiveData.get()
                        todo.todoDate = dateMutableLiveData.get()?.toLong()
                        dao.updateTodo(todo)
                        finishActivityMutableList.value = true
                }else{
                        finishActivityMutableList.value = false
                }
            }catch (e:Exception){
                        finishActivityMutableList.value = false
                Log.e("updateData" , e.message.toString() )
            }

        }

    }

    fun valid():Boolean{
        var valid  = true
        if (titleMutableLiveData.get().isNullOrBlank()){
            valid = false
            titleErrorMutableLiveData.set("please Enter the title Valid")
        }else{
            titleErrorMutableLiveData.set(null)
        }
        if (descriptionMutableLiveData.get().isNullOrBlank()){
            valid = false
            descriptionErrorMutableLiveData.set("please Enter the Description Valid")
        }else{
            descriptionErrorMutableLiveData.set(null)
        }
        return valid
    }
}