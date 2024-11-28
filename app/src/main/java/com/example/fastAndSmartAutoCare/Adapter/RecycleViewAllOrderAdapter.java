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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.Model.OrderModel;
import com.example.onroadremotevehicleassistantapp.R;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class RecycleViewAllOrderAdapter extends RecyclerView.Adapter<RecycleViewAllOrderAdapter.ViewHolder> {
        List<OrderModel> orderList;
        Context context;

public RecycleViewAllOrderAdapter(List<OrderModel> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_order,parent,false);
        ViewHolder holder= new ViewHolder(view);

        return holder;
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.orderid.setText("Order ID : "+orderList.get(position).getOrderId());
        holder.orderdate.setText("Order on "+orderList.get(position).getOrderDate());
        holder.productname.setText(orderList.get(position).getOrderproductName());
        holder.productImg.setImageBitmap(orderList.get(position).getOrderproductPhoto());
        int status = orderList.get(position).getOrderStatus();
       holder.status.setSelection(status);


    holder.updatestatus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int odid = orderList.get(position).getOrderId();
            int str1 = holder.status.getSelectedItemPosition();
            DatabaseHelper DB = new DatabaseHelper(view.getContext());
            boolean success = DB.updateStatus(odid,str1);
            if(success==true){
                Toast.makeText(view.getContext(), "Order Status Updated Successfully...!", Toast.LENGTH_SHORT).show();
                Intent intent = ((Activity) context).getIntent();
                ((Activity) context).finish();
                context.startActivity(intent);
            }else{
                Toast.makeText(view.getContext(), "Failed to update order status...!", Toast.LENGTH_SHORT).show();
            }
        }
    });

        }

@Override
public int getItemCount() {
        return orderList.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView orderdate,productname,orderid;
    ImageView productImg;
    MaterialSpinner status;
    Button updatestatus;




    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        orderid = itemView.findViewById(R.id.adminorder_id);
        orderdate = itemView.findViewById(R.id.adminorder_date);
        productname = itemView.findViewById(R.id.adminorder_name);
        productImg = itemView.findViewById(R.id.adminorder_img);
        status = itemView.findViewById(R.id.adminorder_status);
        updatestatus = itemView.findViewById(R.id.adminorder_update);

    }
}

}

