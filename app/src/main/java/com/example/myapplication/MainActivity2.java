package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;

public class MainActivity2 extends AppCompatActivity {

    private ImageButton qingjia;
    private Intent intent1;
    private Bundle receive;
    private TextView ID;
    private String receiveID;
    private String identity;
    private ImageButton tongzhi;
    private Intent for_send;
    private Bundle send;
    private ImageButton myc_course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ID=(TextView)findViewById(R.id.textView1);
        qingjia=(ImageButton)findViewById(R.id.qingjia);
        tongzhi=(ImageButton)findViewById(R.id.tonghzi);
        myc_course=(ImageButton)findViewById(R.id.my_course);
        qingjia.setOnClickListener(new qingjiaclick());
        tongzhi.setOnClickListener(new tongzhiclick());
        myc_course.setOnClickListener(new my_courseclick());
        /*Bundle b1=this.getIntent().getExtras();
        String name=b1.getString("n");
        t1.setText(name.toString());*/

        receive=this.getIntent().getExtras();
        receiveID=receive.getString("ID");
        identity=receive.getString("identity");
        System.out.println(receiveID);
        System.out.println("++++++++++++++++++++++++++++");
        ID.setText(receiveID);


        for_send=new Intent();
        send=new Bundle();


    }

    private class qingjiaclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            intent1=new Intent();
            intent1.setClass(MainActivity2.this,leave.class);
            startActivity(intent1);
        }
    }

    private class tongzhiclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            send.putString("ID", receiveID);
            System.out.println("++++++++++++++++++++++++");
            for_send.putExtras(send);
            for_send.setClass(MainActivity2.this,school_notice.class);
            startActivity(for_send);
        }
    }

    private class my_courseclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            send.putString("ID", receiveID);
            send.putString("identity",identity);
            System.out.println("++++++++++++++++++++++++");
            for_send.putExtras(send);
            for_send.setClass(MainActivity2.this,student_course.class);
            startActivity(for_send);
        }
    }
}