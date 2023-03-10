package com.route.todoapp.ui.fragment.add

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todoapp.R
import com.route.todoapp.base.BaseBottomSheet
import com.route.todoapp.clearTime
import com.route.todoapp.database.MyDataBase
import com.route.todoapp.databinding.FragmentAddBottomSheetBinding
import com.route.todoapp.model.Todo
import kotlinx.coroutines.launch
import java.util.*

class AddBottomSheetFragment : BaseBottomSheet() {
    lateinit var dataBinding : FragmentAddBottomSheetBinding
    lateinit var viewModel : AddBottomSheetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddBottomSheetViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_add_bottom_sheet , container , false)
        return dataBinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.vm = viewModel
        dataBinding.date.text = "${currentDate.get(Calendar.YEAR)} / ${currentDate.get(Calendar.MONTH)+1} / ${currentDate.get(Calendar.DAY_OF_MONTH)}"
        dataBinding.date.setOnClickListener {
            showDatePicker()
        }
        dataBinding.addTodo.setOnClickListener {
            viewModel.clearTime.observe(viewLifecycleOwner){
                if (it){
                    currentDate.clearTime()
                }
            }
            viewModel.addTodo(context = requireContext() , currentDate = currentDate.timeInMillis )
        }
        subscribeToLiveData()

    }
    fun subscribeToLiveData(){
        viewModel.loadingDialogMutableData.observe(viewLifecycleOwner){
            if (it){
                showLoadingDialog()
            }else{
                hideLoading()
            }
        }
        viewModel.messageMutableData.observe(viewLifecycleOwner){message->
            showMessage(message, "Ok" , posAction = object :  DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.dismiss()
                }
            }, cancelable = false)
        }
        viewModel.dismissBottomSheet.observe(viewLifecycleOwner){
                if (it){
                    dismiss()
                }
        }
    }
    var currentDate = Calendar.getInstance()
    fun showDatePicker(){
        val datePicker = DatePickerDialog(
            requireContext() ,
            object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    currentDate.set( year , month , dayOfMonth)
                    dataBinding.date.text = "$year / ${month+1} / $dayOfMonth"

                }
            },currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)

        )
        datePicker.show()
    }








}