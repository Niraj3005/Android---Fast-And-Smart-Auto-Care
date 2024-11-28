package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextInputEditText useremail,userpwd;
    Button userregister,userlogin,forgetpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);


        useremail = (TextInputEditText) findViewById(R.id.user_email);
        userpwd = (TextInputEditText) findViewById(R.id.user_pwd);
        userregister = (Button) findViewById(R.id.user_register);
        userlogin= (Button) findViewById(R.id.user_login);
        forgetpwd= (Button) findViewById(R.id.fp1);

        userlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatabaseHelper DB = new DatabaseHelper(Login.this);
                String uem=useremail.getText().toString();
                String upd=userpwd.getText().toString();

                if(TextUtils.isEmpty(uem) || TextUtils.isEmpty(upd))
                    Toast.makeText(Login.this,"All fields Required..",Toast.LENGTH_SHORT).show();
                else{
                    boolean checkuseremailpassword = DB.checkuseremailpassword(uem,upd);
                    if(checkuseremailpassword==true){
                        Toast.makeText(Login.this,"Login Successful...",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), UserDashboard.class);
                        i.putExtra("email",uem);
                        startActivity(i);
                    }else{
                        Toast.makeText(Login.this,"Incorrect Username & Password...",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        userregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });

        forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgetPasswordUser.class);
                startActivity(intent);
            }
        });
    }


}