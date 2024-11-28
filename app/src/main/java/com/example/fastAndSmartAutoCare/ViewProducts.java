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

import com.example.onroadremotevehicleassistantapp.Adapter.SingleProductViewAdapter;
import com.example.onroadremotevehicleassistantapp.Model.ProductModel;

import java.util.List;

public class ViewProducts extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SingleProductViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView addnew,fullname,email;
    private SearchView searchProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_view_products);

        addnew = (Button) findViewById(R.id.addnewproduct);
        searchProduct=findViewById(R.id.search_adminproduct);
        fullname = findViewById(R.id.adminname3);
        email = findViewById(R.id.adminemail3);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String aemail = i.getStringExtra("email");
        fullname.setText(name);
        email.setText(aemail);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerviewproduct);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper dbhelper = new DatabaseHelper(ViewProducts.this);
        List<ProductModel> all=dbhelper.viewAllProduct();
        mAdapter = new SingleProductViewAdapter(all,name,aemail,ViewProducts.this);
        recyclerView.setAdapter(mAdapter);

        addnew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewProducts.this, AddProducts.class);
                i.putExtra("name",name);
                i.putExtra("email",aemail);
                startActivity(i);
            }
        });

        searchProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
            Intent intent = new Intent(ViewProducts.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(ViewProducts.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}