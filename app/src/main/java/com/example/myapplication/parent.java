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
import android.widget.ScrollView;
import android.widget.TextView;

public class parent extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView warn;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private Button button;
    private TextView txt;
    private dataBase database;
    private SQLiteDatabase db;
    private String ID;
    private Bundle receive;
    private Intent send;
    private Bundle for_send;
    private TextView check;
    private String student_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        receive=this.getIntent().getExtras();
        ID=receive.getString("ID");
        System.out.println(ID);

        linearLayout=(LinearLayout)findViewById(R.id.linearLayout1);
        scrollView=(ScrollView)findViewById(R.id.scrollView);

        database=new dataBase(this,"leave",null,4);
        db=database.getWritableDatabase();

        String select_id="select * from Parents where account='"+ID+"'";
        Log.i("Ex04","selectDB"+select_id);
        System.out.println("+++++++++++++++++++++++++++");
        Log.i("Ex04","selectDB"+select_id);
        final Cursor cursor1=db.rawQuery(select_id,null);
        StringBuilder s_id= new StringBuilder();
        student_number="";
        String isagree="";
        send=new Intent();
        for_send=new Bundle();
        int i=0;

        while (cursor1.moveToNext())
        {
            s_id.append(cursor1.getString(cursor1.getColumnIndex("student_number")));
            System.out.println(s_id);
            i++;
            student_number=cursor1.getString(cursor1.getColumnIndex("student_number"));
            String select ="select * from leave where student_number='"+student_number+"'";
            Log.i("Ex04","selectDB"+select);
            Cursor cursor=db.rawQuery(select,null);
            while (cursor.moveToNext())
            {
                isagree=cursor.getString(cursor.getColumnIndex("parent_advice"));
            }
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println(isagree);

            System.out.println(student_number);

            System.out.println("!!!!!!!");
            if (isagree==null)
            {
                isagree="1";
            }
            check=new TextView(this);
            linearLayout1=new LinearLayout(this);
            txt=new TextView(this);
            button=new Button(this);
            if (!isagree.equals("yes"))
            {
                check.setText("    未审核");
            }
            else
            {
                check.setText("    已审核");
            }
            txt.setText("您的孩子有新的动态！");
            button.setText("查看详情");
            System.out.println("+++++++");
            final String finalStudent_number = student_number;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for_send.putString("student_number", finalStudent_number.toString());
                    for_send.putString("parentORteacher","parent");
                    System.out.println("++++++++++++++++++++++++");
                    System.out.println(finalStudent_number);
                    send.putExtras(for_send);
                    send.setClass(parent.this,for_leaveMessage.class);
                    startActivity(send);
                }
            });
            linearLayout1.addView(button);
            linearLayout1.addView(txt);
            linearLayout1.addView(check);
            linearLayout.addView(linearLayout1);
        }
        System.out.println(s_id);
        System.out.println(i);


        String select_class="select * from students where stu_number='"+student_number+"'";
        String select_course="select * from StudentTeacher where stu_number='"+student_number+"'";
        Log.i("Ex04","selectDB="+select_class);
        Cursor cursor2=db.rawQuery(select_class,null);
        String classID="";
        String course_number="";
        while (cursor2.moveToNext())
        {
            classID=cursor2.getString(cursor2.getColumnIndex("class_room"));
        }
        cursor2.close();
        Log.i("Ex04","selectDB="+select_course);
        Cursor cursor3=db.rawQuery(select_course,null);
        while (cursor3.moveToNext())
        {
            course_number=cursor3.getString(cursor3.getColumnIndex("course_number"));
        }
        cursor3.close();
        String select_notice="select * from notice where course_number='"+course_number+"' or class_name='"+classID+"'";
        Log.i("Ex04","selectDB="+select_notice);
        final Cursor cursor4 =db.rawQuery(select_notice,null);
        String notice_context="";
        while(cursor4.moveToNext())
        {
            notice_context=cursor4.getString(cursor4.getColumnIndex("notice_context"));
            linearLayout1=new LinearLayout(this);
            txt=new TextView(this);
            txt.setText("您有一条新的通知！");
            button=new Button(this);
            button.setText("查看");
            linearLayout1.addView(button);
            linearLayout1.addView(txt);
            linearLayout.addView(linearLayout1);
            final String finalNotice_context = notice_context;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for_send.putString("type","notice");
                    for_send.putString("context", finalNotice_context);
                    send.putExtras(for_send);
                    send.setClass(parent.this,detail_message.class);
                    startActivity(send);
                }
            });
        }
        cursor4.close();




    }
}