package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onroadremotevehicleassistantapp.Model.ServiceModel;

public class AddServices extends AppCompatActivity {
    TextInputEditText sname,sdesc,scharges;
    ImageView spicture;
    TextView fullname,email;
    Button addser,select;

    private static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_add_services);

        sname=(TextInputEditText) findViewById(R.id.update_sname);
        sdesc=(TextInputEditText) findViewById(R.id.update_sdesc);
        scharges=(TextInputEditText) findViewById(R.id.update_scharges);
        spicture=(ImageView) findViewById(R.id.updatepicture);
        addser = (Button) findViewById(R.id.updateservice);
        select = (Button) findViewById(R.id.select_spicture);
        fullname = findViewById(R.id.adminname10);
        email = findViewById(R.id.adminemail10);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String aemail = i.getStringExtra("email");
        fullname.setText(name);
        email.setText(aemail);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        addser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceModel serviceModel;
                DatabaseHelper DB = new DatabaseHelper(AddServices.this);
                String snm=sname.getText().toString();
                String sd=sdesc.getText().toString();
                String sc=scharges.getText().toString();
                spicture.buildDrawingCache();
                Bitmap sp = spicture.getDrawingCache();



                if(TextUtils.isEmpty(snm) || TextUtils.isEmpty(sd) || TextUtils.isEmpty(sc) || spicture.getDrawable()==null)
                    Toast.makeText(AddServices.this,"All fields Required..",Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkservice = DB.checkservice(snm);
                    if(checkservice==false){
                        serviceModel = new ServiceModel(-1, snm,sd,sc,sp);
                            boolean success = DB.addService(serviceModel);
                            if(success==true){
                                Toast.makeText(AddServices.this, "Service Added Successfully...!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(AddServices.this,Services.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(AddServices.this, "Adding Service Failed...!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(AddServices.this, "Service Already Exists...!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddServices.this,Services.class);
                        startActivity(i);
                        }
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
                    spicture.setImageURI(selectedImageUri);
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
            Intent intent = new Intent(AddServices.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(AddServices.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}