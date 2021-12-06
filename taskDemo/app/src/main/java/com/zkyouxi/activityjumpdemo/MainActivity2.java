package com.zkyouxi.activityjumpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.tv);
        Intent intent=getIntent();
        String data=intent.getStringExtra("name");
        String a="欢迎您！";
        textView.setText(data+a);
    }
}