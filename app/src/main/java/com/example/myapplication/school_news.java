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

public class school_news extends AppCompatActivity {
    private ScrollView scrollView;
    private dataBase database;
    private Bundle receive;
    private String ID;
    private String identity;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private Button button;
    private Button context_button;
    private TextView context_text;
    private TextView txt;
    private Intent send;
    private Bundle for_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_news);

        linearLayout = (LinearLayout) this.findViewById(R.id.add);
        linearLayout1=(LinearLayout)this.findViewById(R.id.linearLayout1);
        send=new Intent();
        for_send=new Bundle();

        database = new dataBase(this, "leave", null, 4);
        SQLiteDatabase db = database.getWritableDatabase();
        String select = "select * from SchoolNews";
        Log.i("Ex04", "selectDB=" + select);
        final Cursor cursor = db.rawQuery(select, null);
        while (cursor.moveToNext())
        {
            linearLayout2=new LinearLayout(this);
            context_button=new Button(this);
            context_text=new TextView(this);
            context_button.setText("查看详情");
            context_text.setText(cursor.getString(cursor.getColumnIndex("news_name")));
            final String news_address=cursor.getString(cursor.getColumnIndex("news_address"));
            context_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for_send.putString("type","news");
                    for_send.putString("context",news_address);
                    send.putExtras(for_send);
                    send.setClass(school_news.this,detail_message.class);
                    startActivity(send);
                }
            });
            linearLayout2.addView(context_button);
            linearLayout2.addView(context_text);
            linearLayout1.addView(linearLayout2);



        }

        receive=this.getIntent().getExtras();
        ID=receive.getString("ID");
        identity=receive.getString("identity");

        if (identity.equals("teacher"))
        {

            txt=new TextView(this);
            txt.setText("这是个测试");
            button=new Button(this);
            button.setText("发布新闻");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    send.setClass(school_news.this,add_news.class);
                    startActivity(send);
                }
            });
            linearLayout.addView(button);
            linearLayout.addView(txt);
        }



    }
}