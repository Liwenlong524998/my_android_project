package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class for_leaveMessage extends AppCompatActivity {
    private Bundle receive;
    private String student_number;
    private dataBase database=new dataBase(this,"leave",null,4);;
    private EditText xuehao;
    private EditText xingming;
    private EditText banji;
    private EditText yuanyin;
    private Button button;
    private RadioButton agree;
    private RadioButton disagree;
    private TextView warn;
    private String identity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_leave_message);


        receive=this.getIntent().getExtras();
        student_number=receive.getString("student_number");
         identity=receive.getString("parentORteacher");
         System.out.println(identity);



        SQLiteDatabase db=database.getWritableDatabase();
        button=(Button)findViewById(R.id.tijiao);
        agree=(RadioButton)findViewById(R.id.agree);
        disagree=(RadioButton)findViewById(R.id.disagree);
        warn=(TextView)findViewById(R.id.warn);

        xuehao=(EditText)findViewById(R.id.qingjia_xuehao);
        xingming=(EditText)findViewById(R.id.xingming);
        banji=(EditText)findViewById(R.id.banji);
        yuanyin=(EditText)findViewById(R.id.yuanyin);

        String selectMessage="select * from leave where student_number='"+student_number+"'";
        Log.i("Ex04","selectDB="+selectMessage);
        Cursor cursor=db.rawQuery(selectMessage,null);
        while(cursor.moveToNext())
        {
            xuehao.setText(cursor.getString(cursor.getColumnIndex("student_number")));
            xingming.setText(cursor.getString(cursor.getColumnIndex("student_name")));
            banji.setText(cursor.getString(cursor.getColumnIndex("class")));
            yuanyin.setText(cursor.getString(cursor.getColumnIndex("leave_reason")));
        }

        button.setOnClickListener(new buttonclick());
    }

    private class buttonclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SQLiteDatabase db=database.getWritableDatabase();
            String update_agree_teacher="update leave set teacher_advice='yes' where student_number='"+student_number+"'";
            String update_disagree_teacher="update leave set teacher_advice='no' where student_number='"+student_number+"'";
            String update_agree_parent="update leave set parent_advice='yes' where student_number='"+student_number+"'";
            String update_disagree_parent="update leave set parent_advice='no' where student_number='"+student_number+"'";
            if (identity.equals("teacher"))
            {
                if (agree.isChecked())
                {
                    Log.i("Ex04","updateDB="+update_agree_teacher);
                    db.execSQL(update_agree_teacher);
                    warn.setText("您已经同意该请假！");
                }
                else if (disagree.isChecked())
                {
                    Log.i("Ex04","updateDB="+update_disagree_teacher);
                    db.execSQL(update_disagree_teacher);
                    warn.setText("您拒绝了该请假！");
                }
            }
            else if (identity.equals("parent"))
            {
                if (agree.isChecked())
                {
                    Log.i("Ex04","updateDB="+update_agree_parent);
                    db.execSQL(update_agree_parent);
                    warn.setText("您已经同意该请假！");
                }
                else if (disagree.isChecked())
                {
                    Log.i("Ex04","updateDB="+update_disagree_parent);
                    db.execSQL(update_disagree_parent);
                    warn.setText("您拒绝了该请假！");
                }

            }

            else
            {
                warn.setText("请选择是否同意该请假！");
            }

        }
    }
}