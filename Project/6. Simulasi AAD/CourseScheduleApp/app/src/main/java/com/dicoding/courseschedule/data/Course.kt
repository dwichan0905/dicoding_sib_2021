package com.dicoding.courseschedule.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO 1 : Define a local database table using the schema in app/schema/course.json
@Entity(tableName = DataCourseName.TABLE_NAME)
data class Course(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DataCourseName.COL_ID, typeAffinity = ColumnInfo.INTEGER)
    @NonNull
    val id: Int = 0,

    @ColumnInfo(name = DataCourseName.COL_COURSE_NAME, typeAffinity = ColumnInfo.TEXT)
    @NonNull
    val courseName: String,

    @ColumnInfo(name = DataCourseName.COL_DAY, typeAffinity = ColumnInfo.INTEGER)
    @NonNull
    val day: Int,

    @ColumnInfo(name = DataCourseName.COL_START_TIME, typeAffinity = ColumnInfo.TEXT)
    @NonNull
    val startTime: String,

    @ColumnInfo(name = DataCourseName.COL_END_TIME, typeAffinity = ColumnInfo.TEXT)
    @NonNull
    val endTime: String,

    @ColumnInfo(name = DataCourseName.COL_LECTURER, typeAffinity = ColumnInfo.TEXT)
    @NonNull
    val lecturer: String,

    @ColumnInfo(name = DataCourseName.COL_NOTE, typeAffinity = ColumnInfo.TEXT)
    @NonNull
    val note: String
)
