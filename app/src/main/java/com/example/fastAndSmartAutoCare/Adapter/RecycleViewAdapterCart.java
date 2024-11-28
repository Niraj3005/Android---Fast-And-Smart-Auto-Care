package com.example.onroadremotevehicleassistantapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onroadremotevehicleassistantapp.Model.CartModel;
import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.R;

import java.util.List;

public class RecycleViewAdapterCart extends RecyclerView.Adapter<RecycleViewAdapterCart.ViewHolder> {
    List<CartModel> cartList;
    Context context;
    private String stringVal;

    public RecycleViewAdapterCart(List<CartModel> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.pid.setText(String.valueOf("Product ID : "+cartList.get(position).getProId()));
        holder.pName.setText("Product Name : "+cartList.get(position).getProName());
        holder.pPrice.setText("\u20B9 : "+cartList.get(position).getProPrice());
        holder.pImg.setImageBitmap(cartList.get(position).getProImg());
        holder.qty.setText(String.valueOf(""+cartList.get(position).getQuantity()));


        holder.minus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String str = (String.valueOf(""+cartList.get(position).getQuantity()));
                int i = Integer.parseInt(str);
                i--;
                stringVal = Integer.toString(i);
                int q = Integer.parseInt(stringVal);
                holder.qty.setText(stringVal);

                int cid=cartList.get(position).getCartId();

                DatabaseHelper db=new DatabaseHelper(view.getContext());

                boolean success = db.updateItem(cid,q);
                if(success == true) {
                    Toast.makeText(view.getContext(), "Cart Updated...", Toast.LENGTH_SHORT).show();
                    Intent intent = ((Activity) context).getIntent();
                    ((Activity) context).finish();
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(view.getContext(), "Cart Updation Failed...!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String str = (String.valueOf(""+cartList.get(position).getQuantity()));
                int i = Integer.parseInt(str);
                i++;
                stringVal = Integer.toString(i);
                int q = Integer.parseInt(stringVal);
                holder.qty.setText(stringVal);

                int cid=cartList.get(position).getCartId();

                DatabaseHelper db=new DatabaseHelper(view.getContext());

                boolean success = db.updateItem(cid,q);
                if(success == true) {
                    Toast.makeText(view.getContext(), "Cart Updated...", Toast.LENGTH_SHORT).show();
                    Intent intent = ((Activity) context).getIntent();
                    ((Activity) context).finish();
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(view.getContext(), "Cart Updation Failed...!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db=new DatabaseHelper(view.getContext());
                boolean success = db.deleteItem(cartList.get(position).getCartId());
                if (success == true) {
                    Toast.makeText(view.getContext(), "Item Removed...", Toast.LENGTH_SHORT).show();
                    Intent intent = ((Activity) context).getIntent();
                    ((Activity) context).finish();
                    context.startActivity(intent);
                } else {
                }
                cartList.remove(position);
                notifyDataSetChanged();
            }
        });



    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pName,pPrice,pid,qty;
        ImageView pImg;
        ImageButton plus,minus,remove;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pid = itemView.findViewById(R.id.id_pro);
            pName = itemView.findViewById(R.id.name_pro);
            pPrice = itemView.findViewById(R.id.price_pro);
            pImg = itemView.findViewById(R.id.img_pro);
            qty = itemView.findViewById(R.id.qty_pro);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            remove = itemView.findViewById(R.id.remove);

        }
    }
}
