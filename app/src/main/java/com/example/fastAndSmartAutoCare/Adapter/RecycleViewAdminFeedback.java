package com.example.onroadremotevehicleassistantapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadremotevehicleassistantapp.AdminViewFeedback;
import com.example.onroadremotevehicleassistantapp.DatabaseHelper;
import com.example.onroadremotevehicleassistantapp.Model.FeedbackModel;
import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.example.onroadremotevehicleassistantapp.Model.UserModel;
import com.example.onroadremotevehicleassistantapp.R;
import com.example.onroadremotevehicleassistantapp.RequestReport;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdminFeedback extends RecyclerView.Adapter<RecycleViewAdminFeedback.ViewHolder> {
    List<FeedbackModel> feedbackList;
    List<FeedbackModel> feedbackListFull;
    Context context;


    public RecycleViewAdminFeedback(List<FeedbackModel> feedbackList, Context context) {
        this.feedbackList = feedbackList;
        this.context = context;
        feedbackListFull = new ArrayList<>(feedbackList);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_single_feedback,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int id = feedbackList.get(position).getFeedbackId();
        String date = feedbackList.get(position).getFeedbackDate();
        int userid = feedbackList.get(position).getFeedbackUserId();
        int mechid = feedbackList.get(position).getFeedbackMechanicId();
        DatabaseHelper dbhelper = new DatabaseHelper(context);
        List<UserModel> userall=dbhelper.viewOneUser(userid);
        List<MechanicModel> mechall=dbhelper.viewOneMechanic(mechid);
        String uname = userall.get(0).getFname()+" "+userall.get(0).getLname();
        String mname = mechall.get(0).getMfname()+" "+mechall.get(0).getMlname();
        float rt = feedbackList.get(position).getFeedbackMechanicRating();
        String rate = String.valueOf(rt);

        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        holder.tableLayout.setPadding(20, 20, 20, 30);
        String[] headerText = {"Feedback Id", "Date", "UserName", "MechanicName", "Rating"};
        for (String c : headerText) {
            TextView tv = new TextView(context);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        holder.tableLayout.addView(rowHeader);


        TableRow row = new TableRow(context);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] colText = {id + "", date,uname, mname,rate};
        for (String text : colText) {
            TextView tv = new TextView(context);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(16);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(text);
            row.addView(tv);
        }
        holder.tableLayout.addView(row);

    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,username,mechanicname,date,rating;
        TableLayout tableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.admin_feedbackId);
            username = itemView.findViewById(R.id.admin_feedbackUsername);
            mechanicname = itemView.findViewById(R.id.admin_feedbackMechanicname);
            date = itemView.findViewById(R.id.admin_feedbackDate);
            rating = itemView.findViewById(R.id.admin_feedbackRating);
            tableLayout = itemView.findViewById(R.id.tablelayout4);



        }
    }
}
