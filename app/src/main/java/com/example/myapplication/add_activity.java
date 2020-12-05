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
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class add_activity extends AppCompatActivity {
    private dataBase database;
    private Button add_activity;
    private EditText activity_title;
    private EditText laoshigonghao;
    private EditText huodongneirong;
    private TextView warn;
    private  String filename;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);
        activity_title=(EditText)findViewById(R.id.activity_title);
        laoshigonghao=(EditText)findViewById(R.id.laoshigonghao);
        huodongneirong=(EditText)findViewById(R.id.huodongneirong);
        warn=(TextView)findViewById(R.id.warn);

        database=new dataBase(this,"leave",null,4);
        db=database.getWritableDatabase();

        add_activity=(Button)findViewById(R.id.add_activity);
        add_activity.setOnClickListener(new add_activityclick());





    }

    private class add_activityclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String select_teacher="select * from teachers where teacher_number='"+laoshigonghao.getText()+"'";
            Log.i("Ex04","selectDB="+select_teacher);
            Cursor cursor=db.rawQuery(select_teacher,null);
            int i=0;
            while(cursor.moveToNext())
            {
                i++;
            }
            if (i==0)
            {
                warn.setText("老师工号填写错误！");
                return;
            }
            else if (activity_title.getText().toString().equals(""))
            {
                warn.setText("活动标题不能为空！");
            }
            else if (huodongneirong.getText().toString().equals(""))
            {
                warn.setText("活动内容不能为空！");
                return;
            }
            else
            {
                filename=laoshigonghao.getText().toString()+activity_title.getText().toString()+"activity";
                System.out.println("++++++++++++++++++++++++++++++++++++++");
                System.out.println(filename);
                //db=database.getWritableDatabase();
                String insert="insert into SchoolActivities(activities_name,activities_address,teacher_number)" +
                        "values('"+activity_title.getText()+"','"+filename+"','"+laoshigonghao.getText()+"')";
                Log.i("Ex04",insert);
                db.execSQL(insert);
                String inputText=huodongneirong.getText().toString();
                save(inputText);
                warn.setText("添加成功！");

            }

        }
    }

    private void save(String inputText) {
        System.out.println("##################################");
        System.out.println(filename);
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try {
            out=openFileOutput(filename, MODE_APPEND);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);

        }catch (IOException e){e.printStackTrace();}
        finally {
            try {
                if (writer!=null)
                    writer.write(inputText);
            }catch (IOException e){e.printStackTrace();}
            finally {
                try {
                    if (writer!=null)
                        writer.close();

                }catch (IOException e){e.printStackTrace();}
            }
        }
    }
}