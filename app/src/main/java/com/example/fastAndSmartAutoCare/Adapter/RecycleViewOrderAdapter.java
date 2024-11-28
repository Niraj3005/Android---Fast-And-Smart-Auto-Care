package com.example.onroadremotevehicleassistantapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.Model.OrderModel;
import com.example.onroadremotevehicleassistantapp.Model.ProductModel;
import com.example.onroadremotevehicleassistantapp.Order;
import com.example.onroadremotevehicleassistantapp.OrderStatus;
import com.example.onroadremotevehicleassistantapp.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecycleViewOrderAdapter extends RecyclerView.Adapter<RecycleViewOrderAdapter.ViewHolder> implements Filterable {
    List<OrderModel> orderList;
    List<OrderModel> orderListFull;
    Context context;

    public RecycleViewOrderAdapter(List<OrderModel> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
        orderListFull = new ArrayList<>(orderList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_single_order,parent,false);
        ViewHolder holder= new ViewHolder(view);

        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.orderdate.setText("Order on "+orderList.get(position).getOrderDate());
        holder.productname.setText(orderList.get(position).getOrderproductName());
        holder.productImg.setImageBitmap(orderList.get(position).getOrderproductPhoto());
        holder.productRating.setRating(orderList.get(position).getOrderproductRating());

        holder.continueorder.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                int status = orderList.get(position).getOrderStatus();
                String orderstatus=String.valueOf(status);
                int id=orderList.get(position).getOrderId();
                String orderid=String.valueOf(id);
                String orderdate=orderList.get(position).getOrderDate();
                String address=orderList.get(position).getOrderDeliveryAddress();
                Intent intent = new Intent(context.getApplicationContext(), OrderStatus.class);
                intent.putExtra("orderid",orderid);
                intent.putExtra("orderdate",orderdate);
                intent.putExtra("orderStatus",orderstatus);
                intent.putExtra("address",address);
                context.startActivity(intent);
            }

        });


        LayerDrawable stars = (LayerDrawable)  holder.productRating.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);


        holder.productRating.setEnabled(true);
        holder.productRating.setClickable(true);
         holder.productRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                DatabaseHelper DB = new DatabaseHelper(context.getApplicationContext());

                int orderId = orderList.get(position).getOrderId();
                boolean success1 = DB.updateOrderProductRating(orderId,rating);

                int proid = orderList.get(position).getOrderproId();
                float allrating = DB.viewProductRating(proid);
                float avgrating = (float) ((allrating+rating)/2.0);
                DecimalFormat value = new DecimalFormat("#.#");
                float f1 = Float.parseFloat(value.format(avgrating));

                boolean success = DB.updateProductRating(proid,f1);

                if(success==true && success1==true){
                    Toast.makeText(context.getApplicationContext(), "You Rated :  "+rating, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context.getApplicationContext(), "Unable to Rate..."  , Toast.LENGTH_LONG).show();
                }

            }});
        holder.productRating.refreshDrawableState();

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    @Override
    public Filter getFilter() {
        return orderFilter;
    }


    private Filter orderFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<OrderModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(orderListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (OrderModel item : orderListFull) {
                    if (item.getOrderproductName().toLowerCase().contains(filterPattern) || item.getOrderDate().toLowerCase().contains(filterPattern) ) {
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
            orderList.clear();
            orderList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderdate,productname,next;
        ImageView productImg;
        RatingBar productRating;
        RelativeLayout continueorder;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderdate = itemView.findViewById(R.id.vieworder_date);
            productname = itemView.findViewById(R.id.vieworder_name);
            productImg = itemView.findViewById(R.id.vieworder_img);
            productRating = itemView.findViewById(R.id.vieworder_rating);
            next = itemView.findViewById(R.id.next);
            continueorder = itemView.findViewById(R.id.continueorder);

        }
    }

}

