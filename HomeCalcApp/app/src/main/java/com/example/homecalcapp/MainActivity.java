package com.example.homecalcapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText stLogin;
    private EditText stPassword;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stLogin = findViewById(R.id.stLogin);
        stPassword = findViewById(R.id.stPassword);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStart) {
            Log.i("home_calc_app_tag", "Click to start button");
            String server = "https://android-for-students.ru";
            String serverPath = "/coursework/login.php";
            HashMap<String,String> map = new HashMap();
            String stLog = stLogin.getText().toString();
            String stPass = stPassword.getText().toString();
            map.put("lgn", stLog);
            map.put("pwd", stPass);
            map.put("g", "RIBO-02-21");
            HTTPRunnable httpRunnable = new HTTPRunnable(server + serverPath, map);
            Thread th = new Thread(httpRunnable);
            if(stLog.equals("Student65814") && stPass.equals("eAcsaV1")){
                Log.i("home_calc_app_tag", "Start app");
                Intent calcIntent = new Intent(getApplicationContext(), CalcActivity.class);
                startActivity(calcIntent);
            }else{
                Toast.makeText(this, "Неверные данные", Toast.LENGTH_SHORT).show();
                Log.w("home_calc_app_tag", "Invalid login or password!");
            }
        }
    }
}