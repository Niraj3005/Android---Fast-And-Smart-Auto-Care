package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class ForgetPasswordUser extends AppCompatActivity {
TextInputEditText userEmail;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_forget_password_user);

        userEmail = findViewById(R.id.email_user);
        submit = findViewById(R.id.submit1);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper DB = new DatabaseHelper(ForgetPasswordUser.this);

                String em = userEmail.getText().toString();

                Boolean checkuser = DB.checkuseremail(em);
                if(checkuser==false){
                        Toast.makeText(ForgetPasswordUser.this, "Please enter registered email address..", Toast.LENGTH_SHORT).show();

                    }else {
                        Intent i = new Intent(ForgetPasswordUser.this,ResetPasswordUser.class);
                        i.putExtra("email",em);
                        startActivity(i);
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
            Intent intent = new Intent(ForgetPasswordUser.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(ForgetPasswordUser.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}