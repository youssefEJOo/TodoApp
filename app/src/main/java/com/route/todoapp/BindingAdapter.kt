package com.route.todoapp

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:error")
    fun setError(textInput : TextInputLayout, errorMessage : String?){
        textInput.error = errorMessage
    }


}