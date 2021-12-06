package com.zkyouxi.httprequestdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttp;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button btn;
    public static final int SET_TEXT=1;
    private String name;
    private String age;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case SET_TEXT:
                    textView.setText("my name is"+name+"\ni am "+age+"years old");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textview);
        editText=findViewById(R.id.ed_http);
        btn=findViewById(R.id.btn);

      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String a="http://172.16.5.89:9000/register";
              if(editText.getText().toString().equals(a)) {
                  Log.d("Union", "btn onclick");
                  HttpUtil.sendOkHttpRequest("http://172.16.5.89:9000/register", new okhttp3.Callback() {
                      @Override
                      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                          try {
                              String responseData = response.body().string();
                              Log.d("Union", responseData);
                              JSONObject object = new JSONObject(responseData);
                         /* JSONArray jsonArray=new JSONArray(responseData);
                          for(int i=0;i<jsonArray.length();i++){*/
                              /* JSONObject jsonObject=jsonArray.getJSONObject(i);*/
                              name = object.getString("name");
                              age = object.getString("age");

                              Log.d("Union", "my name is" + name + "i am" + age + "years old");
                              Message message = new Message();
                              message.what = SET_TEXT;
                              handler.sendMessage(message);


                              /*}*/
                          } catch (Exception e) {
                              e.printStackTrace();
                          }

                      }

                      @Override
                      public void onFailure(@NotNull Call call, @NotNull IOException e) {
                          Log.d("Union", "on fail:" + e.toString());
                      }
                  });
              }else {
                  Toast.makeText(MainActivity.this,"invalid ip",Toast.LENGTH_SHORT).show();
              }
          }
      });
    }
}