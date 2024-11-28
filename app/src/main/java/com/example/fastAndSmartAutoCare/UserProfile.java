package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onroadremotevehicleassistantapp.Model.UserModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class UserProfile extends AppCompatActivity {
    TextInputEditText name,email,phoneno,address;
    TextView uname,uemail,vehicleno;
    Button update;
    ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.mechtxt1);
        email = findViewById(R.id.mechtxt2);
        vehicleno = findViewById(R.id.usertxt3);
        phoneno = findViewById(R.id.mechtxt3);
        address = findViewById(R.id.mechtxt4);
        uname = findViewById(R.id.name);
        uemail = findViewById(R.id.uemail);
        update = findViewById(R.id.updatem);
        photo =  findViewById(R.id.mprofile_photo);

        Intent i =getIntent();
        String str = i.getStringExtra("email");
        DatabaseHelper dbhelper = new DatabaseHelper(UserProfile.this);
        List<UserModel> all=dbhelper.viewAllUser(str);
        name.setText(all.get(0).getFname()+" "+all.get(0).getLname());
        email.setText(all.get(0).getEmail());
        vehicleno.setText(all.get(0).getVehicleno());
        phoneno.setText(all.get(0).getPhoneno());
        address.setText(all.get(0).getAdd());
        Bitmap pt = all.get(0).getPhoto();
        if(pt == null){

        }
        else{
            photo.setImageBitmap(all.get(0).getPhoto());
        }

        uname.setText(all.get(0).getFname()+" "+all.get(0).getLname());
        uemail.setText(all.get(0).getEmail());


       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int id = all.get(0).getId();
               String str = String.valueOf(id);
               String str1 = all.get(0).getFname();
               String str2 = all.get(0).getLname();
               String str3 = email.getText().toString();
               String str7 = all.get(0).getPwd();
               String str4 = vehicleno.getText().toString();
               String str5 = phoneno.getText().toString();
               String str6 = address.getText().toString();
               photo.buildDrawingCache();
               Bitmap img = photo.getDrawingCache();
               Intent i = new Intent(UserProfile.this,UpdateUserProfile.class);
               i.putExtra("id",str);
               i.putExtra("fname",str1);
               i.putExtra("lname",str2);
               i.putExtra("email",str3);
               i.putExtra("vehicleno",str4);
               i.putExtra("phoneno",str5);
               i.putExtra("address",str6);
               i.putExtra("pwd",str7);
               i.putExtra("photo",img);
               startActivity(i);
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
            Intent intent = new Intent(UserProfile.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(UserProfile.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}