package com.route.todoapp.database.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.route.todoapp.model.Todo
import java.util.*

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("select * from Todo")
     fun getAllTodos():LiveData<List<Todo>>

//    @Query("select * from Todo where todoDate = :date ")
//    suspend fun getTodosByDate(date : Long): MutableLiveData<MutableList<Todo>>
}