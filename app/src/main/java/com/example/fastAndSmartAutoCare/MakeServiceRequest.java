package com.example.onroadremotevehicleassistantapp;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.onroadremotevehicleassistantapp.Adapter.RecycleViewAdapterRequestMechanic;
import com.example.onroadremotevehicleassistantapp.Model.RequestModel;
import com.example.onroadremotevehicleassistantapp.Model.UserModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class MakeServiceRequest extends AppCompatActivity {
MaterialSpinner vehicletype;
TextInputEditText sname,vehicleno,scharges,location,mechanic,desc;
Button getlocation,makerequest,getmechanic;
    TextView name,email;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_make_service_request);

        img=findViewById(R.id.imageView16);
        name=findViewById(R.id.username5);
        email=findViewById(R.id.useremail5);

        sname=findViewById(R.id.request_servicename);
        vehicleno=findViewById(R.id.request_vehicleno);
        vehicletype=findViewById(R.id.request_vehicletype);
        scharges=findViewById(R.id.request_charges);
        location=findViewById(R.id.request_location);
        getlocation=findViewById(R.id.request_getlocation);
        desc=findViewById(R.id.request_description);
        makerequest=findViewById(R.id.request_make);
        mechanic=findViewById(R.id.request_mechanic);
        getmechanic=findViewById(R.id.request_getmechanic);

        desc.setSelection(0);

        Intent intent = getIntent();
        String sid = intent.getStringExtra("id");
        String snm = intent.getStringExtra("name");
        String sch = intent.getStringExtra("charges");
        String uemail = intent.getStringExtra("useremail");
        String mechid = intent.getStringExtra("mechid");
        String fname = intent.getStringExtra("fname");
        String lname = intent.getStringExtra("lname");
        String latitude = intent.getStringExtra("latitude");
        String longitude = intent.getStringExtra("longitude");

        if((fname==null) && (lname==null))
        {
            mechanic.setText("");
        }else{
            mechanic.setText(fname+" "+lname);
        }
        if((latitude==null) && (longitude==null))
        {
            location.setText("");
        }else{
            location.setText(latitude+","+longitude);
        }

        DatabaseHelper dbhelper = new DatabaseHelper(MakeServiceRequest.this);
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

        vehicleno.setText(vno);
        sname.setText(snm);
        scharges.setText(sch);

        getmechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MakeServiceRequest.this,ViewRequestMechanic.class);
                i.putExtra("id",uid);
                i.putExtra("sid",sid);
                System.out.println(sid);
                i.putExtra("email",uemail);
                i.putExtra("name",str2);
                i.putExtra("photo",str3);
                startActivity(i);
            }
        });

        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MakeServiceRequest.this,MapsActivity.class);
                i.putExtra("id",sid);
                i.putExtra("name",snm);
                i.putExtra("charges",sch);
                i.putExtra("useremail",uemail);
                i.putExtra("mechid",mechid);
                i.putExtra("fname",fname);
                i.putExtra("lname",lname);
                startActivity(i);
            }
        });

        makerequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String currentDateandTime = sdf.format(new Date());

                RequestModel requestModel;
                DatabaseHelper DB = new DatabaseHelper(MakeServiceRequest.this);
                String mnm=mechanic.getText().toString();
                String vn=vehicleno.getText().toString();
                String vt= (String) vehicletype.getSelectedItem();
                String snm=sname.getText().toString();
                String sch=scharges.getText().toString();
                String loc=location.getText().toString();
                String des=desc.getText().toString();

                if(TextUtils.isEmpty(mnm) || TextUtils.isEmpty(vn) || TextUtils.isEmpty(vt) || TextUtils.isEmpty(snm) || TextUtils.isEmpty(sch) || TextUtils.isEmpty(loc) || TextUtils.isEmpty(des))
                    Toast.makeText(MakeServiceRequest.this,"Some fields are empty..",Toast.LENGTH_SHORT).show();
                else{
                    requestModel = new RequestModel(-1,Integer.parseInt(mechid), userid, vn, vt,snm,sch,loc,des,currentDateandTime,false);
                            boolean success = DB.makeRequest(requestModel);
                            if(success==true){
                                Intent i = new Intent(MakeServiceRequest.this,UserDashboard.class);
                                startActivity(i);
                                Toast.makeText(MakeServiceRequest.this, "Request Made Successfully...!", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(MakeServiceRequest.this, "Request Failed...!", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(MakeServiceRequest.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(MakeServiceRequest.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}