package com.route.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.todoapp.R
import com.route.todoapp.databinding.ItemTodoBinding
import com.route.todoapp.model.Todo

class TodosAdapter(var items : MutableList<Todo>) : RecyclerView.Adapter<TodosAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding : ItemTodoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context)
            , R.layout.item_todo , parent , false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.binding.model = item
        if (onItemClickedToDelete != null){
            holder.binding.rightView.setOnClickListener {
                onItemClickedToDelete?.onItemClickedToDelete(position , item)
            }
        }
        if (onItemClickedToUpdate != null){
            holder.binding.cardView.setOnClickListener {
                onItemClickedToUpdate?.onItemClickedToUpdate(position , item)
            }
        }

    }

    var onItemClickedToDelete : OnItemClicked? = null
    interface OnItemClicked{
        fun onItemClickedToDelete(position: Int , todo: Todo)
    }
    fun setData(todo: MutableList<Todo>){
        items = todo
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(val binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){

    }
    var onItemClickedToUpdate : OnItemClickedToBeUpdated? = null
    interface OnItemClickedToBeUpdated{
        fun onItemClickedToUpdate(position: Int , todo: Todo)
    }
}