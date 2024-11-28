package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportDashboard extends AppCompatActivity {
    TextView fullname,email;
    ImageView orderreport,mechanicreport,requestreport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_report_dashboard);
        fullname = findViewById(R.id.adminname4);
        email = findViewById(R.id.adminemail4);
        orderreport = findViewById(R.id.request_orderreport);
        mechanicreport = findViewById(R.id.request_mechanicreport);
        requestreport = findViewById(R.id.request_requestreport);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String aemail = i.getStringExtra("email");
        fullname.setText(name);
        email.setText(aemail);

        orderreport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ReportDashboard.this, OrderReport.class);
                i.putExtra("name",name);
                i.putExtra("email",aemail);
                startActivity(i);
            }
        });
        mechanicreport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(ReportDashboard.this, MechanicReport.class);
                i.putExtra("name",name);
                i.putExtra("email",aemail);
                startActivity(i);
            }
        });
        requestreport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ReportDashboard.this, RequestReport.class);
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
            Intent intent = new Intent(ReportDashboard.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(ReportDashboard.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}