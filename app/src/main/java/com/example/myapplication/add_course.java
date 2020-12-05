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

public class add_course extends AppCompatActivity {
    private dataBase database;
    private SQLiteDatabase db;
    private Bundle receive;
    private String ID;
    private String identity;
    private EditText course_number;
    private EditText course_name;
    private EditText teacher_number;
    private EditText teacher_name;
    private EditText department;
    private Button confirm_add;
    private TextView warn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        database = new dataBase(this, "leave", null, 4);
        db=database.getWritableDatabase();

        receive=this.getIntent().getExtras();
        ID=receive.getString("ID");
        identity=receive.getString("identity");

        course_number=(EditText)findViewById(R.id.course_number);
        course_name=(EditText)findViewById(R.id.course_name);
        teacher_number=(EditText)findViewById(R.id.teacher_number);
        teacher_name=(EditText)findViewById(R.id.teacher_name);
        department=(EditText)findViewById(R.id.department);
        confirm_add=(Button)findViewById(R.id.confirm_add);
        confirm_add.setOnClickListener(new confirm_addclick());
        warn=(TextView)findViewById(R.id.warn);

    }

    private class confirm_addclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String select="select * from course where course_number='"+course_number.getText()+"'";
            String select_teacher="select * from teachers where teacher_number='"+teacher_number.getText()+"'";
            String insert="insert into course(course_number,course_name,teacher_number,teacher_name,department) " +
                    "values('"+course_number.getText()+"','"+course_name.getText()+"','"+teacher_number.getText()+"','"+teacher_name.getText()+"','"+department.getText()+"')";
            Log.i("Ex04","selectDB="+select);
            Cursor cursor=db.rawQuery(select,null);
            Log.i("Ex04","selectDB="+select_teacher);
            Cursor cursor1=db.rawQuery(select_teacher,null);
            int i=0;
            int j=0;
            while(cursor.moveToNext())
            {
                i++;
            }
            while(cursor1.moveToNext())
            {
                j++;
            }

            if (course_number.getText().toString().equals(""))
            {
                warn.setText("课程编号不能为空！");
                return;
            }
            else if (course_name.getText().toString().equals(""))
            {
                warn.setText("课程名称不能为空！");
                return;
            }
            else if (teacher_number.getText().toString().equals(""))
            {
                warn.setText("老师工号不能为空！");
                return;
            }
            else if (j==0)
            {
                warn.setText("老师工号填写错误！");
                return;
            }
            else if (teacher_name.getText().toString().equals(""))
            {
                warn.setText("老师姓名不能为空！");
                return;
            }
            else if (department.getText().toString().equals(""))
            {
                warn.setText("学院不能为空！");
                return;
            }
            else if (i!=0)
            {
                warn.setText("该课程编号已经被注册！");
                return;
            }
            else
            {
                Log.i("Ex04","insertDB="+insert);
                db.execSQL(insert);
                warn.setText("添加成功");
            }
        }
    }
}