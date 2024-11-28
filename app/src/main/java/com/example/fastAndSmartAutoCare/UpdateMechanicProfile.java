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

import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class UpdateMechanicProfile extends AppCompatActivity {
    TextInputEditText fname,lname,uemail,phoneno,address;
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
        setContentView(R.layout.activity_update_mechanic_profile);
        fname = findViewById(R.id.updatemech1);
        lname = findViewById(R.id.updatemech2);
        uemail = findViewById(R.id.updatemech3);
        phoneno = findViewById(R.id.updatemech4);
        address = findViewById(R.id.updatemech5);
        photo = findViewById(R.id.mprofile_photo);
        name = findViewById(R.id.updatemechname);
        email = findViewById(R.id.updatemechemail);
        update = findViewById(R.id.updatemech);
        select = findViewById(R.id.mimgselect);


        Intent i = getIntent();
        String str = i.getStringExtra("id");
        String str1 = i.getStringExtra("fname");
        String str2 = i.getStringExtra("lname");
        String str3 = i.getStringExtra("email");
        String pwd = i.getStringExtra("pwd");
        String str5 = i.getStringExtra("phoneno");
        String str6 = i.getStringExtra("address");
        String regdate = i.getStringExtra("date");
        Bitmap ph = i.getParcelableExtra("photo");
        String str7 = i.getStringExtra("isactive");
        boolean b = Boolean.parseBoolean(str7);
        int isact=(b)?1:0;

        fname.setText(str1);
        lname.setText(str2);
        uemail.setText(str3);
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
                MechanicModel mechanicModel;
                DatabaseHelper DB = new DatabaseHelper(UpdateMechanicProfile.this);
                List<MechanicModel> all = DB.viewMechanic(str3);
                float rate = all.get(0).getMrating();

                int id = Integer.parseInt(str);
                String fnm = fname.getText().toString();
                String lnm = lname.getText().toString();
                String em = uemail.getText().toString();
                String phno = phoneno.getText().toString();
                String add = address.getText().toString();
                photo.buildDrawingCache();
                Bitmap sp = photo.getDrawingCache();

                mechanicModel = new MechanicModel(id,fnm,lnm,em,pwd,phno,add,regdate,sp,isact,rate);
                boolean success = DB.updateMechanic(mechanicModel);
                if (success == true) {
                    Toast.makeText(UpdateMechanicProfile.this, "Mechanic details Updated Successfully...!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateMechanicProfile.this,MechanicProfile.class);
                    startActivity(i);

                } else {
                    Toast.makeText(UpdateMechanicProfile.this, "Failed to Update Mechanic details...!", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(UpdateMechanicProfile.this, MechanicDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(UpdateMechanicProfile.this, MechanicLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}