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

public class teacher_register extends AppCompatActivity {
private EditText gonghao;
private EditText mima;
private EditText xingming;
private EditText xingbie;
private EditText xueyuan;
private EditText shenfenzheng;
private EditText shoujihao;
private Button bt_register;
private Button bt_login;
private dataBase database;
private TextView warn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        gonghao=(EditText)findViewById(R.id.gonghao);
        mima=(EditText)findViewById(R.id.mima);
        xingming=(EditText)findViewById(R.id.xingming);
        xingbie=(EditText)findViewById(R.id.xingbie);
        shoujihao=(EditText)findViewById(R.id.shoujihao);
        warn=(TextView)findViewById(R.id.warn);
        database=new dataBase(this,"leave",null,4);
        bt_register=(Button)findViewById(R.id.register);
        bt_login=(Button)findViewById(R.id.go_login);
        bt_register.setOnClickListener(new registerclick());
        bt_login.setOnClickListener(new loginclick());
    }
    class registerclick implements View.OnClickListener{
        @Override

        public void onClick(View v) {
            String gh=gonghao.getText().toString();
            if (gonghao.getText().toString().equals(""))
            {
                warn.setText("请输入工号");
                System.out.println("--------------------------------------");
                return;
            }
            else if (mima.getText().toString().equals(""))
            {
                warn.setText("请输入密码");
                return;
            }
            else if (xingming.getText().toString().equals(""))
            {
                warn.setText("请输入姓名");
                return;
            }
            else if (xingbie.getText().toString().equals(""))
            {
                warn.setText("请输入性别");
                return;
            }
            else if (shoujihao.getText().toString().equals(""))
            {
                warn.setText("请输入手机号");
                return;
            }
            else
                {
                SQLiteDatabase db=database.getWritableDatabase();
                String insert="insert into teachers(teacher_number,pass_word,name,sex,phone_number) " +
                        "values('"+gonghao.getText()+"','"+mima.getText()+"','"+xingming.getText()+"','"+xingbie.getText()+"','"+shoujihao.getText()+"')";
                String sql="select * from teachers where teacher_number='"+gonghao.getText()+"' ";
                Log.i("Ex04","createDB="+sql);
                    System.out.println("####################################");
                Cursor cursor=db.rawQuery(sql,null);
                    System.out.println("--------------------------------------");
                StringBuilder s = new StringBuilder();
                int i=0;
                while(cursor.moveToNext())
                {
                    s.append(cursor.getInt(cursor.getColumnIndex("teacher_number")));
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    s.append(cursor.getInt(cursor.getColumnIndex("pass_word")));
                    i++;
                }
                if(i!=0){
                    warn.setText("该用户已存在！");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++");
                    return;
                }
                else
                {
                    Log.i("Ex04","createDB="+insert);
                    db.execSQL(insert);
                    warn.setText("注册成功！");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++");
                }
            }


        }
    }
    class loginclick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent1=new Intent();
            intent1.setClass(teacher_register.this,MainActivity.class);
            startActivity(intent1);
        }
    }
}