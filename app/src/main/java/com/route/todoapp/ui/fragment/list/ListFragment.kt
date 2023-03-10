package com.route.todoapp.ui.fragment.list


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todoapp.R
import com.route.todoapp.adapters.TodosAdapter
import com.route.todoapp.clearTime
import com.route.todoapp.database.MyDataBase
import com.route.todoapp.databinding.FragmentListBinding
import com.route.todoapp.model.Todo
import com.route.todoapp.ui.fragment.update.UpdateTodoActivity
import java.util.*


class ListFragment : Fragment() {
    lateinit var dataBinding : FragmentListBinding
    lateinit var viewModel: ListViewModel
    var todoAdapter: TodosAdapter = TodosAdapter(mutableListOf())
    var selectedDate = CalendarDay.today()
    var calendarDate = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_list , container , false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarDate.set(selectedDate.year , selectedDate.month-1 , selectedDate.day)
        calendarDate.clearTime()

        viewModel.getTodoLiveData.observe(viewLifecycleOwner){
            it?.let {
                todoAdapter.setData(it)
                Log.e("OnViewCreated" , todoAdapter.items.toString())
            }

        }

        dataBinding.recyclerViewNote.adapter = todoAdapter
        dataBinding.calendarView.selectedDate = selectedDate
        dataBinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDate = date

        }
        todoAdapter.onItemClickedToDelete = object : TodosAdapter.OnItemClicked{
            override fun onItemClickedToDelete(position : Int , todo : Todo) {
                todoAdapter.items.removeAt(position)
                viewModel.deleteTodo(todo)
            }
        }
        todoAdapter.onItemClickedToUpdate = object : TodosAdapter.OnItemClickedToBeUpdated{
            override fun onItemClickedToUpdate(position: Int, todo: Todo) {
                val intent  = Intent(requireContext() , UpdateTodoActivity::class.java )
                intent.putExtra("todo" , todo)
                startActivity(intent)
            }

        }

    }

}