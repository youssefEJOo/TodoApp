package com.route.todoapp.ui.fragment.list


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todoapp.R
import com.route.todoapp.adapters.TodosAdapter
import com.route.todoapp.clearTime
import com.route.todoapp.database.MyDataBase
import com.route.todoapp.databinding.FragmentListBinding
import com.route.todoapp.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class ListFragment : Fragment() {
    lateinit var dataBinding : FragmentListBinding
    lateinit var viewModel: ListViewModel
    var todoAdapter: TodosAdapter = TodosAdapter(mutableListOf())
    var todoList : MutableList<Todo> = mutableListOf()
    var selectedDate = CalendarDay.today()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_list , container , false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao =  MyDataBase.getInstance(requireContext()).getTodoDao()
        viewModel = ListViewModel(dao)
//        viewModel.getTodo()
            viewModel.todoListLiveData.observe(viewLifecycleOwner){
                it?.let {
                    todoAdapter.setData(it)
                }


            }
//        getTodos()
//        refreshTodos()
        dataBinding.recyclerViewNote.adapter = todoAdapter
        dataBinding.calendarView.selectedDate = selectedDate
        dataBinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDate = date
//            refreshTodos()

        }


    }

    var calendarDate = Calendar.getInstance()
//    fun getTodos(){
//        calendarDate.set(selectedDate.year , selectedDate.month-1 , selectedDate.day)
//        calendarDate.clearTime()
////        viewModel.getTodo(requireContext())
//        viewModel.todoListLiveData.observe(viewLifecycleOwner){todo->
//            todoList = todo
//        }
//
//    }
//    fun refreshTodos(){
//        todoAdapter.items = todoList
//        todoAdapter.notifyDataSetChanged()
//    }

}