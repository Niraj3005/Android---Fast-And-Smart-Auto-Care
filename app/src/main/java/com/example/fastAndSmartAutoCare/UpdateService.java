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

import com.example.onroadremotevehicleassistantapp.Model.ServiceModel;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateService extends AppCompatActivity {

    TextInputEditText ser_id,ser_name,ser_charges,ser_desc;
    ImageView ser_picture;
    TextView fullname,email;
    Button selectpic,ser_update;
    private static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_update_service);

        ser_id = (TextInputEditText) findViewById(R.id.update_sid);
        ser_name = (TextInputEditText) findViewById(R.id.update_sname);
        ser_charges = (TextInputEditText) findViewById(R.id.update_scharges);
        ser_desc = (TextInputEditText) findViewById(R.id.update_sdesc);
        ser_picture = (ImageView) findViewById(R.id.updatepicture);
        selectpic = (Button) findViewById(R.id.select_spicture);
        ser_update = (Button) findViewById(R.id.updateservice);
        fullname = findViewById(R.id.adminname11);
        email = findViewById(R.id.adminemail11);


        selectpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        Intent intent = getIntent();
        String str = intent.getStringExtra("id");
        String str1 = intent.getStringExtra("name");
        String str2 = intent.getStringExtra("charges");
        String str3 = intent.getStringExtra("desc");
        Bitmap str4 = intent.getParcelableExtra("pic");
        String name = intent.getStringExtra("adminname");
        String aemail = intent.getStringExtra("adminemail");
        fullname.setText(name);
        email.setText(aemail);
        ser_id.setText(str);
        ser_name.setText(str1);
        ser_charges.setText(str2);
        ser_desc.setText(str3);
        ser_picture.setImageBitmap(str4);

        ser_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceModel serviceModel;
                DatabaseHelper DB = new DatabaseHelper(UpdateService.this);

                String sid = ser_id.getText().toString();
                String snm = ser_name.getText().toString();
                String sch = ser_charges.getText().toString();
                String sdes = ser_desc.getText().toString();
                ser_picture.buildDrawingCache();
                Bitmap sp = ser_picture.getDrawingCache();

                int id = Integer.parseInt(String.valueOf(sid));
                serviceModel = new ServiceModel(id, snm, sch, sdes, sp);
                boolean success = DB.updateService(serviceModel);
                if (success == true) {
                    Toast.makeText(UpdateService.this, "Service Updated Successfully...!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateService.this,Services.class);
                    startActivity(i);

                } else {
                    Toast.makeText(UpdateService.this, "Service Updation Failed...!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
        void imageChooser() {

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
                        ser_picture.setImageURI(selectedImageUri);
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
            Intent intent = new Intent(UpdateService.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(UpdateService.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    }