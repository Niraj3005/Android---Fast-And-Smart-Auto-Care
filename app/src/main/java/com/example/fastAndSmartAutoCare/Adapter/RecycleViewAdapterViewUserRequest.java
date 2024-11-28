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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.GiveFeedback;
import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.example.onroadremotevehicleassistantapp.Model.RequestModel;
import com.example.onroadremotevehicleassistantapp.Model.UserModel;
import com.example.onroadremotevehicleassistantapp.R;
import com.example.onroadremotevehicleassistantapp.UpdateRequest;
import com.example.onroadremotevehicleassistantapp.UserDashboard;
import com.example.onroadremotevehicleassistantapp.ViewRequestServices;
import com.example.onroadremotevehicleassistantapp.ViewUserRequest;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterViewUserRequest extends RecyclerView.Adapter<RecycleViewAdapterViewUserRequest.ViewHolder> implements Filterable {
    List<RequestModel> requestList;
    List<RequestModel> requestListFull;
    Context context;
    String uemail;

    public RecycleViewAdapterViewUserRequest(List<RequestModel> requestList, Context context,String uemail) {
        this.requestList = requestList;
        this.context = context;
        this.uemail = uemail;
        requestListFull = new ArrayList<>(requestList);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_single_user_request,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int mechid = requestList.get(position).getRequestMechanicId();
        DatabaseHelper dbhelper = new DatabaseHelper(context.getApplicationContext());
        List<MechanicModel> all=dbhelper.viewOneMechanic(mechid);
        holder.requestId.setText("Request ID : "+requestList.get(position).getRequestId());
        holder.requestMechname.setText(all.get(0).getMfname()+" "+all.get(0).getMlname());
        holder.requestVehicleno.setText(requestList.get(position).getRequestVehicleno());
        holder.requestMobileno.setText(all.get(0).getMphoneno());
        holder.requestVehicletype.setText(requestList.get(position).getRequestVehicletype());
        holder.requestDesc.setText(requestList.get(position).getRequestDescription());
        holder.requestDate.setText("Requested On "+requestList.get(position).getRequestDate());

        boolean status = requestList.get(position).isRequestStatus();
        if(status==true){
            holder.feedback.setVisibility(View.VISIBLE);
        }else {
            holder.feedback.setVisibility(View.INVISIBLE);

        }

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mnm = all.get(position).getMfname()+""+all.get(position).getMlname();
                String vno = requestList.get(position).getRequestVehicleno();
                String phno = all.get(position).getMphoneno();
                String snm = requestList.get(position).getRequestServiceName();
                String sch = requestList.get(position).getRequestCharges();
                String loc = requestList.get(position).getRequestLocation();
                String desc = requestList.get(position).getRequestDescription();
                String date = requestList.get(position).getRequestDate();

                Intent i = new Intent(view.getContext(), UpdateRequest.class);
                i.putExtra("useremail",uemail);
                i.putExtra("mname",mnm);
                i.putExtra("vehicleno",vno);
                i.putExtra("phoneno",phno);
                i.putExtra("sname",snm);
                i.putExtra("scharges",sch);
                i.putExtra("location",loc);
                i.putExtra("description",desc);
                i.putExtra("date",date);

                context.startActivity(i);
            }
        });

        holder.feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int str = requestList.get(position).getRequestId();
                int str1 = requestList.get(position).getRequestUserId();
                int str2 = requestList.get(position).getRequestMechanicId();
                String reqid = String.valueOf(str);
                String userid = String.valueOf(str1);
                String mechid = String.valueOf(str2);

                Intent i = new Intent(view.getContext(), GiveFeedback.class);
                i.putExtra("requestId",reqid);
                i.putExtra("userId",userid);
                i.putExtra("mechId",mechid);

                context.startActivity(i);
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
        TextView requestId,requestMechname,requestVehicleno,requestVehicletype,requestMobileno,requestDesc,requestDate;
        Button update,cancel,feedback;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            requestId = itemView.findViewById(R.id.view_idrequest1);
            requestMechname = itemView.findViewById(R.id.view_mechnamerequest1);
            requestVehicleno = itemView.findViewById(R.id.view_vehiclenorequest1);
            requestVehicletype = itemView.findViewById(R.id.view_vehicletyperequest1);
            requestMobileno = itemView.findViewById(R.id.view_mobilenorequest1);
            requestDesc = itemView.findViewById(R.id.view_requestdesc1);
            update = itemView.findViewById(R.id.view_updaterequest1);
            cancel = itemView.findViewById(R.id.view_cancelrequest1);
            feedback = itemView.findViewById(R.id.give_feedback);
            requestDate = itemView.findViewById(R.id.view_daterequest1);


        }
    }

}

