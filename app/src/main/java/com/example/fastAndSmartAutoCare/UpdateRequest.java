package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.graphics.Bitmap;
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

import androidx.appcompat.app.AppCompatActivity;

import com.example.onroadremotevehicleassistantapp.Model.RequestModel;
import com.example.onroadremotevehicleassistantapp.Model.UserModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class UpdateRequest extends AppCompatActivity {
    MaterialSpinner vehicletype;
    TextInputEditText sname,vehicleno,scharges,location,mechanic,desc;
    Button makerequest,getmechanic;
    TextView name,email;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_update_request);

        img=findViewById(R.id.imageView19);
        name=findViewById(R.id.username8);
        email=findViewById(R.id.useremail8);

        vehicleno=findViewById(R.id.update_vehicleno);
        vehicletype=findViewById(R.id.update_vehicletype);
        location=findViewById(R.id.update_location);
        desc=findViewById(R.id.update_description);
        makerequest=findViewById(R.id.request_update);
        mechanic=findViewById(R.id.update_mechanic);
        getmechanic=findViewById(R.id.update_getmechanic);

        desc.setSelection(0);

        Intent intent = getIntent();
        String sid = intent.getStringExtra("id");
        String snm = intent.getStringExtra("name");
        String sch = intent.getStringExtra("charges");
        String uemail = intent.getStringExtra("useremail");
        String mechid = intent.getStringExtra("mechid");
        String fname = intent.getStringExtra("fname");
        String lname = intent.getStringExtra("lname");
        String mname = intent.getStringExtra("mname");
        String vhno = intent.getStringExtra("vehicleno");
        String loc = intent.getStringExtra("location");
        String des = intent.getStringExtra("description");
        String date = intent.getStringExtra("date");

        if((fname==null) && (lname==null))
        {
            mechanic.setText(mname);
        }else{
            mechanic.setText(fname+" "+lname);
        }

        DatabaseHelper dbhelper = new DatabaseHelper(UpdateRequest.this);
        List<UserModel> all=dbhelper.viewAllUser(uemail);
        String vno = all.get(0).getVehicleno();
        int userid = all.get(0).getId();
        String uid = String.valueOf(userid);
        String str2 = all.get(0).getFname()+""+all.get(0).getLname();
        Bitmap str3 = all.get(0).getPhoto();
        email.setText(uemail);
        name.setText(str2);

        if(str3 == null){

        }
        else{
            img.setImageBitmap(str3);
        }

        vehicleno.setText(vhno);
        location.setText(loc);
        desc.setText(des);

        getmechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateRequest.this,ViewRequestMechanic.class);
                i.putExtra("id",uid);
                i.putExtra("sid",sid);
                i.putExtra("email",uemail);
                i.putExtra("name",str2);
                i.putExtra("photo",str3);
                startActivity(i);
            }
        });

        makerequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestModel requestModel;
                DatabaseHelper DB = new DatabaseHelper(UpdateRequest.this);
                String mnm=mechanic.getText().toString();
                String vn=vehicleno.getText().toString();
                String vt=vehicletype.getSelectedItem().toString();
                String snm=sname.getText().toString();
                String sch=scharges.getText().toString();
                String loc=location.getText().toString();
                String des=desc.getText().toString();

                if(TextUtils.isEmpty(mnm) || TextUtils.isEmpty(vn) || TextUtils.isEmpty(vt) || TextUtils.isEmpty(snm) || TextUtils.isEmpty(sch) || TextUtils.isEmpty(loc) || TextUtils.isEmpty(des))
                    Toast.makeText(UpdateRequest.this,"Some fields are empty..",Toast.LENGTH_SHORT).show();
                else{
                    requestModel = new RequestModel(-1,Integer.parseInt(mechid), userid, vn, vt,snm,sch,loc,des,date,false);
                    boolean success = DB.updateRequest(requestModel);
                    if(success==true){
                        Toast.makeText(UpdateRequest.this, "Request Updated Successfully...!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(UpdateRequest.this,MakeServiceRequest.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(UpdateRequest.this, "Failed to update Request...!", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(UpdateRequest.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(UpdateRequest.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}