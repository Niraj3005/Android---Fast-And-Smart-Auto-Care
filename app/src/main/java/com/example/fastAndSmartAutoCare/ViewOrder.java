package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.Adapter.RecycleViewOrderAdapter;
import com.example.onroadremotevehicleassistantapp.Model.OrderModel;

import java.util.List;

public class ViewOrder extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecycleViewOrderAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView name,email;
    ImageView img;
    private SearchView searchOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_view_order);

        img=findViewById(R.id.imageView14);
        name=findViewById(R.id.username3);
        email=findViewById(R.id.useremail3);
        searchOrder=findViewById(R.id.search_userorder);


        Intent intent = getIntent();
        String st = intent.getStringExtra("id");
        int userid = Integer.parseInt(st);
        String str1 = intent.getStringExtra("email");
        String str2 = intent.getStringExtra("name");
        Bitmap str3 = intent.getParcelableExtra("photo");
        email.setText(str1);
        name.setText(str2);

        if(str3 == null){

        }
        else{
            img.setImageBitmap(str3);
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewuserorder);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper dbhelper = new DatabaseHelper(ViewOrder.this);
        List<OrderModel> all = dbhelper.viewUserOrders(userid);
        mAdapter = new RecycleViewOrderAdapter(all, ViewOrder.this);
        recyclerView.setAdapter(mAdapter);

        searchOrder.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
            Intent intent = new Intent(ViewOrder.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(ViewOrder.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

}