package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class register extends AppCompatActivity {
private Button login;
private Button register;
private dataBase database;
private EditText xuehao;
private EditText mima;
private EditText xingming;
private EditText xingbie;
private EditText banji;
private EditText shoujihao;
private TextView warn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        xuehao=(EditText)findViewById(R.id.xuehao);
        mima=(EditText)findViewById(R.id.mima);
        xingming=(EditText)findViewById(R.id.xingming);
        xingbie=(EditText)findViewById(R.id.xingbie);
        banji=(EditText)findViewById(R.id.banji);
        shoujihao=(EditText)findViewById(R.id.shoujihao);
        warn=(TextView)findViewById(R.id.warn);
        database=new dataBase(this,"leave",null,4);
        login=(Button)findViewById(R.id.go_login);
        register=(Button)findViewById(R.id.register);
        login.setOnClickListener(new login());
        register.setOnClickListener(new goregister());
    }
    class login implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(register.this,MainActivity.class);
        }
    }
    class goregister implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (xuehao.getText().toString().equals(""))
            {
                warn.setText("请输入学号！");
                return;
            }
            else if (mima.getText().toString().equals(""))
            {
                warn.setText("请输入密码！");
                return;
            }
            else if (xingming.getText().toString().equals(""))
            {
                warn.setText("请输入姓名！");
                return;
            }
            else if (xingbie.getText().toString().equals(""))
            {
                warn.setText("请输入性别！");
                return;
            }
            else if (banji.getText().toString().equals(""))
            {
                warn.setText("请输入班级！");
                return;
            }
            else if (shoujihao.getText().toString().equals(""))
            {
                warn.setText("请输入手机号！");
                return;
            }
            else
            {
                SQLiteDatabase db=database.getWritableDatabase();
                String sql="select * from students where stu_number='"+xuehao.getText()+"' ";
                String insert="insert into Students(stu_number,pass_word,name,sex,class_room,phone_number) " +
                        "values('"+xuehao.getText()+"','"+mima.getText()+"','"+xingming.getText()+"','"+xingbie.getText()+"'," +
                        "'"+banji.getText()+"','"+shoujihao.getText()+"')";
                Log.i("Ex04","createDB="+sql);
                Cursor cursor=db.rawQuery(sql,null);
                StringBuilder s = new StringBuilder();
                int i=0;
                while(cursor.moveToNext())
                {
                    s.append(cursor.getInt(cursor.getColumnIndex("stu_number")));
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    s.append(cursor.getInt(cursor.getColumnIndex("pass_word")));
                    i++;
                }
                System.out.println(i);

                if(i!=0){
                    warn.setText("该用户已存在！");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++");
                }

                else{
                    Log.i("Ex04","createDB="+insert);
                    db.execSQL(insert);
                    warn.setText("注册成功！");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++");
                }
            }


        }
    }
}