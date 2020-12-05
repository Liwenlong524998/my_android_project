package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class leave extends AppCompatActivity {
private EditText xuehao;
private EditText xingming;
    private EditText yuanyin;
    private EditText banji;
    private Button tijiao;
    private dataBase database;
    private dataBase database_s;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        xuehao=(EditText)findViewById(R.id.qingjia_xuehao);
        xingming=(EditText)findViewById(R.id.xingming);
        yuanyin=(EditText)findViewById(R.id.yuanyin);
        banji=(EditText)findViewById(R.id.banji);
        tijiao=(Button)findViewById(R.id.tijiao);
        txt=(TextView)findViewById(R.id.warn);
        database=new dataBase(this,"leave",null,4);
        //database_s=new dataBase(this,"students",null,3);
        tijiao.setOnClickListener(new tijiaoclick());
    }

    private class tijiaoclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SQLiteDatabase db=database.getWritableDatabase();
            //SQLiteDatabase db_s=database_s.getWritableDatabase();
            String select_s="select * from students where stu_number='"+xuehao.getText()+"' and class_room='"+banji.getText()+"'";
            System.out.println("---------------------------------------");
            Log.i("Ex04","selectDB="+select_s);
            Cursor cursor=db.rawQuery(select_s,null);
            int i=0;
            StringBuilder s= new StringBuilder();
            while (cursor.moveToNext())
            {
                s.append(cursor.getString(cursor.getColumnIndex("stu_number")));
                s.append(",");
                s.append(cursor.getString(cursor.getColumnIndex("class_room")));
                i++;
            }
            System.out.println(s);
            System.out.println(i);
            if (i!=0)
            {
                String insert="insert into leave(student_number,student_name,leave_reason,class)" +
                        "values('"+xuehao.getText()+"','"+xingming.getText()+"','"+yuanyin.getText()+"','"+banji.getText()+"')";
                Log.i("Ex04","insertDB="+insert);
                db.execSQL(insert);
                System.out.println("++++++++++++++++++++++++++++++++++");
                txt.setText("提交成功");
            }
            else
                {
                    txt.setText("账号或者班级填写错误！");
                }
        }
    }
}