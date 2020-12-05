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

public class school_notice extends AppCompatActivity {
    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private Button button;
    private TextView txt;
    private dataBase database;
    private String ID;
    private Bundle receive;
    private Intent for_send;
    private Bundle send;
    private TextView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_notice);

        receive=this.getIntent().getExtras();
        ID=receive.getString("ID");
        System.out.println(ID);

        linearLayout=(LinearLayout)findViewById(R.id.linearLayout1);

        scrollView=(ScrollView)findViewById(R.id.scrollView);
        database=new dataBase(this,"leave",null,4);
        final SQLiteDatabase db=database.getWritableDatabase();
        String select_id="select * from leave where student_number='"+ID+"'";
        System.out.println("+++++++++++++++++++++++++++");
        final Cursor cursor1=db.rawQuery(select_id,null);
        StringBuilder s_id= new StringBuilder();
        String isagree_parent="";
        String isagree_teacher="";
        for_send=new Intent();
        send=new Bundle();
        int i=0;
        while (cursor1.moveToNext())
        {
                isagree_parent=cursor1.getString(cursor1.getColumnIndex("parent_advice"));
                isagree_teacher=cursor1.getString(cursor1.getColumnIndex("teacher_advice"));
                if (isagree_parent==null)
                {
                    isagree_parent="1";
                }
            if (isagree_teacher==null)
            {
                isagree_teacher="1";
            }

            System.out.println(isagree_parent);
            System.out.println("!!!!!!!");
            System.out.println(isagree_parent);
            System.out.println(isagree_teacher);
            check=new TextView(this);
            linearLayout1=new LinearLayout(this);
            txt=new TextView(this);
            button=new Button(this);
            if (isagree_parent.equals("yes")&& isagree_teacher.equals("yes"))
            {
                txt.setText("       请假已通过！");
            }
            else
            {
                txt.setText("       请假未通过！");
            }

            button.setText("确认");
            System.out.println("+++++++");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String delete="delete from leave where student_number='"+ID+"'";
                    Log.i("Ex04","deleteDB="+delete);
                    db.execSQL(delete);
                }
            });
            linearLayout1.addView(button);
            linearLayout1.addView(txt);
            linearLayout1.addView(check);
            linearLayout.addView(linearLayout1);
        }
        System.out.println(s_id);
        System.out.println(i);


        String select_class="select * from students where stu_number='"+ID+"'";
        String select_course="select * from StudentTeacher where stu_number='"+ID+"'";
        Log.i("Ex04","selectDB="+select_class);
        Cursor cursor2=db.rawQuery(select_class,null);
        String classID="";
        String course_number="";
        while (cursor2.moveToNext())
        {
            classID=cursor2.getString(cursor2.getColumnIndex("class_room"));
        }
        Log.i("Ex04","selectDB="+select_course);
        Cursor cursor3=db.rawQuery(select_course,null);
        while (cursor3.moveToNext())
        {
            course_number=cursor3.getString(cursor3.getColumnIndex("course_number"));
        }
        String select_notice="select * from notice where course_number='"+course_number+"' or class_name='"+classID+"'";
        Log.i("Ex04","selectDB="+select_notice);
        Cursor cursor4 =db.rawQuery(select_notice,null);
        while(cursor4.moveToNext())
        {
            linearLayout1=new LinearLayout(this);
            txt=new TextView(this);
            txt.setText("您有一条新的通知！");
            button=new Button(this);
            button.setText("查看");
            linearLayout1.addView(button);
            linearLayout1.addView(txt);
            linearLayout.addView(linearLayout1);
        }

    }
}