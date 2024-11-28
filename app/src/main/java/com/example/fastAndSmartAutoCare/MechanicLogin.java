package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MechanicLogin extends AppCompatActivity {
    TextInputEditText memail,mpwd;
Button mregister,mlogin,forgetpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mechanic_login);

        memail=findViewById(R.id.m_email);
        mpwd=findViewById(R.id.m_pwd);
        mlogin=findViewById(R.id.m_login);
        mregister=findViewById(R.id.m_register);
        forgetpwd= (Button) findViewById(R.id.mechanicfp);


        forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MechanicLogin.this, ForgetPasswordMechanic.class);
                startActivity(intent);
            }
        });

        mlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatabaseHelper DB = new DatabaseHelper(MechanicLogin.this);
                String mem=memail.getText().toString();
                String mpd=mpwd.getText().toString();

                if(TextUtils.isEmpty(mem) || TextUtils.isEmpty(mpd))
                    Toast.makeText(MechanicLogin.this,"All fields Required..",Toast.LENGTH_SHORT).show();
                else{
                    boolean checkmechpassword = DB.checkmechemailpassword(mem,mpd);
                    List<MechanicModel> all = DB.viewMechanic(mem);
                    int active= all.get(0).getIsactive();
                    if(checkmechpassword==true){
                        if(active==1){
                            Toast.makeText(MechanicLogin.this,"Login Successful...",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MechanicLogin.this, MechanicDashboard.class);
                            i.putExtra("email",mem);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(MechanicLogin.this,"Mechanic is not active...!",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(MechanicLogin.this,"Incorrect Username & Password...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MechanicLogin.this, RegistrationMechanic.class);
                startActivity(i);
            }
        });

    }
}