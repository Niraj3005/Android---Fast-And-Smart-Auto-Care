package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onroadremotevehicleassistantapp.Model.UserModel;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateUserProfile extends AppCompatActivity {
TextInputEditText fname,lname,uemail,vehicleno,phoneno,address;
ImageView photo,select;
TextView name,email;
Button update;

    private static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_update_user_profile);

        fname = findViewById(R.id.updateuser1);
        lname = findViewById(R.id.updateuser2);
        uemail = findViewById(R.id.updateuser3);
        vehicleno = findViewById(R.id.updateuser4);
        phoneno = findViewById(R.id.updateuser5);
        address = findViewById(R.id.updateuser6);
        photo = findViewById(R.id.mprofile_photo);
        name = findViewById(R.id.updateusername);
        email = findViewById(R.id.updateuseremail);
        update = findViewById(R.id.updateuser);
        select = findViewById(R.id.mimgselect);


        Intent i = getIntent();
        String str = i.getStringExtra("id");
        String str1 = i.getStringExtra("fname");
        String str2 = i.getStringExtra("lname");
        String str3 = i.getStringExtra("email");
        String str4 = i.getStringExtra("vehicleno");
        String str5 = i.getStringExtra("phoneno");
        String str6 = i.getStringExtra("address");
        String pwd = i.getStringExtra("pwd");
        Bitmap ph = i.getParcelableExtra("photo");


        fname.setText(str1);
        lname.setText(str2);
        uemail.setText(str3);
        vehicleno.setText(str4);
        phoneno.setText(str5);
        address.setText(str6);
        name.setText(str1+" "+str2);
        email.setText(str3);
        photo.setImageBitmap(ph);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel userModel;
                DatabaseHelper DB = new DatabaseHelper(UpdateUserProfile.this);

                int id = Integer.parseInt(str);
                String fnm = fname.getText().toString();
                String lnm = lname.getText().toString();
                String em = uemail.getText().toString();
                String vno = vehicleno.getText().toString();
                String phno = phoneno.getText().toString();
                String add = address.getText().toString();
                photo.buildDrawingCache();
                Bitmap sp = photo.getDrawingCache();

                userModel = new UserModel(id,fnm,lnm,em,pwd,vno,phno,add,sp);
                boolean success = DB.updateUser(userModel);
                if (success == true) {
                    Toast.makeText(UpdateUserProfile.this, "User details Updated Successfully...!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateUserProfile.this, UserProfile.class);
                    startActivity(i);

                } else {
                    Toast.makeText(UpdateUserProfile.this, "Failed to Update Mechanic details...!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
        void imageChooser() {

            // create an instance of the
            // intent of the type image
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
        }

        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK) {

                if (requestCode == SELECT_PICTURE) {
                    Uri selectedImageUri = data.getData();
                    if (null != selectedImageUri) {
                        photo.setImageURI(selectedImageUri);
                    }
                }
            }
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
            Intent intent = new Intent(UpdateUserProfile.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(UpdateUserProfile.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    }