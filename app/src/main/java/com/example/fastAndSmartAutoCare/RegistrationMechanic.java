package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class RegistrationMechanic extends AppCompatActivity {
    TextInputEditText mfname,mlname,memail,maddress,mpwd,mcpwd,mphoneno;
    Button mregister,mlogin;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration_mechanic);

        mfname = (TextInputEditText) findViewById(R.id.mhfname);
        mlname = (TextInputEditText) findViewById(R.id.mhlname);
        memail = (TextInputEditText) findViewById(R.id.mhemail);
        mpwd = (TextInputEditText) findViewById(R.id.mhpwd);
        mcpwd = (TextInputEditText) findViewById(R.id.mhcpwd);
        mphoneno = (TextInputEditText) findViewById(R.id.mhphoneno);
        maddress = (TextInputEditText) findViewById(R.id.mhaddress);
        mregister = (Button) findViewById(R.id.mhregister);
        mlogin = (Button) findViewById(R.id.mhlogin);
        show=findViewById(R.id.showpwd1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String regdate = sdf.format(new Date());

        mpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // get the password when we start typing
                String password = mpwd.getText().toString();
                validatepass(password);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MechanicModel mechanicModel;
                DatabaseHelper DB = new DatabaseHelper(RegistrationMechanic.this);
                String mfn = mfname.getText().toString();
                String mln = mlname.getText().toString();
                String mem = memail.getText().toString();
                String mpd = mpwd.getText().toString();
                String mcpd = mcpwd.getText().toString();
                String mphn = mphoneno.getText().toString();
                String madd = maddress.getText().toString();


                if (TextUtils.isEmpty(mfn) || TextUtils.isEmpty(mln) || TextUtils.isEmpty(mem) || TextUtils.isEmpty(mpd) || TextUtils.isEmpty(mcpd) || TextUtils.isEmpty(mphn) || TextUtils.isEmpty(madd))
                    Toast.makeText(RegistrationMechanic.this, "All fields Required..", Toast.LENGTH_SHORT).show();
                else {
                    if (mpd.equals(mcpd)) {
                        Boolean checkuser = DB.checkmechemail(mem);
                        if (checkuser == false) {
                            mechanicModel = new MechanicModel(-1, mfn, mln, mem, mpd, mphn, madd,regdate, null, 0, 3.5F);
                            boolean success = DB.addOneMechanic(mechanicModel);
                            if (success == true) {
                                Toast.makeText(RegistrationMechanic.this, "Registered Successfully...!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegistrationMechanic.this, MechanicLogin.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(RegistrationMechanic.this, "Registeration Failed...!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegistrationMechanic.this, "User Already Exists...!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistrationMechanic.this, "Password are not Matching...!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationMechanic.this, MechanicLogin.class);
                startActivity(intent);
            }

        });
    }
        public void validatepass(String password) {

            Pattern uppercase = Pattern.compile("[A-Z]");
            Pattern lowercase = Pattern.compile("[a-z]");
            Pattern digit = Pattern.compile("[0-9]");

            if (!lowercase.matcher(password).find()) {
                show.setText("Weak Password");
                show.setTextColor(Color.RED);
            } else {
                if (!uppercase.matcher(password).find()) {
                    show.setText("Weak Password");
                    show.setTextColor(Color.RED);
                } else {
                    if (!digit.matcher(password).find()) {
                        show.setText("Medium Password");
                        show.setTextColor(0xFFFF9800);
                    } else {
                        if (password.length() < 8) {
                            show.setText("Weak Password");
                            show.setTextColor(Color.RED);
                        } else {
                            show.setText("Strong Password");
                            show.setTextColor(Color.GREEN);
                        }
                    }
                }
            }
        }
    }
