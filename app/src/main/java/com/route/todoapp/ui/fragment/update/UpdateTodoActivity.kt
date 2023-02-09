package com.route.todoapp.ui.fragment.update
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.route.todoapp.R
import com.route.todoapp.database.MyDataBase
import com.route.todoapp.databinding.ActivityUpdateTodoBinding
import com.route.todoapp.model.Todo
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
        dataBinding.vm = viewModel
        viewModel.setData()
        dataBinding.editTodo.setOnClickListener {
            viewModel.updateData()
        }
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.finishActivityMutableList.observe(this){
            if (it != true){
                return@observe
            }else{
                finish()
            }
        }
    }


}