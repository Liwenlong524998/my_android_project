package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class leave_message extends AppCompatActivity {
    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private Button button;
    private TextView txt;
    private dataBase database;
    private String ID;
    private Bundle receive;
    private Intent for_send;
    private Bundle send;
    private TextView check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_message);
        receive=this.getIntent().getExtras();
        ID=receive.getString("ID");
        System.out.println(ID);



        //linearLayout = (LinearLayout) this.findViewById(R.id.linearLayout1);


        linearLayout=(LinearLayout)findViewById(R.id.linearLayout1);
        //System.out.println(linearLayout.getOrientation());
        //System.out.println("#####################");
        scrollView=(ScrollView)findViewById(R.id.scrollView);
        database=new dataBase(this,"leave",null,4);
        SQLiteDatabase db=database.getWritableDatabase();
        String select_id="select * from teacherClass where teacher_number='"+ID+"'";
        System.out.println("+++++++++++++++++++++++++++");
        Log.i("Ex04","selectDB"+select_id);
        Cursor cursor1=db.rawQuery(select_id,null);
        StringBuilder s_id= new StringBuilder();
        int i=0;
        while (cursor1.moveToNext())
        {
            s_id.append(cursor1.getString(cursor1.getColumnIndex("classID")));
            i++;
        }
        System.out.println(s_id);
        if (i!=0)
        {
            String select ="select * from leave where class='"+s_id+"' ";
            System.out.println("---------------------------------------");
            Log.i("Ex04","selectDB="+select);
            final Cursor cursor=db.rawQuery(select,null);
            String student_number="";
            String isagree="";
            for_send=new Intent();
            send=new Bundle();
            while (cursor.moveToNext())
            {
                student_number=cursor.getString(cursor.getColumnIndex("student_number"));
                System.out.println(student_number);
                isagree=cursor.getString(cursor.getColumnIndex("teacher_advice"));
                System.out.println(isagree);
                check=new TextView(this);
                //check.setTextColor(12);
                linearLayout1=new LinearLayout(this);
                txt=new TextView(this);
                button=new Button(this);
                if (isagree==null)
                {
                    check.setText("    未审核");
                }
                else
                {
                    check.setText("    已审核");
                }


                txt.setText(cursor.getString(cursor.getColumnIndex("student_name")));
                button.setText("查看详情");
                final String finalStudent_number = student_number;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        send.putString("student_number", finalStudent_number.toString());
                        send.putString("parentORteacher","teacher");
                        System.out.println("++++++++++++++++++++++++");
                        System.out.println(finalStudent_number);
                        for_send.putExtras(send);
                        for_send.setClass(leave_message.this,for_leaveMessage.class);
                        startActivity(for_send);
                    }
                });
                linearLayout1.addView(button);
                linearLayout1.addView(txt);
                linearLayout1.addView(check);
                linearLayout.addView(linearLayout1);
            }

        }



    }
}