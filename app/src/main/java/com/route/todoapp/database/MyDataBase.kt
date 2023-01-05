package com.route.todoapp.database

import android.content.Context
import androidx.room.*
import com.route.todoapp.database.daos.TodoDao
import com.route.todoapp.model.Todo
import kotlin.coroutines.coroutineContext

@Database(entities = [Todo::class] , version = 1)
//@TypeConverters(DateConverter::class)
abstract class MyDataBase : RoomDatabase() {

    abstract fun getTodoDao(): TodoDao

    companion object{
        private var myDataBase : MyDataBase? = null
        fun getInstance(context: Context):MyDataBase{
            if (myDataBase == null){
                 myDataBase = Room.databaseBuilder(
                    context,
                    MyDataBase::class.java, "todos_database"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

            }
            return myDataBase!!
        }
    }
}