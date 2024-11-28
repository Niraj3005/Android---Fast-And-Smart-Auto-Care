package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.Adapter.RecycleViewAdapterCart;
import com.example.onroadremotevehicleassistantapp.Model.CartModel;

import java.util.List;

public class ViewCart extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView total;
    Button order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_view_cart);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        int uid = Integer.parseInt(id);
        String str1 = intent.getStringExtra("email");
        String str2 = intent.getStringExtra("name");
        Bitmap str3 = intent.getParcelableExtra("photo");

        DatabaseHelper db = new DatabaseHelper(ViewCart.this);
        order = findViewById(R.id.order);
        total = findViewById(R.id.total);

        String str = db.getSumValue(uid);
        total.setText("Total Price : \u20B9 "+str);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerviewcart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper dbhelper = new DatabaseHelper(ViewCart.this);
        List<CartModel> all=dbhelper.viewAllItems(uid);
        mAdapter = new RecycleViewAdapterCart(all,ViewCart.this);
        recyclerView.setAdapter(mAdapter);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewCart.this,Order.class);
                i.putExtra("id",id);
                i.putExtra("email",str1);
                i.putExtra("name",str2);
                i.putExtra("photo",str3);
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
            Intent intent = new Intent(ViewCart.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(ViewCart.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

}