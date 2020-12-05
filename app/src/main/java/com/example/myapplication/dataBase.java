package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class dataBase extends SQLiteOpenHelper {
    public dataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Student_Sql="create table students(stu_number text primary key," +
                "pass_word text not null," +
                "name text not null," +
                "sex text not null," +
                "class_room text not null," +
                "phone_number text not null," +
                "nackname text," +
                "picture text)";
        Log.i("Ex04","createDB="+Student_Sql);
        db.execSQL(Student_Sql);

        String teacher_Sql="create table teachers(teacher_number text primary key," +
                "pass_word text not null," +
                "name text not null," +
                "sex text not null," +
                "phone_number text not null," +
                "nackname text," +
                "class_name text)";
        Log.i("Ex04","createDB="+teacher_Sql);
        db.execSQL(teacher_Sql);

        String course_sql="create table course(course_number text primary key," +
                "course_name text not null," +
                "teacher_number text not null," +
                "teacher_name text not null," +
                "department text not null)";
        Log.i("Ex04","createDB="+course_sql);
        db.execSQL(course_sql);

        String StudentCourse_Sql="create table StudentTeacher(stu_number text," +
                "course_number text," +
                "teacher_number text," +
                "student_name text not null," +
                "teacher_name text not null," +
                "course_name text not null," +
                "primary key(stu_number,course_number,teacher_number))";
        Log.i("Ex04","createDB="+StudentCourse_Sql);
        db.execSQL(StudentCourse_Sql);

        String CourseData_Sql="create table CourseData(course_number text," +
                "teacher_number text," +
                "course_name text not null," +
                "data_address text not null," +
                "primary key(course_number,teacher_number))";
        Log.i("Ex04","createDB="+CourseData_Sql);
        db.execSQL(CourseData_Sql);

        String SchoolActivities_Sql="create table SchoolActivities(activities_name text not null," +
                "activities_address text not null," +
                "teacher_number text not null)";
        Log.i("Ex04","createDB="+SchoolActivities_Sql);
        db.execSQL(SchoolActivities_Sql);

        String SchoolNews_Sql="create table SchoolNews(news_name text not null," +
                "news_address text not null," +
                "teacher_number text not null) ";
        Log.i("Ex04","createDB="+SchoolNews_Sql);
        db.execSQL(SchoolNews_Sql);

        String parent_Sql="create table Parents(account text primary key," +
                "password text not null," +
                "name text not null," +
                "student_number text not null," +
                "phone text not null)";
        Log.i("Ex04","createDB="+parent_Sql);
        db.execSQL(parent_Sql);

        String notice_Sql="create table notice(teacher_number text not null," +
                "course_number text," +
                "class_name text," +
                "notice_number text not null primary key," +
                "notice_context text not null)";
        Log.i("Ex04","createDB="+notice_Sql);
        db.execSQL(notice_Sql);

        String leave_Sql="create table leave(student_number text primary key," +
                "student_name text not null," +
                "leave_reason text not null," +
                "class text not null," +
                "teacher_advice text," +
                "parent_advice text)";
        Log.i("Ex04","createDB="+leave_Sql);
        db.execSQL(leave_Sql);

        String teacher_class="create table teacherClass(teacher_number text primary key,classID text not null)";
        Log.i("Ex04","createDB="+teacher_class);
        db.execSQL(teacher_class);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
