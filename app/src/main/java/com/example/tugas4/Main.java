package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(myBtnSettingClick);
        SharedPreferences prefs = Main.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null); Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(myBtnLoginClick);
        if (statusLogin != null){
            button3.setText("Logout");
        }else{
            button3.setText("Login"); }
    }

    private View.OnClickListener myBtnSettingClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Main.this, SettingsActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener myBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs = Main.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
            String statusLogin = prefs.getString("isLogin",null);
            SharedPreferences.Editor edit = prefs.edit();

        Button btnLogin = findViewById(R.id.button3);
        if (statusLogin != null){
            edit.putString("isLogin",null);
            btnLogin.setText("Login");
        }else{
            edit.putString("isLogin","Admin");
            btnLogin.setText("Logout");         }
        edit.commit();
        }
    };
}
