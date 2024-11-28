package com.example.onroadremotevehicleassistantapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.Adapter.RecycleViewAdapterOrder;
import com.example.onroadremotevehicleassistantapp.Model.CartModel;
import com.example.onroadremotevehicleassistantapp.Model.OrderModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class Order extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView totalprice,name,email;
    Button placeorder;
    ImageView img;
    TextInputEditText address;
    MaterialSpinner mop;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_order);


        img=findViewById(R.id.imageView13);
        name=findViewById(R.id.username2);
        email=findViewById(R.id.useremail2);
        address=findViewById(R.id.deliveryaddress);
        mop=findViewById(R.id.modeofpayment);
        placeorder = findViewById(R.id.confirmorder);
        totalprice = findViewById(R.id.totalprice);

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
        DatabaseHelper db = new DatabaseHelper(Order.this);
        String price = db.getSumValue(userid);
        totalprice.setText("Total Price : \u20B9 " + price);


        recyclerView = (RecyclerView) findViewById(R.id.recyclervieworder);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DatabaseHelper dbhelper = new DatabaseHelper(Order.this);
        List<CartModel> all = dbhelper.viewItems(userid);
        mAdapter = new RecycleViewAdapterOrder(all, Order.this);
        recyclerView.setAdapter(mAdapter);

        int size = all.size();



        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dadd = address.getText().toString();
                String payment = mop.getSelectedItem().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String currentDateandTime = sdf.format(new Date());

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Order.this);
                alertDialog.setTitle("Order Confirmation");

                alertDialog.setMessage("Are you sure you want to Confirm order....");
                alertDialog.setIcon(R.drawable.orderconfirm);
                alertDialog.setPositiveButton("Confirm Order",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                OrderModel orderModel;
                                boolean success = false;
                                DatabaseHelper DB = new DatabaseHelper(Order.this);
                                for (int i=0;i<=size;i++) {
                                    int cartid = all.get(i).getCartId();
                                    int proid = all.get(i).getProId();
                                    String price = all.get(i).getProPrice();
                                    int pprice = Integer.parseInt(price);
                                    String proname = all.get(i).getProName();
                                    int qt = all.get(i).getQuantity();
                                     int pri =qt*pprice;
                                     String product_price = String.valueOf(pri);
                                     Bitmap pimg = all.get(i).getProImg();

                                    orderModel = new OrderModel(-1,cartid,userid,proid, currentDateandTime, product_price,proname, dadd, payment, 1,qt,pimg,0.0F);
                                     success = DB.addOrder(orderModel);
                                }
                                if(success==true){
                                    Toast.makeText(Order.this, "Order placed Successfully...!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Order.this,UserDashboard.class);
                                    startActivity(i);
                                }else{
                                    Toast.makeText(Order.this, "Unable to place order...!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                alertDialog.setNegativeButton("Discard",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();

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
            Intent intent = new Intent(Order.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(Order.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}