package com.example.onroadremotevehicleassistantapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onroadremotevehicleassistantapp.Model.CartModel;
import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.Model.ProductModel;
import com.example.onroadremotevehicleassistantapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterProduct extends RecyclerView.Adapter<RecycleViewAdapterProduct.ViewHolder> implements Filterable {
    List<ProductModel> productList;
    List<ProductModel> productListFull;
    Context context;

    public RecycleViewAdapterProduct(List<ProductModel> productList, Context context) {
        this.productList = productList;
        this.context = context;
        productListFull = new ArrayList<>(productList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product,parent,false);
        ViewHolder holder= new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        float rate = productList.get(position).getProductRating();
        String prate = String.valueOf(rate);
        holder.productRating.setText(prate+" ");
        holder.productName.setText(productList.get(position).getProductName());
        holder.productPrice.setText("\u20B9 "+productList.get(position).getProductPrice());
        holder.productImg.setImageBitmap(productList.get(position).getProductImage());

        int qty=productList.get(position).getStockQuantity();

        Intent intent = ((Activity) context).getIntent();
        String str = intent.getStringExtra("id");
        int id = Integer.parseInt(str);

        if (qty == 0){
            holder.stock.setText("Out of Stock");
            holder.buy.setVisibility(View.INVISIBLE);
            holder.addtocart.setVisibility(View.INVISIBLE);

        }
        else{
            holder.stock.setText("InStock");
        }

        holder.buy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CartModel cartModel;
                DatabaseHelper DB = new DatabaseHelper(view.getContext());

                int sid=productList.get(position).getProductId();
                String str=String.valueOf(sid);
                String str1=productList.get(position).getProductName();
                String str3=productList.get(position).getProductPrice();
                holder.productImg.buildDrawingCache();
                Bitmap str5 = holder.productImg.getDrawingCache();

                cartModel = new CartModel(-1,id,sid,1,str1,str3,str5);
                boolean success = DB.addItem(cartModel);
                if(success==true){
                    Toast.makeText(view.getContext(), "Item Added to Cart...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "Item Failed to Add in Cart...", Toast.LENGTH_SHORT).show();
                }

            }
        });


        holder.addtocart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CartModel cartModel;
                DatabaseHelper DB = new DatabaseHelper(view.getContext());

                int sid=productList.get(position).getProductId();
                String str=String.valueOf(sid);
                String str1=productList.get(position).getProductName();
                String str3=productList.get(position).getProductPrice();
                holder.productImg.buildDrawingCache();
                Bitmap str5 = holder.productImg.getDrawingCache();


                cartModel = new CartModel(-1,id,sid,1,str1,str3,str5);
                boolean success = DB.addItem(cartModel);
                if(success==true){
                    Toast.makeText(view.getContext(), "Item Added to Cart...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(), "Item Failed to Add in Cart...", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        return productFilter;
    }
    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProductModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ProductModel item : productListFull) {
                    if (item.getProductName().toLowerCase().contains(filterPattern) || item.getProductCategory().toLowerCase().contains(filterPattern) ) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList.clear();
            productList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productPrice,productRating,stock;
        ImageView productImg;
        Button buy,addtocart;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.pname);
            productPrice = itemView.findViewById(R.id.price);
            stock = itemView.findViewById(R.id.stockstatus);
            productImg = itemView.findViewById(R.id.pimg);
            productRating = itemView.findViewById(R.id.prating);
            buy = itemView.findViewById(R.id.buy);
            addtocart = itemView.findViewById(R.id.btnaddcart);


        }
    }

}
