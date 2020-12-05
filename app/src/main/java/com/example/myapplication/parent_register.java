package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class parent_register extends AppCompatActivity {
    private EditText zhanghao;
    private EditText mima;
    private EditText xingming;
    private EditText xuesheng;
    private EditText shoujihao;
    private Button bt_register;
    private Button bt_login;
    private dataBase database;
    //private dataBase database_stu;
    private TextView warn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_register);
        zhanghao=(EditText)findViewById(R.id.zhanghao);
        mima=(EditText)findViewById(R.id.mima);
        xingming=(EditText)findViewById(R.id.xingming);
        xuesheng=(EditText)findViewById(R.id.xuesheng);
        shoujihao=(EditText)findViewById(R.id.dianhua);
        database=new dataBase(this,"leave",null,4);
        //database_stu=new dataBase(this,"leave",null,3);
        warn=(TextView)findViewById(R.id.warn);
        bt_register=(Button)findViewById(R.id.register);
        bt_login=(Button)findViewById(R.id.go_login);
        bt_register.setOnClickListener(new registerclick());
        bt_login.setOnClickListener(new loginclick());
    }
    class registerclick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(zhanghao.getText().toString().equals(""))
            {
                warn.setText("请输入账号！");
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
            else if (xuesheng.getText().toString().equals(""))
            {
                warn.setText("请输入您孩子的学号");
            }
            else if (shoujihao.getText().toString().equals(""))
            {
                warn.setText("请输入手机号码！");
            }
            else
            {
                SQLiteDatabase db=database.getWritableDatabase();
                //SQLiteDatabase db1=database_stu.getWritableDatabase();
                String parent="select * from Parents where account='"+zhanghao.getText()+"' ";
                String child="select * from students where stu_number='"+xuesheng.getText()+"'";
                String insert="insert into parents(account,password,name,student_number,phone) " +
                        "values('"+zhanghao.getText()+"','"+mima.getText()+"','"+xingming.getText()+"',"+xuesheng.getText()+"," +
                        "'"+shoujihao.getText()+"')";
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
                Log.i("Ex04","select_parent="+parent);
                Log.i("Ex04","select_student="+child);
                Cursor cursor_student=db.rawQuery(child,null);
                System.out.println("===================================================");
                Cursor cursor_parent=db.rawQuery(parent,null);
                StringBuilder s = new StringBuilder();
                int i=0;
                int j=0;
                while(cursor_parent.moveToNext())
                {
                    s.append(cursor_parent.getInt(cursor_parent.getColumnIndex("account")));
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    i++;
                }
                System.out.println(i);
                while(cursor_student.moveToNext())
                {
                    j++;
                }
                System.out.println(j);

                if(i!=0){
                    warn.setText("该用户已存在！");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++");
                }
                else if (j==0)
                {
                    warn.setText("学生的账号错误或不存在该学生！");
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
            intent1.setClass(parent_register.this,MainActivity.class);
            startActivity(intent1);
        }
    }
}