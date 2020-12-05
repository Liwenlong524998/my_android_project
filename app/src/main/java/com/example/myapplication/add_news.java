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

public class add_news extends AppCompatActivity {
    private dataBase database;
    private Button add_news;
    private EditText news_title;
    private EditText laoshigonghao;
    private EditText xinwenneirong;
    private TextView warn;
    private  String filename;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        news_title=(EditText)findViewById(R.id.news_title);
        laoshigonghao=(EditText)findViewById(R.id.laoshigonghao);
        xinwenneirong=(EditText)findViewById(R.id.xinwenneirong);
        warn=(TextView)findViewById(R.id.warn);

        database=new dataBase(this,"leave",null,4);
        db=database.getWritableDatabase();

        add_news=(Button)findViewById(R.id.add_news);
        add_news.setOnClickListener(new add_newsclick());
    }

    private class add_newsclick implements View.OnClickListener {
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
            else if (news_title.getText().toString().equals(""))
            {
                warn.setText("新闻标题不能为空！");
                return;
            }
            else if (xinwenneirong.getText().toString().equals(""))
            {
                warn.setText("新闻内容不能为空！");
                return;
            }
            else
            {
                filename=laoshigonghao.getText().toString()+news_title.getText().toString()+"activity";
                System.out.println("++++++++++++++++++++++++++++++++++++++");
                System.out.println(filename);
                // SQLiteDatabase db=database.getWritableDatabase();
                String insert="insert into SchoolNews(news_name,news_address,teacher_number)" +
                        "values('"+news_title.getText()+"','"+filename+"','"+laoshigonghao.getText()+"')";
                Log.i("Ex04",insert);
                db.execSQL(insert);
                String inputText=xinwenneirong.getText().toString();
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