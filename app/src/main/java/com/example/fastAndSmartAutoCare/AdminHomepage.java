package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onroadremotevehicleassistantapp.Model.AdminModel;

import java.util.List;

public class AdminHomepage extends AppCompatActivity {
ImageView service,mechanic,product,report,feedback;
TextView fullname,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_homepage);

        fullname = findViewById(R.id.adminname);
        email = findViewById(R.id.adminemail);

        service=(ImageView) findViewById(R.id.user_request);
        mechanic=(ImageView) findViewById(R.id.user_order);
        product=(ImageView) findViewById(R.id.user_product);
        report=(ImageView) findViewById(R.id.report);
        feedback=(ImageView) findViewById(R.id.user_feedback);


        DatabaseHelper dbhelper = new DatabaseHelper(AdminHomepage.this);
        List<AdminModel> all=dbhelper.viewAdminDetails();
        String name = all.get(0).getAdminFname()+" "+all.get(0).getAdminLname();
        String aemail = all.get(0).getAdminEmail();
        fullname.setText(name);
        email.setText(aemail);


        service.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomepage.this, Services.class);
                i.putExtra("name",name);
                i.putExtra("email",aemail);
                startActivity(i);
            }
        });

        mechanic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomepage.this, ViewMechanic.class);
                i.putExtra("name",name);
                i.putExtra("email",aemail);
                startActivity(i);
            }
        });

        product.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomepage.this, ViewProducts.class);
                i.putExtra("name",name);
                i.putExtra("email",aemail);
                startActivity(i);
            }
        });

        report.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomepage.this, ReportDashboard.class);
                i.putExtra("name",name);
                i.putExtra("email",aemail);
                startActivity(i);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomepage.this, AdminViewFeedback.class);
                i.putExtra("name",name);
                i.putExtra("email",aemail);
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
            return true;
        }
        else if(id == R.id.item2){
            Intent intent1 = new Intent(AdminHomepage.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}