package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.IDN;

public class student_course extends AppCompatActivity {
    private dataBase database;
    private SQLiteDatabase db;
    private Bundle receive;
    private Intent send;
    private Bundle for_send;
    private String ID;
    private String identity;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private TextView course_name;
    private TextView teacher_name;
    private Button delete;
    private Button add_course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course);
        linearLayout1=(LinearLayout)findViewById(R.id.linearLayout1);
        add_course=(Button)findViewById(R.id.add_course);
        add_course.setOnClickListener(new add_courseclick());

        database = new dataBase(this, "leave", null, 4);
        db=database.getWritableDatabase();
        for_send=new Bundle();
        send=new Intent();

        receive=this.getIntent().getExtras();
        ID=receive.getString("ID");
        identity=receive.getString("identity");

        String select="select * from StudentTeacher where stu_number='"+ID+"'";
        Log.i("Ex04","selectDB="+select);
        Cursor cursor=db.rawQuery(select,null);
        while(cursor.moveToNext())
        {
            linearLayout=new LinearLayout(this);
            course_name=new TextView(this);
            teacher_name=new TextView(this);
            delete=new Button(this);
            course_name.setText("     "+cursor.getString(cursor.getColumnIndex("course_name")));
            teacher_name.setText("     "+cursor.getString(cursor.getColumnIndex("teacher_name")));
            linearLayout.addView(delete);
            linearLayout.addView(course_name);
            linearLayout.addView(teacher_name);
            linearLayout1.addView(linearLayout);
        }
    }

    private class add_courseclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for_send.putString("ID", ID);
            for_send.putString("identity",identity);
            System.out.println("++++++++++++++++++++++++");
            send.putExtras(for_send);
            send.setClass(student_course.this,select_course.class);
            startActivity(send);
        }
    }
}