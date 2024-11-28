package com.example.onroadremotevehicleassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.Adapter.RecycleViewAllOrderAdapter;
import com.example.onroadremotevehicleassistantapp.Model.OrderModel;

import java.util.List;

public class AdminViewAllOrders extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_admin_view_all_orders);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewallorder1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper dbhelper = new DatabaseHelper(AdminViewAllOrders.this);
        List<OrderModel> all = dbhelper.viewAllOrders();
        mAdapter = new RecycleViewAllOrderAdapter(all, AdminViewAllOrders.this);
        recyclerView.setAdapter(mAdapter);
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
            Intent intent = new Intent(AdminViewAllOrders.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(AdminViewAllOrders.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}