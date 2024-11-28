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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.Adapter.RecycleViewAdapterRequestMechanic;
import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;

import java.util.List;

public class ViewRequestMechanic extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecycleViewAdapterRequestMechanic mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView name,email;
    ImageView img;
    SearchView searchMechanic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_view_request_mechanic);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerviewrequestmechanic);

        img=findViewById(R.id.imageView17);
        name=findViewById(R.id.username6);
        email=findViewById(R.id.useremail6);
        searchMechanic=findViewById(R.id.search_requestmechanic);

        Intent intent = getIntent();
        String str = intent.getStringExtra("id");
        String sid = intent.getStringExtra("sid");
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


        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayout = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayout);
        DatabaseHelper dbhelper = new DatabaseHelper(ViewRequestMechanic.this);
        List<MechanicModel> all=dbhelper.viewAllMechanic();
        mAdapter = new RecycleViewAdapterRequestMechanic(all,ViewRequestMechanic.this,str1,sid);
        recyclerView.setAdapter(mAdapter);

        searchMechanic.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
            Intent intent = new Intent(ViewRequestMechanic.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(ViewRequestMechanic.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}
