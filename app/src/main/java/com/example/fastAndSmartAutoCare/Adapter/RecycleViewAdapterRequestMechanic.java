package com.example.onroadremotevehicleassistantapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.MakeServiceRequest;
import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.example.onroadremotevehicleassistantapp.Model.ServiceModel;
import com.example.onroadremotevehicleassistantapp.R;
import com.example.onroadremotevehicleassistantapp.ViewRequestMechanic;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterRequestMechanic extends RecyclerView.Adapter<RecycleViewAdapterRequestMechanic.ViewHolder> implements Filterable {
    List<MechanicModel> mechanicList;
    List<MechanicModel> mechanicListFull;
    Context context;
    String uemail;
    String sid;


    public RecycleViewAdapterRequestMechanic(List<MechanicModel> mechanicList, Context context, String uemail, String sid)
     {
        this.mechanicList = mechanicList;
        this.context = context;
        this.uemail=uemail;
         this.sid=sid;
         mechanicListFull = new ArrayList<>(mechanicList);


     }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_request_mechanic,parent,false);
        ViewHolder holder= new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(mechanicList.get(position).getMfname()+" "+mechanicList.get(position).getMlname());
        holder.address.setText(mechanicList.get(position).getMaddress());
        holder.rating.setText(" "+mechanicList.get(position).getMrating());
        Bitmap pt=mechanicList.get(position).getMprofileimage();
        if(pt == null){

        }
        else{
            holder.picture.setImageBitmap(mechanicList.get(position).getMprofileimage());
        }

        holder.picture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                int str=mechanicList.get(position).getMid();
                String mid=String.valueOf(str);
                String mfnm=mechanicList.get(position).getMfname().toString();
                String mlnm=mechanicList.get(position).getMlname().toString();

                DatabaseHelper dbhelper = new DatabaseHelper(context.getApplicationContext());
                List<ServiceModel> all=dbhelper.viewServices(Integer.parseInt(sid));
                int str1 = all.get(0).getId();
                String sid = String.valueOf(str1);
                String sname = all.get(0).getSname();
                String sch = all.get(0).getScharges();

                Intent i = new Intent(view.getContext(), MakeServiceRequest.class);
                i.putExtra("id", sid);
                i.putExtra("name", sname);
                i.putExtra("charges", sch);
                i.putExtra("useremail",uemail);
                i.putExtra("mechid", mid);
                i.putExtra("fname", mfnm);
                i.putExtra("lname", mlnm);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mechanicList.size();
    }

    @Override
    public Filter getFilter() {
        return mechanicFilter;
    }

    private Filter mechanicFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MechanicModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mechanicListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MechanicModel item : mechanicListFull) {
                    if (item.getMfname().toLowerCase().contains(filterPattern) || item.getMlname().toLowerCase().contains(filterPattern) || item.getMaddress().toLowerCase().contains(filterPattern) ) {
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
            mechanicList.clear();
            mechanicList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,rating,address;
        ImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.reqmechname);
            rating = itemView.findViewById(R.id.reqmechrating);
            address = itemView.findViewById(R.id.reqmechaddress);
            picture = itemView.findViewById(R.id.reqmechimg);

        }
    }
}
