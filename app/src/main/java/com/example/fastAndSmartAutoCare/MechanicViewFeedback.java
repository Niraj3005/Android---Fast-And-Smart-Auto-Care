package com.example.onroadremotevehicleassistantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onroadremotevehicleassistantapp.Model.FeedbackModel;
import com.example.onroadremotevehicleassistantapp.Model.UserModel;

import java.util.List;

public class MechanicViewFeedback extends AppCompatActivity {
TextView fdate,frequestid,fusername,femailid,fsatisfaction,fsuggestion,frating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_mechanic_view_feedback);

        fdate = findViewById(R.id.view_fdate);
        frequestid = findViewById(R.id.view_frequestid);
        fusername = findViewById(R.id.view_fusername);
        femailid = findViewById(R.id.view_femailid);
        fsatisfaction = findViewById(R.id.view_fsatisfaction);
        fsuggestion = findViewById(R.id.view_fsuggestion);
        frating = findViewById(R.id.view_frating);

        Intent i = getIntent();
        String id = i.getStringExtra("requestId");
        int requestId = Integer.parseInt(id);

        DatabaseHelper db=new DatabaseHelper(MechanicViewFeedback.this);
        List<FeedbackModel> all = db.viewMechanicFeedback(requestId);
        if(all.isEmpty()){
        }
        else{
            int userId = all.get(0).getFeedbackUserId();
            String date= all.get(0).getFeedbackDate();
            String satis = all.get(0).getFeedbackSatisfaction();
            String sugg = all.get(0).getFeedbackSuggestion();
            float rate = all.get(0).getFeedbackMechanicRating();
            List<UserModel> all1 = db.viewOneUser(userId);
            String name = all1.get(0).getFname()+" "+all1.get(0).getLname();
            String email = all1.get(0).getEmail();
            fdate.setText("Requested On, "+date);
            frequestid.setText("RequestId : "+requestId);
            femailid.setText(email);
            fusername.setText(name);
            fsatisfaction.setText(satis);
            fsuggestion.setText(sugg);
            frating.setText(" "+String.valueOf(rate));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            Intent intent = new Intent(MechanicViewFeedback.this, MechanicDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(MechanicViewFeedback.this, MechanicLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}