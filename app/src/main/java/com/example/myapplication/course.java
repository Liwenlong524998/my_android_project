package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class course extends AppCompatActivity {
    private Bundle receive;
    private Intent send;
    private Bundle for_send;
    private String ID;
    private String identity;
    private dataBase database;
    private SQLiteDatabase db;
    private Button add_course;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private TextView course_name;
    private Button delete;
    private TextView course_number;
    private Button add_notice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        send=new Intent();
        for_send=new Bundle();

        database=new dataBase(this,"leave",null,4);
        db=database.getWritableDatabase();

        receive=this.getIntent().getExtras();
        ID=receive.getString("ID");
        identity=receive.getString("identity");

        linearLayout1=(LinearLayout)findViewById(R.id.linearLayout1);
        add_course=(Button)findViewById(R.id.add_course);
        add_notice=(Button)findViewById(R.id.add_notice);
        add_notice.setOnClickListener(new add_noticeclick());
        add_course.setOnClickListener(new add_courseclick());

        String select="select * from course where teacher_number='"+ID+"'";
        Cursor cursor=db.rawQuery(select,null);
        int i=0;
        while(cursor.moveToNext())
        {
            linearLayout=new LinearLayout(this);
            delete=new Button(this);
            course_number=new TextView(this);
            course_name=new TextView(this);
            delete.setText("删除");
            course_number.setText("   "+cursor.getString(cursor.getColumnIndex("course_number")));
            course_name.setText("   "+cursor.getString(cursor.getColumnIndex("course_name")));
            linearLayout.addView(delete);
            linearLayout.addView(course_number);
            linearLayout.addView(course_name);
            linearLayout1.addView(linearLayout);
        }

    }

    private class add_courseclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for_send.putString("ID",ID);
            for_send.putString("identity","teacher");
            send.putExtras(for_send);
            send.setClass(course.this,add_course.class);
            startActivity(send);

        }
    }

    private class add_noticeclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for_send.putString("ID",ID);
            for_send.putString("identity","teacher");
            send.putExtras(for_send);
            send.setClass(course.this,add_notice.class);
            startActivity(send);
        }
    }
}