package com.example.onroadremotevehicleassistantapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.MechanicViewFeedback;
import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.example.onroadremotevehicleassistantapp.Model.RequestModel;
import com.example.onroadremotevehicleassistantapp.Model.UserModel;
import com.example.onroadremotevehicleassistantapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterViewMechanicRequest extends RecyclerView.Adapter<RecycleViewAdapterViewMechanicRequest.ViewHolder> implements Filterable {
    List<RequestModel> requestList;
    List<RequestModel> requestListFull;
    Context context;

    public RecycleViewAdapterViewMechanicRequest(List<RequestModel> requestList, Context context) {
        this.requestList = requestList;
        this.context = context;
        requestListFull = new ArrayList<>(requestList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_single_mech_request,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int userid = requestList.get(position).getRequestUserId();
        DatabaseHelper dbhelper = new DatabaseHelper(context.getApplicationContext());
        List<UserModel> all=dbhelper.viewOneUser(userid);
        holder.requestId.setText("Request ID : "+requestList.get(position).getRequestId());
        holder.requestUname.setText(all.get(0).getFname()+" "+all.get(0).getLname());
        holder.requestVehicleno.setText(requestList.get(position).getRequestVehicleno());
        holder.requestMobileno.setText(all.get(0).getPhoneno());
        holder.requestVehicletype.setText(requestList.get(position).getRequestVehicletype());
        holder.requestdesc.setText(requestList.get(position).getRequestDescription());
        holder.requestLocation.setText(requestList.get(position).getRequestLocation());
        holder.requestDate.setText("Requested On "+requestList.get(position).getRequestDate());

        boolean active = requestList.get(position).isRequestStatus();
        int reqid = requestList.get(position).getRequestId();
        String requestId = String.valueOf(reqid);

        if(active==true){
            holder.status.setChecked(true);
        }
        else{
            holder.status.setChecked(false);
        }

        holder.status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DatabaseHelper dbhelper = new DatabaseHelper(context.getApplicationContext());
                    boolean success = dbhelper.requestStatus(reqid, true);
                    if (success == true) {
                        Toast.makeText(context.getApplicationContext(), "Request Completed", Toast.LENGTH_SHORT).show();
                        Intent intent = ((Activity) context).getIntent();
                        ((Activity) context).finish();
                        context.startActivity(intent);
                    }

                } else {
                    DatabaseHelper dbhelper = new DatabaseHelper(context.getApplicationContext());
                    boolean success = dbhelper.requestStatus(reqid, false);
                    if (success == true) {
                            Toast.makeText(context.getApplicationContext(), "Request Pending", Toast.LENGTH_SHORT).show();
                        Intent intent = ((Activity) context).getIntent();
                        ((Activity) context).finish();
                        context.startActivity(intent);
                    }

                }
            }
        });

        holder.track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str[]={};
                String location = requestList.get(position).getRequestLocation();
               String[] stringarray = location.split(",");
               String lati = stringarray[0];
                double lat = Double.parseDouble(lati);
                String longi = stringarray[1];
                double lon = Double.parseDouble(longi);
                String link = "http://maps.google.com/maps?q=loc:" + String.format("%f,%f", lat, lon);
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db=new DatabaseHelper(view.getContext());
                boolean success = db.cancelRequest(requestList.get(position).getRequestId());
                if (success == true) {
                    Toast.makeText(view.getContext(), "Request Cancelled...", Toast.LENGTH_SHORT).show();
                    Intent intent = ((Activity) context).getIntent();
                    ((Activity) context).finish();
                    context.startActivity(intent);
                } else {
                }
                requestList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.viewFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), MechanicViewFeedback.class);
                intent.putExtra("requestId",requestId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    @Override
    public Filter getFilter() {
        return requestFilter;
    }

    private Filter requestFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RequestModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(requestListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (RequestModel item : requestListFull) {
                    if (item.getRequestServiceName().toLowerCase().contains(filterPattern)) {
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
            requestList.clear();
            requestList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView requestId,requestUname,requestVehicleno,requestVehicletype,requestMobileno,requestLocation,requestdesc,requestDate;
        Button track,cancel,viewFeedback;
        ToggleButton status;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            requestId = itemView.findViewById(R.id.view_idrequest);
            requestUname = itemView.findViewById(R.id.view_usernamerequest);
            requestVehicleno = itemView.findViewById(R.id.view_vehiclenorequest);
            requestVehicletype = itemView.findViewById(R.id.view_vehicletyperequest);
            requestMobileno = itemView.findViewById(R.id.view_mobilenorequest);
            requestdesc = itemView.findViewById(R.id.view_descrequest);
            requestLocation = itemView.findViewById(R.id.view_locationrequest);
            track = itemView.findViewById(R.id.view_trackrequest);
            cancel = itemView.findViewById(R.id.view_cancelrequest);
            status = itemView.findViewById(R.id.request_status);
            requestDate = itemView.findViewById(R.id.view_daterequest);
            viewFeedback = itemView.findViewById(R.id.view_feedbackrequest);



        }
    }

}

