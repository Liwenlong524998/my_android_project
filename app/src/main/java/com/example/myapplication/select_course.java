package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class select_course extends AppCompatActivity {
    private dataBase database;
    private SQLiteDatabase db;
    private Bundle receive;
    private String ID;
    private String identity;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private TextView course_number;
    private TextView course_name;
    private TextView teacher_name;
    private Button selected;
    private String student_name;
    private String teacher_number;
    private String t_name;
    private String c_number;
    private String c_name;
    private TextView warn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        linearLayout1=(LinearLayout)findViewById(R.id.linearLayout1);
        database = new dataBase(this, "leave", null, 4);
        db=database.getWritableDatabase();
        warn=(TextView)findViewById(R.id.warn);

        receive=this.getIntent().getExtras();
        ID=receive.getString("ID");
        identity=receive.getString("identity");


        String select_student="select * from students where stu_number='"+ID+"'";
        Log.i("Ex04","selectDB="+select_student);
        Cursor cursor1=db.rawQuery(select_student,null);
        while (cursor1.moveToNext())
        {
            student_name=cursor1.getString(cursor1.getColumnIndex("name"));
        }
        cursor1.close();
        final String select="select * from course";
        Log.i("Ex04","selectDB="+select);
        Cursor cursor=db.rawQuery(select,null);

        while(cursor.moveToNext())
        {
            teacher_number=cursor.getString(cursor.getColumnIndex("teacher_number"));
            t_name=cursor.getString(cursor.getColumnIndex("teacher_name"));
            c_number=cursor.getString(cursor.getColumnIndex("course_number"));
            c_name=cursor.getString(cursor.getColumnIndex("course_name"));

            linearLayout=new LinearLayout(this);
            course_number=new TextView(this);
            course_name=new TextView(this);
            teacher_name=new TextView(this);
            selected=new Button(this);
            course_number.setText(c_number);
            course_name.setText(c_name);
            teacher_name.setText(t_name);
            selected.setText("选课");
            linearLayout.addView(selected);
            linearLayout.addView(course_number);
            linearLayout.addView(course_name);
            linearLayout.addView(teacher_name);
            linearLayout1.addView(linearLayout);
            selected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectstudent_teacher="select * from StudentTeacher where stu_number='"+ID+"' and course_number='"+c_number+"' and teacher_number='"+teacher_number+"'";
                   Log.i("Ex04","selectDB="+selectstudent_teacher);
                    Cursor cursor2=db.rawQuery(selectstudent_teacher,null);
                    int k=0;
                    while (cursor2.moveToNext())
                    {
                        k++;
                    }
                    cursor2.close();
                    if (k!=0)
                    {
                        warn.setText("已经选择过该课程！");
                    }
                    else
                    {
                        String insert="insert into StudentTeacher(stu_number,course_number,teacher_number,student_name,teacher_name,course_name) values" +
                                "('"+ID+"','"+c_number+"','"+teacher_number+"','"+student_name+"','"+t_name+"','"+c_name+"')" ;
                        Log.i("Ex04","insertDB="+insert);
                        db.execSQL(insert);
                        warn.setText("选课成功！");
                    }
                }
            });
        }
    }
}