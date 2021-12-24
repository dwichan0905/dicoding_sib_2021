package com.dicoding.habitapp.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//TODO 1 : Define a local database table using the schema in app/schema/habits.json
@Parcelize
@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    @NonNull
    val id: Int = 0,

    @ColumnInfo(name = "title", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    val title: String,

    @ColumnInfo(name = "minutesFocus", typeAffinity = ColumnInfo.INTEGER)
    @NonNull
    val minutesFocus: Long,

    @ColumnInfo(name = "startTime", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    val startTime: String,

    @ColumnInfo(name = "priorityLevel", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    val priorityLevel: String
): Parcelable
