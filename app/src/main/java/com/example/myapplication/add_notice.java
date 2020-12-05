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

public class add_notice extends AppCompatActivity {
    private dataBase database;
    private SQLiteDatabase db;
    private Bundle receive;
    private String ID;
    private String identity;
    private EditText teacher_number;
    private EditText course_number;
    private EditText class_name;
    private EditText notice_number;
    private Button notice_add;
    private TextView warn;
    private EditText notice_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        database=new dataBase(this,"leave",null,4);
        db=database.getWritableDatabase();

        receive=this.getIntent().getExtras();
        ID=receive.getString("ID");
        identity=receive.getString("identity");

        teacher_number=(EditText)findViewById(R.id.teacher_number);
        course_number=(EditText)findViewById(R.id.course_number);
        class_name=(EditText)findViewById(R.id.class_name);
        notice_number=(EditText)findViewById(R.id.notice_number);
        notice_context=(EditText)findViewById(R.id.notice_context);
        notice_add=(Button)findViewById(R.id.notice_add);
        warn=(TextView)findViewById(R.id.warn);
        notice_add.setOnClickListener(new notice_addclick());
    }

    private class notice_addclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String select_course="select * from course where teacher_number='"+teacher_number.getText()+"' and course_number='"+course_number.getText()+"'";
            String select_teacher="select * from teacherClass where teacher_number='"+teacher_number.getText()+"' and classID='"+class_name.getText()+"'";
            String select_notice="select * from notice where notice_number='"+notice_number.getText()+"'";
            String insert="insert into notice(teacher_number,course_number,class_name,notice_number,notice_context) values" +
                    "('"+teacher_number.getText()+"','"+course_number.getText()+"','"+class_name.getText()+"','"+notice_number.getText()+"','"+notice_context.getText()+"')";
            int i=0;
            int j=0;
            int k=0;
            Log.i("Ex04","selectDB="+select_notice);
            Cursor cursor3=db.rawQuery(select_notice,null);
            while (cursor3.moveToNext())
            {
                k++;
            }
            if (!teacher_number.getText().toString().equals("")&&!course_number.getText().toString().equals(""))
            {
                Log.i("Ex04","selectDB="+select_course);
                Cursor cursor1=db.rawQuery(select_course,null);

                while(cursor1.moveToNext())
                {
                    i++;
                }
                if (i==0)
                {
                   warn.setText("老师账号(课程代码)填写错误或该老师没有注册该课程");
                   return;
                }
            }
            if (!teacher_number.getText().toString().equals(" ")&&!class_name.getText().toString().equals(""))
            {
                Log.i("Ex04","selectDB="+select_teacher);
                Cursor cursor2=db.rawQuery(select_teacher,null);
                while(cursor2.moveToNext())
                {
                    j++;
                }
                if (j==0)
                {
                    warn.setText("老师账号(班级名称)填写错误或该老师没有注册该班级");
                }
            }
            if (k!=0)
            {
                warn.setText("该通知已经被发布过，不可重复发布！");
            }
            else if (notice_context.getText().toString().equals(""))
            {
                warn.setText("通知内容不能为空！");
            }
            else if (i!=0)
            {
                Log.i("Ex04","insertDB="+insert);
                db.execSQL(insert);
                warn.setText("通知已发布！");
            }
            else  if (j!=0)
            {
                Log.i("Ex04","insertDB="+insert);
                db.execSQL(insert);
                warn.setText("通知已发布！");
            }
            else if (i==0&&j==0)
            {
                warn.setText("该通知将不会有人收到，系统已经组织该通知发布!");
            }
        }
    }
}