package com.example.onroadremotevehicleassistantapp.Adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.example.onroadremotevehicleassistantapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterMechanic extends RecyclerView.Adapter<RecycleViewAdapterMechanic.ViewMyHolder> implements Filterable {
    List<MechanicModel> mechanicList;
    Context context;
    List<MechanicModel> mechanicListFull;

    public RecycleViewAdapterMechanic(List<MechanicModel> mechanicList, Context context) {
        this.mechanicList = mechanicList;
        this.context = context;
        mechanicListFull = new ArrayList<>(mechanicList);

    }

    @NonNull
    @Override
    public ViewMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_mechanic,parent,false);
        ViewMyHolder holder= new ViewMyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMyHolder viewMyHolder, @SuppressLint("RecyclerView") int position) {
        viewMyHolder.mfname.setText("Full Name : "+mechanicList.get(position).getMfname());
        viewMyHolder.mlname.setText(mechanicList.get(position).getMlname());
        viewMyHolder.memail.setText("Email : "+mechanicList.get(position).getMemail());
        viewMyHolder.mphoneno.setText("Phone No. : "+mechanicList.get(position).getMphoneno());
        viewMyHolder.maddress.setText("Address : "+mechanicList.get(position).getMaddress());
        viewMyHolder.photo.setImageBitmap(mechanicList.get(position).getMprofileimage());

       Bitmap str3 = mechanicList.get(position).getMprofileimage();

        if (str3 == null) {
            viewMyHolder.photo.setImageResource(R.drawable.user1);
        } else {
        }


        int active = mechanicList.get(position).getIsactive();
        if(active==1){
            viewMyHolder.status.setText("Status : Activated");
        }
        else{
            viewMyHolder.status.setText("Status : Deactivated");
        }


        int id = mechanicList.get(position).getMid();
        if(active==1){
            viewMyHolder.actdeact.setChecked(true);
        }
        else{
            viewMyHolder.actdeact.setChecked(false);
        }

        viewMyHolder.actdeact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DatabaseHelper dbhelper = new DatabaseHelper(context.getApplicationContext());
                    boolean success = dbhelper.isMechanicActive(id, true);
                    if (success == true) {
                        Toast.makeText(context.getApplicationContext(), "Mechanic Activated", Toast.LENGTH_SHORT).show();
                        Intent intent = ((Activity) context).getIntent();
                        ((Activity) context).finish();
                        context.startActivity(intent);
                    }

                } else {
                    DatabaseHelper dbhelper = new DatabaseHelper(context.getApplicationContext());
                    boolean success = dbhelper.isMechanicActive(id, false);
                    if (success == true) {
                        Toast.makeText(context.getApplicationContext(), "Mechanic Activated", Toast.LENGTH_SHORT).show();
                        Intent intent = ((Activity) context).getIntent();
                        ((Activity) context).finish();
                        context.startActivity(intent);
                    }

                }
            }
        });

        viewMyHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db=new DatabaseHelper(view.getContext());
                boolean success = db.deleteMechanic(mechanicList.get(position).getMid());
                if (success == true) {
                    Toast.makeText(view.getContext(), "Mechanic Account Deleted....", Toast.LENGTH_SHORT).show();

                } else {
                }
                mechanicList.remove(position);
                notifyDataSetChanged();
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
                    if (item.getMfname().toLowerCase().contains(filterPattern) || item.getMlname().toLowerCase().contains(filterPattern) ) {
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

    public class ViewMyHolder extends RecyclerView.ViewHolder {
        TextView mfname,mlname,memail,mphoneno,maddress,status;
        ToggleButton actdeact;
        ImageView photo;
        Button delete;

        public ViewMyHolder(@NonNull View itemView) {
            super(itemView);
            mfname = itemView.findViewById(R.id.view_fname);
            mlname = itemView.findViewById(R.id.view_lname);
            memail = itemView.findViewById(R.id.view_email);
            mphoneno = itemView.findViewById(R.id.view_phoneno);
            maddress = itemView.findViewById(R.id.view_address);
            status = itemView.findViewById(R.id.status);
            actdeact = itemView.findViewById(R.id.toggleButton);
            photo = itemView.findViewById(R.id.mprofile_photo);
            delete = itemView.findViewById(R.id.mech_delete);
        }
    }
}
