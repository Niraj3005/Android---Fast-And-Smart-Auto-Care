package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    TextInputEditText adminemail,adminpwd;
    Button adminlogin,forgetpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_login);
        
        adminemail = (TextInputEditText) findViewById(R.id.admin_email);
        adminpwd = (TextInputEditText) findViewById(R.id.admin_pwd);
        adminlogin= (Button) findViewById(R.id.admin_login);
        forgetpwd= (Button) findViewById(R.id.adminfp);

        forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogin.this, ForgetPasswordAdmin.class);
                startActivity(intent);
            }
        });

        adminlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatabaseHelper DB = new DatabaseHelper(AdminLogin.this);
                String aem=adminemail.getText().toString();
                String apd=adminpwd.getText().toString();

                if(TextUtils.isEmpty(aem) || TextUtils.isEmpty(apd))
                    Toast.makeText(AdminLogin.this,"All fields Required..",Toast.LENGTH_SHORT).show();
                else{
                    boolean checkadminemailpassword = DB.checkadminemailpassword(aem,apd);
                    if(checkadminemailpassword==true){
                        Toast.makeText(AdminLogin.this,"Login Successful...",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), AdminHomepage.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(AdminLogin.this,"Incorrect Username & Password...",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}