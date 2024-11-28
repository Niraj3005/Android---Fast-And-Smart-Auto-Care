package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onroadremotevehicleassistantapp.Model.ProductModel;
import com.google.android.material.textfield.TextInputEditText;

import fr.ganfra.materialspinner.MaterialSpinner;

public class AddProducts extends AppCompatActivity {
    TextInputEditText pname,price,pdesc,stockqty;
    ImageView pimg;
    MaterialSpinner pcategory,vehicletyp;
    Button addproduct,selectproduct;
    TextView fullname,email;

    private static final int SELECT_PICTURE = 1;

    private boolean shown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_add_products);

        pname=(TextInputEditText) findViewById(R.id.productName);
        pcategory=(MaterialSpinner) findViewById(R.id.productCategory);
        vehicletyp=(MaterialSpinner) findViewById(R.id.vehicleType);
        price=(TextInputEditText) findViewById(R.id.productPrice);
        pdesc=(TextInputEditText) findViewById(R.id.productDesc);
        stockqty=(TextInputEditText) findViewById(R.id.stockQty);
        pimg=(ImageView) findViewById(R.id.productPicture);
        addproduct = (Button) findViewById(R.id.addProduct);
        selectproduct = (Button) findViewById(R.id.select_productPicture);
        fullname = findViewById(R.id.adminname9);
        email = findViewById(R.id.adminemail9);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String aemail = i.getStringExtra("email");
        fullname.setText(name);
        email.setText(aemail);

        selectproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductModel productModel;
                DatabaseHelper DB = new DatabaseHelper(AddProducts.this);
                String pnm=pname.getText().toString();
                String pcate=pcategory.getSelectedItem().toString();
                String vhty=vehicletyp.getSelectedItem().toString();
                String pri=price.getText().toString();
                String pd=pdesc.getText().toString();
                String st=stockqty.getText().toString();
                int stqty = Integer.parseInt(st);
                pimg.buildDrawingCache();
                Bitmap pi = pimg.getDrawingCache();



                if(TextUtils.isEmpty(pnm) || TextUtils.isEmpty(pcate) || TextUtils.isEmpty(vhty) || TextUtils.isEmpty(pri) || TextUtils.isEmpty(pd) || TextUtils.isEmpty(st) || pimg.getDrawable()==null)
                    Toast.makeText(AddProducts.this,"All fields Required..",Toast.LENGTH_SHORT).show();
                else{
                    productModel = new ProductModel(-1, 3.5F,pnm,pcate,vhty,pri,pd,stqty,pi);
                        boolean success = DB.addProduct(productModel);
                        if(success==true){
                            Toast.makeText(AddProducts.this, "Product Added Successfully...!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(AddProducts.this,ViewProducts.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(AddProducts.this, "Adding Product Failed...!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        });

    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    pimg.setImageURI(selectedImageUri);
                }
            }
        }
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
            Intent intent = new Intent(AddProducts.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(AddProducts.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}


