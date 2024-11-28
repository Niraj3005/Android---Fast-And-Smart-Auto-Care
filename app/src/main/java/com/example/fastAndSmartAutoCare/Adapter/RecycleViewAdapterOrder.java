package com.example.onroadremotevehicleassistantapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onroadremotevehicleassistantapp.Model.CartModel;
import com.example.onroadremotevehicleassistantapp.R;

import java.util.List;

public class RecycleViewAdapterOrder extends RecyclerView.Adapter<RecycleViewAdapterOrder.ViewHolder> {
    List<CartModel> cartList;
    Context context;
    private String stringVal;

    public RecycleViewAdapterOrder(List<CartModel> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_summary,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.pName.setText("Product Name : "+cartList.get(position).getProName());
        holder.pPrice.setText("\u20B9 : "+cartList.get(position).getProPrice());
        holder.pImg.setImageBitmap(cartList.get(position).getProImg());
        holder.qty.setText(String.valueOf("Quantity : "+cartList.get(position).getQuantity()));


           }

    @Override
    public int getItemCount() {
        return cartList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pName,pPrice,qty;
        ImageView pImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pName = itemView.findViewById(R.id.name_product);
            pPrice = itemView.findViewById(R.id.price_product);
            pImg = itemView.findViewById(R.id.img_product);
            qty = itemView.findViewById(R.id.qty_product);

        }
    }
}

