package com.example.onroadremotevehicleassistantapp.Adapter;

import android.annotation.SuppressLint;
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

import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.Model.ProductModel;
import com.example.onroadremotevehicleassistantapp.R;
import com.example.onroadremotevehicleassistantapp.Model.ServiceModel;
import com.example.onroadremotevehicleassistantapp.UpdateService;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterService extends RecyclerView.Adapter<RecycleViewAdapterService.ViewHolder> implements Filterable {
    List<ServiceModel> serviceList;
    List<ServiceModel> serviceListFull;
    String name,email;
    Context context;

    public RecycleViewAdapterService(List<ServiceModel> serviceList,String name,String email, Context context) {
        this.serviceList = serviceList;
        this.name=name;
        this.email=email;
        this.context = context;
        serviceListFull = new ArrayList<>(serviceList);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_services,parent,false);
       ViewHolder holder= new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.sname.setText(serviceList.get(position).getSname());
        holder.scharges.setText("Charges :"+serviceList.get(position).getScharges());
        holder.spicture.setImageBitmap(serviceList.get(position).getSpicture());

        holder.supdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int sid=serviceList.get(position).getId();
                String str=String.valueOf(sid);
                String str1=serviceList.get(position).getSname().toString();
                String str2=serviceList.get(position).getScharges();
                String str3=serviceList.get(position).getSdesc().toString();
                holder.spicture.buildDrawingCache();
                Bitmap str4 = holder.spicture.getDrawingCache();

                Intent i = new Intent(view.getContext(), UpdateService.class);
                i.putExtra("id", str);
                i.putExtra("name", str1);
                i.putExtra("charges", str2);
                i.putExtra("desc", str3);
                i.putExtra("pic", str4);
                i.putExtra("adminname",name);
                i.putExtra("adminemail",email);
                context.startActivity(i);
            }
        });
        holder.sdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db=new DatabaseHelper(view.getContext());
                boolean success = db.deleteService(serviceList.get(position).getId());
                if (success == true) {
                    Toast.makeText(view.getContext(), "Service Deleted Successfully...!", Toast.LENGTH_SHORT).show();

                } else {
                }
                serviceList.remove(position);
                notifyDataSetChanged();
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
                    if (item.getSname().toLowerCase().contains(filterPattern) || item.getScharges().toLowerCase().contains(filterPattern) ) {
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
        TextView sname,scharges;
        ImageView spicture;
        Button supdate,sdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sname = itemView.findViewById(R.id.sname);
            scharges = itemView.findViewById(R.id.charges);
            spicture = itemView.findViewById(R.id.img);
            supdate = itemView.findViewById(R.id.update_service);
            sdelete = itemView.findViewById(R.id.buy);

        }
    }
}
