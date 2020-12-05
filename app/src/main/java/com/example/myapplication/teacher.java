package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class teacher extends AppCompatActivity {
private Button home;
private TextView ID;
private Bundle b_receive;
private Bundle for_send;
private Intent send;
private String receive_ID;
private ImageButton leave_m;
private ImageButton wodebanji;
private ImageButton huodong;
private ImageButton xinwen;
private ImageButton my_course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        ID=(TextView)findViewById(R.id.textView4);
        home=(Button)findViewById(R.id.home);
        leave_m=(ImageButton)findViewById(R.id.leave_message);
        wodebanji=(ImageButton)findViewById(R.id.wodebanji);
        huodong=(ImageButton)findViewById(R.id.huodong);
        xinwen=(ImageButton)findViewById(R.id.xinwen);
        my_course=(ImageButton)findViewById(R.id.my_course) ;

        home.setOnClickListener(new homeclick());
        leave_m.setOnClickListener(new leave_messageclick());
        wodebanji.setOnClickListener(new wodebaji_click());
        huodong.setOnClickListener(new huodongclick());
        xinwen.setOnClickListener(new xinwenclick());
        my_course.setOnClickListener(new my_courseclick());

        for_send=new Bundle();
        send=new Intent();


        b_receive=this.getIntent().getExtras();
        receive_ID=b_receive.getString("ID");
        ID.setText(receive_ID);
        System.out.println(receive_ID);

    }

    private class homeclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }

    private class leave_messageclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for_send.putString("ID",receive_ID);
            send.putExtras(for_send);
            send.setClass(teacher.this,leave_message.class);
            startActivity(send);
        }
    }

    private class wodebaji_click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            send.setClass(teacher.this,my_class.class);
            startActivity(send);


        }
    }

    private class huodongclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for_send.putString("ID",receive_ID);
            for_send.putString("identity","teacher");
            send.putExtras(for_send);
            send.setClass(teacher.this,school_activities.class);
            startActivity(send);
        }
    }

    private class xinwenclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for_send.putString("ID",receive_ID);
            for_send.putString("identity","teacher");
            send.putExtras(for_send);
            send.setClass(teacher.this,school_news.class);
            startActivity(send);
        }
    }

    private class my_courseclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for_send.putString("ID",receive_ID);
            for_send.putString("identity","teacher");
            send.putExtras(for_send);
            send.setClass(teacher.this,course.class);
            startActivity(send);
        }
    }
}