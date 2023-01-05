package com.route.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.route.todoapp.database.DateConverter
import java.io.Serializable
import java.util.*

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val todoId: Int? = null,
    @ColumnInfo
    var todoTitle: String? = null,
    @ColumnInfo
    var todoDescription: String? = null,
    @ColumnInfo
    var todoDate: Long? = null,
    @ColumnInfo
    var isDone: Boolean? = null
): Serializable
