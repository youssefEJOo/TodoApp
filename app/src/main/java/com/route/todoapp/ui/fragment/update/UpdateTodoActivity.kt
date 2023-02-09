package com.route.todoapp.ui.fragment.update
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import com.route.todoapp.R
import com.route.todoapp.database.MyDataBase
import com.route.todoapp.databinding.ActivityUpdateTodoBinding
import com.route.todoapp.model.Todo
import kotlinx.coroutines.launch
import java.util.*


class UpdateTodoActivity : AppCompatActivity() {
    lateinit var viewModel: UpdateTodoViewModel
    lateinit var dataBinding : ActivityUpdateTodoBinding
    lateinit var todo : Todo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_todo)
        todo = intent.getSerializableExtra("todo") as Todo
        val dao = MyDataBase.getInstance().getTodoDao()
        viewModel = UpdateTodoViewModel(dao , todo)
        dataBinding.vm = this
        setData()

    }
    fun setData(){
        dataBinding.titleTextInputLayout.editText?.setText(todo.todoTitle)
        dataBinding.descriptionTextInputLayout.editText?.setText(todo.todoDescription)
        dataBinding.date.text = todo.todoDate.toString()
    }

    fun updateData(){
            try {
                if(valid()){
                    todo.todoTitle = dataBinding.titleTextInputLayout.editText?.text.toString()
                    todo.todoDescription = dataBinding.descriptionTextInputLayout.editText?.text.toString()
                    todo.todoDate = dataBinding.date.drawingTime
                    viewModel.updateDataBase(todo)
                    finish()
                }
            }catch (e:Exception){
                Log.e("updateData" , e.message.toString() )
            }



    }

    fun valid():Boolean{
        var valid  = true
        if (dataBinding.titleTextInputLayout.editText?.text.isNullOrBlank()){
            valid = false
            dataBinding.titleTextInputLayout.error = "please Enter the title"
        }else{
            dataBinding.titleTextInputLayout.error = null
        }
        if (dataBinding.descriptionTextInputLayout.editText?.text.isNullOrBlank()){
            valid = false
            dataBinding.descriptionTextInputLayout.error = "please Enter the Description"
        }else{
            dataBinding.descriptionTextInputLayout.error = null
        }
        return valid
    }

}