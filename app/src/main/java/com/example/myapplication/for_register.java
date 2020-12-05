package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.Instant;

public class for_register extends AppCompatActivity {
private Button for_student;
private Button for_teacher;
private Button for_parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_register);
        for_student=(Button)findViewById(R.id.for_allStudent);
        for_parent=(Button)findViewById(R.id.for_parent);
        for_teacher=(Button)findViewById(R.id.for_teacher);
        for_student.setOnClickListener(new studentclick());
        for_parent.setOnClickListener(new parentclick());
        for_teacher.setOnClickListener(new teacherclick());
    }
    class studentclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent1=new Intent();
            intent1.setClass(for_register.this,register.class);
            startActivity(intent1);
        }
    }
    class parentclick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent2=new Intent();
            intent2.setClass(for_register.this,parent_register.class);
            startActivity(intent2);
        }
    }
    class teacherclick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent3=new Intent();
            intent3.setClass(for_register.this,teacher_register.class);
            startActivity(intent3);
        }
    }
}