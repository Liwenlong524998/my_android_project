package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class detail_message extends AppCompatActivity {
    private  Bundle receive;
    private String filename;
    private String type;
    private TextView file_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);

        file_context=(TextView)findViewById(R.id.file_context);

        receive=new Bundle();
        receive=this.getIntent().getExtras();
        type=receive.getString("type");
        filename=receive.getString("context");

        if (type.equals("news") || type.equals("activity"))
        {
            FileInputStream in=null;
            BufferedReader reader=null;
            String text="";
            try{
                in=openFileInput(filename);
                reader=new BufferedReader(new InputStreamReader(in));
                StringBuilder result=new StringBuilder();
                String line;
                while ((line=reader.readLine())!=null)
                {
                    result.append(line);
                }
                text=result.toString();
                reader.close();

            }catch(IOException e){e.printStackTrace();}

            file_context.setText(text);
        }
        if (type.equals("notice"))
        {
            file_context.setText(filename);
        }


    }
}