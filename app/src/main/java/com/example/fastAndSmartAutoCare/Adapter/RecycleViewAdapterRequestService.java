package com.example.onroadremotevehicleassistantapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.MakeServiceRequest;
import com.example.onroadremotevehicleassistantapp.Model.RequestModel;
import com.example.onroadremotevehicleassistantapp.Model.ServiceModel;
import com.example.onroadremotevehicleassistantapp.R;
import com.example.onroadremotevehicleassistantapp.UpdateService;
import com.example.onroadremotevehicleassistantapp.ViewRequestServices;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterRequestService extends RecyclerView.Adapter<RecycleViewAdapterRequestService.ViewHolder> implements Filterable {
    List<ServiceModel> serviceList;
    List<ServiceModel> serviceListFull;
    Context context;
    String uemail;

    public RecycleViewAdapterRequestService(List<ServiceModel> serviceList, Context context,String uemail) {
        this.serviceList = serviceList;
        this.context = context;
        this.uemail=uemail;
        serviceListFull = new ArrayList<>(serviceList);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_request_services,parent,false);
        ViewHolder holder= new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.sname.setText(serviceList.get(position).getSname());
        holder.spicture.setImageBitmap(serviceList.get(position).getSpicture());
        holder.spicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                int sid=serviceList.get(position).getId();
                String str=String.valueOf(sid);
                String str1=serviceList.get(position).getSname().toString();
                String str2=serviceList.get(position).getScharges();

                Intent i = new Intent(view.getContext(), MakeServiceRequest.class);
                i.putExtra("id", str);
                i.putExtra("name", str1);
                i.putExtra("charges", str2);
                i.putExtra("useremail",uemail);

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    @Override
    public Filter getFilter() {
        return serviceFilter;
    }

    private Filter serviceFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ServiceModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(serviceListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ServiceModel item : serviceListFull) {
                    if (item.getSname().toLowerCase().contains(filterPattern)) {
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
            serviceList.clear();
            serviceList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sname;
        ImageView spicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sname = itemView.findViewById(R.id.reqsname);
            spicture = itemView.findViewById(R.id.reqimg);

        }
    }
}
