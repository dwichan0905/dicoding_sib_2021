package com.dicoding.todoapp.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO 1 : Define a local database table using the schema in app/schema/tasks.json
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    @NonNull
    val id: Int = 0,

    @ColumnInfo(name = "title", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    val title: String,

    @ColumnInfo(name = "description", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    val description: String,

    @ColumnInfo(name = "dueDate", typeAffinity = ColumnInfo.INTEGER)
    @NonNull
    val dueDateMillis: Long,

    @ColumnInfo(name = "completed", typeAffinity = ColumnInfo.INTEGER)
    @NonNull
    val isCompleted: Boolean = false
)
