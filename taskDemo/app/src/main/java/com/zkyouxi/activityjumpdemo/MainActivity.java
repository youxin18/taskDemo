package com.zkyouxi.activityjumpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        username=findViewById(R.id.name_et);
        password=findViewById(R.id.password_et);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a="123456";
                String b="123456";
                if(username.getText().toString().equals(a)&&password.getText().toString().equals(b)){
                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                    intent.putExtra("name","游欣");
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"用户名或密码不对！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}