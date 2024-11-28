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

import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MechanicProfile extends AppCompatActivity {
    TextInputEditText name,email,phoneno,address;
    TextView mname,memail;
    Button update;
    ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_mechanic_profile);

        name = findViewById(R.id.mechtxt1);
        email = findViewById(R.id.mechtxt2);
        phoneno = findViewById(R.id.mechtxt3);
        address = findViewById(R.id.mechtxt4);
        mname = findViewById(R.id.mname);
        memail = findViewById(R.id.memail);
        update = findViewById(R.id.updatem);
        photo =  findViewById(R.id.mprofile_photo);

        Intent i =getIntent();
        String str = i.getStringExtra("email");
        DatabaseHelper dbhelper = new DatabaseHelper(MechanicProfile.this);
        List<MechanicModel> all=dbhelper.viewMechanic(str);
        name.setText(all.get(0).getMfname()+" "+all.get(0).getMlname());
        email.setText(all.get(0).getMemail());
        phoneno.setText(all.get(0).getMphoneno());
        address.setText(all.get(0).getMaddress());
        Bitmap pt = all.get(0).getMprofileimage();
        if(pt == null){

        }
        else{
            photo.setImageBitmap(all.get(0).getMprofileimage());
        }

        mname.setText(all.get(0).getMfname()+" "+all.get(0).getMlname());
        memail.setText(all.get(0).getMemail());


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = all.get(0).getMid();
                String str = String.valueOf(id);
                String str1 = all.get(0).getMfname();
                String str2 = all.get(0).getMlname();
                String str3 = email.getText().toString();
                String str4 = all.get(0).getMpwd();
                String str5 = phoneno.getText().toString();
                String str6 = address.getText().toString();
                String str8 = all.get(0).getRegDate();

                int b = all.get(0).getIsactive();
                String str7 = String.valueOf(b);

                photo.buildDrawingCache();
                Bitmap img = photo.getDrawingCache();
                Intent i = new Intent(MechanicProfile.this,UpdateMechanicProfile.class);
                i.putExtra("id",str);
                i.putExtra("fname",str1);
                i.putExtra("lname",str2);
                i.putExtra("email",str3);
                i.putExtra("pwd",str4);
                i.putExtra("phoneno",str5);
                i.putExtra("address",str6);
                i.putExtra("date",str8);
                i.putExtra("photo",img);
                i.putExtra("isactive",str7);

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
            Intent intent = new Intent(MechanicProfile.this, MechanicDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(MechanicProfile.this, MechanicLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}