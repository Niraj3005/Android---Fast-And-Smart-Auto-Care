package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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

import androidx.appcompat.app.AppCompatActivity;

import com.example.onroadremotevehicleassistantapp.Model.ProductModel;
import com.google.android.material.textfield.TextInputEditText;

import fr.ganfra.materialspinner.MaterialSpinner;

public class UpdateProducts extends AppCompatActivity {
    TextInputEditText pid,pname,price,pdesc,stockqty;
    ImageView pimg;
    TextView fullname,email;
    MaterialSpinner pcategory,vehicletyp;
    Button updateproduct,selectproduct;


    private static final int SELECT_PICTURE = 1;

    private boolean shown = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_update_products);

        pid=(TextInputEditText) findViewById(R.id.productId);
        pname=(TextInputEditText) findViewById(R.id.productName);
        pcategory=(MaterialSpinner) findViewById(R.id.productCategory);
        vehicletyp=(MaterialSpinner) findViewById(R.id.vehicleType);
        price=(TextInputEditText) findViewById(R.id.productPrice);
        pdesc=(TextInputEditText) findViewById(R.id.productDesc);
        stockqty=(TextInputEditText) findViewById(R.id.stockQty1);
        pimg=(ImageView) findViewById(R.id.productPicture);
        updateproduct = (Button) findViewById(R.id.addProduct);
        selectproduct = (Button) findViewById(R.id.select_productPicture);
        fullname = findViewById(R.id.adminname12);
        email = findViewById(R.id.adminemail12);

        selectproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        Intent intent = getIntent();
        String str = intent.getStringExtra("id");
        String str1 = intent.getStringExtra("name");
        String str3 = intent.getStringExtra("price");
        String str4 = intent.getStringExtra("desc");
        String str5 = intent.getStringExtra("qty");
        Bitmap str6 = intent.getParcelableExtra("pic");
        String name = intent.getStringExtra("adminname");
        String aemail = intent.getStringExtra("adminemail");
        fullname.setText(name);
        email.setText(aemail);
        pid.setText(str);
        pname.setText(str1);
        price.setText(str3);
        pdesc.setText(str4);
        stockqty.setText(str5);
        pimg.setImageBitmap(str6);

        updateproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductModel productModel;
                DatabaseHelper DB = new DatabaseHelper(UpdateProducts.this);
                String proid=pid.getText().toString();
                int id = Integer.parseInt(String.valueOf(proid));
                String pnm=pname.getText().toString();
                String pcate=pcategory.getSelectedItem().toString();
                String vhty=vehicletyp.getSelectedItem().toString();
                String pri=price.getText().toString();
                String pd=pdesc.getText().toString();
                String st=stockqty.getText().toString();
                int stqty = Integer.parseInt(st);
                pimg.buildDrawingCache();
                Bitmap pi = pimg.getDrawingCache();



                    productModel = new ProductModel(id, 3.5F,pnm,pcate,vhty,pri,pd,stqty,pi);
                    boolean success = DB.updateProduct(productModel);
                    if(success==true){
                        Toast.makeText(UpdateProducts.this, "Product Updated Successfully...!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(UpdateProducts.this,ViewProducts.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(UpdateProducts.this, "Product Updation Failed...!", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(UpdateProducts.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(UpdateProducts.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}