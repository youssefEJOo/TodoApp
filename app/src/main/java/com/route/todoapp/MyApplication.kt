package com.route.todoapp

import android.app.Application
import com.route.todoapp.database.MyDataBase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDataBase.init(this)
    }
}