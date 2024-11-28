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

import com.example.onroadremotevehicleassistantapp.Model.UserModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;


public class Registration extends AppCompatActivity {
    TextInputEditText sendfname,sendlname,sendemail,sendaddress,sendpwd,sendcpwd,sendvehicleno,sendphoneno;
    Button uregister,ulogin;
    TextView show;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);

        sendfname = (TextInputEditText) findViewById(R.id.u_fname);
        sendlname = (TextInputEditText)findViewById(R.id.u_lname);
        sendemail = (TextInputEditText)findViewById(R.id.u_email);
        sendpwd = (TextInputEditText)findViewById(R.id.u_pwd);
        sendcpwd = (TextInputEditText)findViewById(R.id.u_cpwd);
        sendvehicleno = (TextInputEditText)findViewById(R.id.u_vehicleno);
        sendphoneno = (TextInputEditText)findViewById(R.id.u_phoneno);
        sendaddress = (TextInputEditText)findViewById(R.id.u_address);
        uregister=(Button)findViewById(R.id.u_register);
        ulogin=(Button)findViewById(R.id.u_login);
        show=findViewById(R.id.showpwd);

        sendpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // get the password when we start typing
                String password = sendpwd.getText().toString();
                validatepass(password);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });


        uregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel userModel;
                DatabaseHelper DB = new DatabaseHelper(Registration.this);
                String fn=sendfname.getText().toString();
                String ln=sendlname.getText().toString();
                String em=sendemail.getText().toString();
                String pd=sendpwd.getText().toString();
                String cpd=sendcpwd.getText().toString();
                String vn=sendvehicleno.getText().toString();
                String phn=sendphoneno.getText().toString();
                String add=sendaddress.getText().toString();

                if(TextUtils.isEmpty(fn) || TextUtils.isEmpty(ln) || TextUtils.isEmpty(em) || TextUtils.isEmpty(pd) || TextUtils.isEmpty(cpd) || TextUtils.isEmpty(phn) || TextUtils.isEmpty(vn)  || TextUtils.isEmpty(add))
                    Toast.makeText(Registration.this,"All fields Required..",Toast.LENGTH_SHORT).show();
                else{
                    if(pd.equals(cpd)){
                        Boolean checkuser = DB.checkuseremail(em);
                        if(checkuser==false){
                            userModel = new UserModel(-1, fn, ln, em, pd, vn,phn,add,null);
                            boolean success = DB.addOneUser(userModel);
                            if(success==true){
                                Toast.makeText(Registration.this, "Registered Successfully...!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Registration.this,Login.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(Registration.this, "Registeration Failed...!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(Registration.this, "User Already Exists...!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Registration.this, "Password are not Matching...!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        ulogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, Login.class);
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
                        show.setTextColor(0xFFFF9800);
                    } else {
                        show.setText("Strong Password");
                        show.setTextColor(Color.GREEN);
                    }
                }
            }
        }
    }
}
