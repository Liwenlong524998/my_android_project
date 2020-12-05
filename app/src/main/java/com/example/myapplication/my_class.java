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

public class my_class extends AppCompatActivity {
private Button button;
private EditText banji;
private dataBase database;
private EditText gonghao;
private TextView warn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);
        button=(Button)findViewById(R.id.add);
        banji=(EditText)findViewById(R.id.banji);
        gonghao=(EditText)findViewById(R.id.gonghao);
        warn=(TextView)findViewById(R.id.warn);
        database=new dataBase(this,"leave",null,4);
        button.setOnClickListener(new buttonclick());
    }

    private class buttonclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SQLiteDatabase db=database.getWritableDatabase();
            String select="select * from teachers where teacher_number='"+gonghao.getText()+"'";
            String select_class="select * from teacherClass where teacher_number='"+gonghao.getText()+"' and classID='"+banji.getText()+"'";
            String insert="insert into teacherClass(teacher_number,classID) values('"+gonghao.getText()+"','"+banji.getText()+"')";
            Log.i("Ex04","selectDB="+select);
            Cursor cursor=db.rawQuery(select,null);
            int i=0;
            while(cursor.moveToNext())
            {
                i++;
            }
            if (i!=0)
            {
                int j=0;
                Log.i("Ex04","selectDB="+select_class);
                Cursor cursor1=db.rawQuery(select_class,null);
                while(cursor1.moveToNext())
                {
                    j++;
                }
                if (j==0)
                {
                    Log.i("Ex04","insertDB"+insert);
                    db.execSQL(insert);
                    warn.setText("添加成功！");
                }
                else
                {
                    warn.setText("该班级已经被注册！");
                }


            }
            else
            {
                warn.setText("不存在该老师！");
            }

        }
    }
}