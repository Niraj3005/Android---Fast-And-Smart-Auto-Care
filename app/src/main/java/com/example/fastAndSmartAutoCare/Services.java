package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.Adapter.RecycleViewAdapterService;
import com.example.onroadremotevehicleassistantapp.Model.ServiceModel;

import java.util.List;

public class Services extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecycleViewAdapterService mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Button addservice;
    TextView fullname,email;
    SearchView searchService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_services);

        recyclerView=(RecyclerView) findViewById(R.id.recyclervieworder);
        addservice=(Button) findViewById(R.id.addnewproduct);
        searchService=findViewById(R.id.search_adminservice);
        fullname = findViewById(R.id.adminname2);
        email = findViewById(R.id.adminemail2);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String aemail = i.getStringExtra("email");
        fullname.setText(name);
        email.setText(aemail);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper dbhelper = new DatabaseHelper(Services.this);
        List<ServiceModel> all=dbhelper.viewAllServices();
        mAdapter = new RecycleViewAdapterService(all,name,aemail,Services.this);
        recyclerView.setAdapter(mAdapter);
        addservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Services.this,AddServices.class);
                intent.putExtra("name",name);
                intent.putExtra("email",aemail);
                startActivity(intent);
            }
        });

        searchService.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
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
            Intent intent = new Intent(Services.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(Services.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}