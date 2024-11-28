package com.example.onroadremotevehicleassistantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onroadremotevehicleassistantapp.Adapter.RecycleViewAdminFeedback;
import com.example.onroadremotevehicleassistantapp.Adapter.SingleProductViewAdapter;
import com.example.onroadremotevehicleassistantapp.Model.FeedbackModel;
import com.example.onroadremotevehicleassistantapp.Model.ProductModel;

import java.util.List;

public class AdminViewFeedback extends AppCompatActivity {
    private TextView fullname,email;
    private SearchView searchfeedback;
    private RecyclerView recyclerView;
    private RecycleViewAdminFeedback mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_view_feedback);
        getSupportActionBar().setTitle(R.string.appname);

        fullname = findViewById(R.id.adminname8);
        email = findViewById(R.id.adminemail8);
        searchfeedback=findViewById(R.id.search_adminfeedback);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerviewadminfeedback);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String aemail = i.getStringExtra("email");
        fullname.setText(name);
        email.setText(aemail);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper dbhelper = new DatabaseHelper(AdminViewFeedback.this);
        List<FeedbackModel> all=dbhelper.viewAllFeedback();
        mAdapter = new RecycleViewAdminFeedback(all,AdminViewFeedback.this);
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
            Intent intent = new Intent(AdminViewFeedback.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(AdminViewFeedback.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}
