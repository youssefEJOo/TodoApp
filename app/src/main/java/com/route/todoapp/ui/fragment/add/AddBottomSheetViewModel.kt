package com.route.todoapp.ui.fragment.add

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.todoapp.clearTime
import com.route.todoapp.database.MyDataBase
import com.route.todoapp.model.Todo
import kotlinx.coroutines.launch
import java.sql.Time
import java.util.*

class AddBottomSheetViewModel : ViewModel() {

    val titleMutableLiveData = ObservableField<String?>()
    val descriptionMutableLiveData = ObservableField<String?>()
    val dateMutableLiveData = MutableLiveData<Long>()
    val titleErrorMutableLiveData = ObservableField<String?>()
    val descriptionErrorMutableLiveData = ObservableField<String?>()
    val dateErrorMutableLiveData = MutableLiveData<Long>()
    val loadingDialogMutableData = MutableLiveData<Boolean>()
    val messageMutableData = MutableLiveData<String>()
    val dismissBottomSheet = MutableLiveData<Boolean>()
    //val clearTime = MutableLiveData<Boolean>()

    fun addTodo(currentDate : Long , context: Context){
        viewModelScope.launch {
            try {
                if (validate()){
                    //clearTime.value = true
                    var todo = Todo(
                        todoTitle = titleMutableLiveData.get(),
                        todoDescription = descriptionMutableLiveData.get(),
                        todoDate = currentDate,
                        isDone = false
                    )
                    MyDataBase.getInstance(context).getTodoDao().insertTodo(todo)
                    Log.e("add Todo" , "${todo.todoTitle.toString()}")
                    loadingDialogMutableData.value = false
                    messageMutableData.value = "Task Added Successfully"
                    dismissBottomSheet.value = true
//                showMessage("Task Added Successfully" , "Ok" , posAction = { dialog, which ->
//                    dialog.dismiss()
//                    dismiss()
//                }, cancelable = false)

//                            Toast.makeText(requireContext() , "The Todo Is Added" , Toast.LENGTH_LONG).show()
                }
            }catch (e : Exception){
                dismissBottomSheet.value = false
                loadingDialogMutableData.value = true
                Log.e("addNote" , "errorMessage${e.message}")
            }
        }


    }

    fun validate():Boolean{
        var valid = true
        if (titleMutableLiveData.get().isNullOrBlank()){
            titleErrorMutableLiveData.set("Please Enter Title")
            valid = false
        }else{
            titleErrorMutableLiveData.set(null)
        }
        if (descriptionMutableLiveData.get().isNullOrBlank()){
           descriptionErrorMutableLiveData.set("Please Enter Description")
            valid = false
        }else{
            descriptionErrorMutableLiveData.set(null)
        }
//        if (dataBinding.date.text.toString().isNullOrBlank()){
//            dataBinding.date.error = "Please Enter Date"
//            valid = false
//        }else{
//            dataBinding.date.error = null
//        }

        return valid
    }
}