package com.example.tugas4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button resetButton = findViewById(R.id.button4);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                builder.setMessage("Apakah anda yakin untuk mereset nilai protein?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                final Dialog dialog1 = new Dialog(Main.this);
                                dialog1.setContentView(R.layout.custom_dialog); dialog1.setTitle("Custom Dialog");

                                Button btnDialog = dialog1.findViewById(R.id.button5);
                                btnDialog.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        // TODO Auto-generated method stub
                                        dialog1.dismiss();     } });

                                    dialog1.show();
                            }
                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog = new ProgressDialog(Main.this);
                        progressDialog.setMessage("Melakukan sesuatu ...");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                    handler.sendEmptyMessage(0);
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();

                                }
                            }
                        });
                        thread.start();
                        progressDialog.show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(myBtnSettingClick);
        SharedPreferences prefs = Main.this.getSharedPreferences("prefs_file", MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin", null);
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(myBtnLoginClick);
        if (statusLogin != null) {
            button3.setText("Logout");
        } else {
            button3.setText("Login");
        }
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
            SharedPreferences prefs = Main.this.getSharedPreferences("prefs_file", MODE_PRIVATE);
            String statusLogin = prefs.getString("isLogin", null);
            SharedPreferences.Editor edit = prefs.edit();

            Button btnLogin = findViewById(R.id.button3);
            if (statusLogin != null) {
                edit.putString("isLogin", null);
                btnLogin.setText("Login");
            } else {
                edit.putString("isLogin", "Admin");
                btnLogin.setText("Logout");
            }
            edit.commit();
        }
    };


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            progressDialog.dismiss();
        }
    };

}
