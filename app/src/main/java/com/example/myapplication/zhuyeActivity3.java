package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class zhuyeActivity3 extends AppCompatActivity {
private Button button;
private TextView txt;
private Button for_students;
private Button my;
private Bundle bundle1;
private Button school_activities1;
private Button school_news;
private Bundle for_send;
private Intent send;
private String ID;
private String identity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuye);
        Button b1=(Button)findViewById(R.id.button);
        my=(Button)findViewById(R.id.my);
        school_activities1=(Button)findViewById(R.id.school_activities) ;
        school_news=(Button)findViewById(R.id.school_news);
        for_students=(Button)findViewById(R.id.for_students);

        for_send=new Bundle();
        send=new Intent();

        b1.setOnClickListener(new b1click());
        for_students.setOnClickListener(new studentsclick());
        my.setOnClickListener(new myclick());
        school_activities1.setOnClickListener(new school_activities_click());
        school_news.setOnClickListener(new school_newsclick());


        bundle1=this.getIntent().getExtras();
        txt=(TextView)findViewById(R.id.textView1);
        ID=bundle1.getString("n");
        identity=bundle1.getString("identity");
        txt.setText(ID.toString());
        System.out.println(ID);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    private class b1click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            send.setClass(zhuyeActivity3.this,MainActivity.class);
            startActivity(send);
        }
    }
    class studentsclick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent in2=new Intent();
            send.setClass(zhuyeActivity3.this,for_register.class);
            startActivity(send);
        }
    }

    private class myclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for_send.putString("ID",ID);
            String identity=bundle1.getString("identity");
            System.out.println("+++++++++++++++++++++++++++++++");
            System.out.println(identity);
            if (identity.equals("student"))
            {
                send.setClass(zhuyeActivity3.this,MainActivity2.class);
            }
            else if (identity.equals("teacher"))
            {
                send.setClass(zhuyeActivity3.this,teacher.class);
            }
            else if (identity.equals("parent"))
            {
                send.setClass(zhuyeActivity3.this,parent.class);
            }
            send.putExtras(for_send);
            startActivity(send);
        }
    }

    private class school_activities_click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for_send.putString("ID",ID);
            for_send.putString("identity",identity);
            send.putExtras(for_send);
            send.setClass(zhuyeActivity3.this,school_activities.class);
            startActivity(send);
        }
    }

    private class school_newsclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for_send.putString("ID",ID);
            for_send.putString("identity",identity);
            send.putExtras(for_send);
            send.setClass(zhuyeActivity3.this,school_news.class);
            startActivity(send);

        }
    }
}