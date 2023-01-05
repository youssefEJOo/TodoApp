package com.route.todoapp.database

import androidx.room.TypeConverter
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromDate(date: Date):Long{
        return date.time
    }
    @TypeConverter
    fun fromLong(date: Long):Date{
        return Date(date)
    }
}