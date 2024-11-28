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

import com.example.onroadremotevehicleassistantapp.Adapter.RecycleViewAdapterViewUserRequest;
import com.example.onroadremotevehicleassistantapp.Model.RequestModel;

import java.util.List;

public class ViewUserRequest extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecycleViewAdapterViewUserRequest mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<RequestModel> requestModalArrayList;

    private TextView name,email;
    private ImageView img;
    private SearchView searchuserrequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_view_user_request);

        img=findViewById(R.id.imageView18);
        name=findViewById(R.id.username7);
        email=findViewById(R.id.useremail7);
        searchuserrequest = findViewById(R.id.search_userrequest);

        Intent intent = getIntent();
        String str = intent.getStringExtra("id");
        int userid = Integer.parseInt(str);
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

        recyclerView=(RecyclerView) findViewById(R.id.recyclerviewuserrequest);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper dbhelper = new DatabaseHelper(ViewUserRequest.this);
        List<RequestModel> all=dbhelper.viewUserRequest(userid);
        mAdapter = new RecycleViewAdapterViewUserRequest(all, ViewUserRequest.this,str1);
        recyclerView.setAdapter(mAdapter);

        searchuserrequest.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
            Intent intent = new Intent(ViewUserRequest.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(ViewUserRequest.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }



}