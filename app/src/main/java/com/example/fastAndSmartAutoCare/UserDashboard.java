package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onroadremotevehicleassistantapp.Model.UserModel;

import java.util.List;

public class UserDashboard extends AppCompatActivity {
TextView email,name;
ImageView profile,request,viewrequest,order,product,feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_user_dashboard);
        email = findViewById(R.id.useremail);
        name = findViewById(R.id.username);
        profile = findViewById(R.id.imageView8);
        request = findViewById(R.id.user_request);
        order= findViewById(R.id.user_order);
        product = findViewById(R.id.user_product);
        feedback = findViewById(R.id.user_feedback);
        viewrequest = findViewById(R.id.viewuser_request);


        Intent i = getIntent();
        String str = i.getStringExtra("email");
        email.setText(str);

        DatabaseHelper dbhelper = new DatabaseHelper(UserDashboard.this);
        List<UserModel> all=dbhelper.viewAllUser(str);
        int userid = all.get(0).getId();
        String usid = String.valueOf(userid);
        name.setText(all.get(0).getFname()+" "+all.get(0).getLname());
        String str1 = all.get(0).getFname()+" "+all.get(0).getLname();
        Bitmap pt = all.get(0).getPhoto();
        if(pt == null){

        }
        else{
            profile.setImageBitmap(all.get(0).getPhoto());
        }

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDashboard.this, UserProfile.class);
                i.putExtra("email",str);
                startActivity(i);
            }
        });
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDashboard.this,Products.class);
                i.putExtra("id",usid);
                i.putExtra("email",str);
                i.putExtra("name",str1);
                i.putExtra("photo",pt);
                startActivity(i);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDashboard.this,ViewOrder.class);
                i.putExtra("id",usid);
                i.putExtra("email",str);
                i.putExtra("name",str1);
                i.putExtra("photo",pt);
                startActivity(i);
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDashboard.this,ViewRequestServices.class);
                i.putExtra("id",usid);
                i.putExtra("email",str);
                i.putExtra("name",str1);
                i.putExtra("photo",pt);
                startActivity(i);

            }
        });
        viewrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDashboard.this, ViewUserRequest.class);
                i.putExtra("id",usid);
                i.putExtra("email",str);
                i.putExtra("name",str1);
                i.putExtra("photo",pt);
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
            Intent intent = new Intent(UserDashboard.this, RVA_HomePage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(UserDashboard.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}