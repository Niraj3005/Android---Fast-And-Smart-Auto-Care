package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class OrderStatus extends AppCompatActivity {
    View view_order_placed,view_order_confirmed,view_order_processed,view_order_pickup,view_order_cancel,con_divider,ready_divider,placed_divider,cancel_divider;
    ImageView img_orderconfirmed,orderprocessed,orderpickup,ordercancel;
    TextView textorderpickup,text_confirmed,textorderprocessed,textordercancel,date,id,address;
    EditText changeaddress;
    Button updateaddress,cancelorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_order_status);
        id=findViewById(R.id.textid);
        date=findViewById(R.id.textdate);
        address=findViewById(R.id.shippingaddress);
        changeaddress=findViewById(R.id.changeshippingaddress);
        updateaddress=findViewById(R.id.updateaddress);
        cancelorder=findViewById(R.id.cancelorder);

        view_order_placed=findViewById(R.id.view_order_placed);
        view_order_confirmed=findViewById(R.id.view_order_confirmed);
        view_order_processed=findViewById(R.id.view_order_processed);
        view_order_pickup=findViewById(R.id.view_order_pickup);
        view_order_cancel=findViewById(R.id.view_order_cancel);
        placed_divider=findViewById(R.id.placed_divider);
        con_divider=findViewById(R.id.con_divider);
        ready_divider=findViewById(R.id.ready_divider);
        cancel_divider=findViewById(R.id.cancel_divider);

        textorderpickup=findViewById(R.id.textorderpickup);
        text_confirmed=findViewById(R.id.text_confirmed);
        textorderprocessed=findViewById(R.id.textorderprocessed);
        textordercancel=findViewById(R.id.textordercancel);

        img_orderconfirmed=findViewById(R.id.img_orderconfirmed);
        orderprocessed=findViewById(R.id.orderprocessed);
        orderpickup=findViewById(R.id.orderpickup);
        ordercancel=findViewById(R.id.ordercancel);

        Intent intent=getIntent();
        String orderid=intent.getStringExtra("orderid");
        String orderdate=intent.getStringExtra("orderdate");
        String orderStatus=intent.getStringExtra("orderStatus");
        String add=intent.getStringExtra("address");

        int odid = Integer.parseInt(orderid);

        id.setText(orderid);
        date.setText(orderdate);
        address.setText("Shipping Address : "+add);
        getOrderStatus(orderStatus);

        updateaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper DB = new DatabaseHelper(OrderStatus.this);
                String changeadd = changeaddress.getText().toString();
                if(TextUtils.isEmpty(changeadd))
                    Toast.makeText(OrderStatus.this,"Field Required..",Toast.LENGTH_SHORT).show();
                else{
                    boolean success = DB.updateAddress(odid,changeadd);
                    if(success==true){
                        Toast.makeText(OrderStatus.this, "Address Changed Successfully...!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(OrderStatus.this,OrderStatus.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(OrderStatus.this, "Failed to change address...!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cancelorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper DB = new DatabaseHelper(OrderStatus.this);
                    boolean success = DB.updateStatus(odid,5);
                    if(success==true){
                        Toast.makeText(OrderStatus.this, "Order Cancelled Successfully...!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(OrderStatus.this,OrderStatus.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(OrderStatus.this, "Failed to cancel order...!", Toast.LENGTH_SHORT).show();
                    }
                }
        });





    }

    private void getOrderStatus(String orderStatus) {
        if (orderStatus.equals("1")){
            float alfa= (float) 0.5;
            setStatus(alfa);

        }else if (orderStatus.equals("2")){
            float alfa= (float) 1;
            setStatus1(alfa);



        }else if (orderStatus.equals("3")){
            float alfa= (float) 1;
            setStatus2(alfa);


        }else if (orderStatus.equals("4")){
            float alfa= (float) 1;
            setStatus3(alfa);


        }else if (orderStatus.equals("5")){
        float alfa= (float) 1;
        setStatus4(alfa);
    }
    }



    private void setStatus(float alfa) {
        float myf= (float) 0.5;
        view_order_placed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        placed_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        placed_divider.setAlpha(alfa);

        view_order_confirmed.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        con_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        img_orderconfirmed.setAlpha(alfa);
        text_confirmed.setAlpha(alfa);

        view_order_processed.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        orderprocessed.setAlpha(alfa);
        textorderprocessed.setAlpha(alfa);

        view_order_pickup.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        ready_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        orderpickup.setAlpha(alfa);
        textorderpickup.setAlpha(myf);

        view_order_cancel.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        cancel_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        ordercancel.setAlpha(alfa);
        textordercancel.setAlpha(myf);




    }

    private void setStatus1(float alfa) {
        float myf= (float) 0.5;
        view_order_placed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        placed_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));

        view_order_confirmed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        con_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        img_orderconfirmed.setAlpha(alfa);
        text_confirmed.setAlpha(alfa);

        view_order_processed.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        orderprocessed.setAlpha(myf);
        textorderprocessed.setAlpha(myf);

        ready_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        view_order_pickup.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        textorderpickup.setAlpha(myf);
        orderpickup.setAlpha(myf);
        view_order_pickup.setAlpha(myf);

        view_order_cancel.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        cancel_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        ordercancel.setAlpha(alfa);
        textordercancel.setAlpha(myf);


    }

    private void setStatus2(float alfa) {
        float myf= (float) 0.5;
        view_order_placed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        placed_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));

        view_order_confirmed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        con_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        orderprocessed.setAlpha(alfa);
        img_orderconfirmed.setAlpha(alfa);
        text_confirmed.setAlpha(alfa);


        view_order_processed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        textorderprocessed.setAlpha(alfa);

        view_order_pickup.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        ready_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        textorderpickup.setAlpha(myf);
        orderpickup.setAlpha(myf);


        view_order_cancel.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        cancel_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        ordercancel.setAlpha(alfa);
        textordercancel.setAlpha(myf);
    }

    private void setStatus3(float alfa) {
        view_order_placed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        placed_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));

        view_order_confirmed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        con_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        img_orderconfirmed.setAlpha(alfa);
        text_confirmed.setAlpha(alfa);

        view_order_processed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        orderprocessed.setAlpha(alfa);
        textorderprocessed.setAlpha(alfa);

        view_order_pickup.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        ready_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        textorderpickup.setAlpha(alfa);
        orderpickup.setAlpha(alfa);

        view_order_cancel.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        cancel_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        ordercancel.setAlpha(alfa);
    }

    private void setStatus4(float alfa) {
        view_order_placed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));

        view_order_confirmed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        con_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        img_orderconfirmed.setAlpha(alfa);
        text_confirmed.setAlpha(alfa);

        view_order_processed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        placed_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        orderprocessed.setAlpha(alfa);
        textorderprocessed.setAlpha(alfa);

        view_order_pickup.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        ready_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        textorderpickup.setAlpha(alfa);
        orderpickup.setAlpha(alfa);

        view_order_cancel.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        cancel_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        ordercancel.setAlpha(alfa);
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
            Intent intent = new Intent(OrderStatus.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(OrderStatus.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}