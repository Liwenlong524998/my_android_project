package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//private Button button1;
    private EditText edit1;
    private EditText edit2;
    private Button regist;
    private Button b1;
    private dataBase database_s;
    //private dataBase database_t;
    private dataBase database_p;
    private TextView txt1;
    private RadioButton stu;
    private RadioButton teacher;
    private RadioButton parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         edit1=(EditText)findViewById(R.id.edit1);
         edit2=(EditText)findViewById(R.id.edit2);
         txt1=(TextView)findViewById(R.id.warn) ;
         stu=(RadioButton) findViewById(R.id.student);
         teacher=(RadioButton)findViewById(R.id.teacher);
         parent=(RadioButton)findViewById(R.id.parent);
        database_s=new dataBase(this,"leave",null,4);
        //database_t=new dataBase(this,"teachers",null,1);
        database_p=new dataBase(this,"leave",null,4);
        b1=(Button)findViewById(R.id.button1);
        regist=findViewById(R.id.register);
        regist.setOnClickListener(new registclick());
        b1.setOnClickListener(new b1click());
    }

    class registclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent2=new Intent();
            intent2.setClass(MainActivity.this,for_register.class);
            System.out.println("--------------------------------------------");
            startActivity(intent2);
            System.out.println("###############################################");

        }
    }

    private class b1click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SQLiteDatabase db_s=database_s.getWritableDatabase();
            //SQLiteDatabase db_t=database_t.getWritableDatabase();
            SQLiteDatabase db_p=database_p.getWritableDatabase();
            String sql="select * from students where stu_number='"+edit1.getText()+"' and pass_word='"+edit2.getText()+"'";
            String sql_teacher="select * from teachers where teacher_number='"+edit1.getText()+"' and pass_word='"+edit2.getText()+"'";
            String sql_parent="select * from Parents where account='"+edit1.getText()+"' and password='"+edit2.getText()+"'";
            Log.i("Ex04","selectDB="+sql);
            Intent intent1 = new Intent();
            intent1.setClass(MainActivity.this, zhuyeActivity3.class);
            Bundle b1 = new Bundle();
            if (stu.isChecked())
            {
                Cursor cursor=db_s.rawQuery(sql,null);
                StringBuilder s = new StringBuilder();
                int i=0;
                while(cursor.moveToNext())
                {
                    s.append(cursor.getInt(cursor.getColumnIndex("stu_number")));
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    s.append(",");
                    s.append(cursor.getInt(cursor.getColumnIndex("pass_word")));
                    i++;
                    System.out.println(s);
                }


                if(i!=0){
                    b1.putString("n",edit1.getText().toString());
                    b1.putString("identity","student");
                    intent1.putExtras(b1);
                    startActivity(intent1);
                }
                else
                {
                    txt1.setText("用户名或密码错误！");

                }
            }
            else if (teacher.isChecked())
            {
                Log.i("Ex04","selectDB="+sql_teacher);
                Cursor cursor=db_s.rawQuery(sql_teacher,null);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                StringBuilder s = new StringBuilder();
                int i=0;
                while(cursor.moveToNext())
                {
                    s.append(cursor.getInt(cursor.getColumnIndex("teacher_number")));
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    s.append(",");
                    s.append(cursor.getInt(cursor.getColumnIndex("pass_word")));
                    i++;
                }

                if(i!=0){
                    /*Intent intent1 = new Intent();
                    intent1.setClass(MainActivity.this, zhuyeActivity3.class);
                    Bundle b1 = new Bundle();*/
                    b1.putString("n",edit1.getText().toString());
                    b1.putString("identity","teacher");
                    intent1.putExtras(b1);
                    startActivity(intent1);
                }
                else
                {
                    txt1.setText("用户名或密码错误！");
                    //System.out.println(txt;

                }
            }
            else if (parent.isChecked())
            {
                Cursor cursor=db_p.rawQuery(sql_parent,null);
                StringBuilder s = new StringBuilder();
                Log.i("Ex04","selectDB="+sql_parent);
                int i=0;
                while(cursor.moveToNext())
                {
                    s.append(cursor.getInt(cursor.getColumnIndex("account")));
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    s.append(",");
                    s.append(cursor.getInt(cursor.getColumnIndex("password")));
                    i++;
                }

                if(i!=0){
                    /*Intent intent1 = new Intent();
                    intent1.setClass(MainActivity.this, zhuyeActivity3.class);
                    Bundle b1 = new Bundle();*/
                    b1.putString("n",edit1.getText().toString());
                    b1.putString("identity","parent");
                    intent1.putExtras(b1);
                    startActivity(intent1);
                }
                else
                {
                    txt1.setText("用户名或密码错误！");

                }
            }

            else
            {
                txt1.setText("请选择你的身份");
            }

        }
    }
    /*class b1click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent1=new Intent();
            intent1.setClass(MainActivity.this,MainActivity2.class);
            Bundle b1=new Bundle();
            EditText et1=(EditText)findViewById(R.id.edit1);
            EditText et2=(EditText)findViewById(R.id.edit2);

            System.out.println("shuchu========================================");
                b1.putString("n",et1.getText().toString());
                b1.putString("p",et2.getText().toString());
                intent1.putExtras(b1);
                startActivity(intent1);

        }
    }*/
}