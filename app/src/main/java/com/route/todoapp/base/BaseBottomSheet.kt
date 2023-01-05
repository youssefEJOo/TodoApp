package com.route.todoapp.base

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.zip.Inflater

abstract class BaseBottomSheet: BottomSheetDialogFragment(){

    var progressDialog: ProgressDialog? =null;
    fun showLoadingDialog(){
        progressDialog = ProgressDialog(requireContext());
        progressDialog?.setMessage("Loading...")
        progressDialog?.show()
    }
    fun hideLoading(){
        progressDialog?.dismiss();
    }
    var alertDialog : android.app.AlertDialog? =null
    fun showMessage(message:String,
                    posActionTitle:String?=null,
                    posAction: DialogInterface.OnClickListener?=null,
                    negActionTitle:String?=null,
                    negAction: DialogInterface.OnClickListener?=null,
                    cancelable:Boolean = true){

        val messageDialogBuilder = android.app.AlertDialog.Builder(requireContext())
        messageDialogBuilder.setMessage(message)

        if(posActionTitle!=null){
            messageDialogBuilder.setPositiveButton(posActionTitle,
                posAction?: DialogInterface.OnClickListener { dialog, p1 -> dialog?.dismiss() })
        }
        if(negActionTitle!=null){
            messageDialogBuilder.setNegativeButton(negActionTitle,
                negAction?: DialogInterface.OnClickListener { dialog, p1 -> dialog?.dismiss() })
        }
        messageDialogBuilder.setCancelable(cancelable)

        alertDialog = messageDialogBuilder.show()
    }



}