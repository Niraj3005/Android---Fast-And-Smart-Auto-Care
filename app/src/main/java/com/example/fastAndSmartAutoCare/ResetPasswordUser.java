package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ResetPasswordUser extends AppCompatActivity {
TextView email;
TextInputEditText pwd,cpwd;
Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_reset_password_user);

        email = findViewById(R.id.txt4);
        pwd = findViewById(R.id.pwduser);
        cpwd = findViewById(R.id.cpwduser);
        reset = findViewById(R.id.reset2);

        Intent intent = getIntent();
        String str = intent.getStringExtra("email");
        email.setText("Your Email Address : \n"+str);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pd = pwd.getText().toString();
                String cpd = cpwd.getText().toString();
                DatabaseHelper DB = new DatabaseHelper(ResetPasswordUser.this);
                if (TextUtils.isEmpty(pd) || TextUtils.isEmpty(cpd))
                    Toast.makeText(ResetPasswordUser.this, "All fields Required..", Toast.LENGTH_SHORT).show();
                else {
                    if(pd.equals(cpd)){
                    boolean success = DB.updateUserPassword(str,pd);
                    if (success == true) {
                        Toast.makeText(ResetPasswordUser.this, "Password Changed Successfully...!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ResetPasswordUser.this, Login.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(ResetPasswordUser.this, "Failed to Change Password...!", Toast.LENGTH_SHORT).show();
                    }
                    }else{
                        Toast.makeText(ResetPasswordUser.this, "Password are not Matching...!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            Intent intent = new Intent(ResetPasswordUser.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(ResetPasswordUser.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}