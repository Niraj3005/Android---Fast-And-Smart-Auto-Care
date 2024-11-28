package com.example.onroadremotevehicleassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.onroadremotevehicleassistantapp.Model.FeedbackModel;
import com.example.onroadremotevehicleassistantapp.Model.UserModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GiveFeedback extends AppCompatActivity {
   EditText fSuggestion;
   RatingBar fRating;
   RadioGroup fSatisfaction;
   RadioButton verysatisfied,satisfied,neutral,unsatisfied,veryunsatisfied;
   Button fbutton;
   int fid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_give_feedback);

        fRating = findViewById(R.id.feedback_rating);
        fSuggestion = findViewById(R.id.feedback_suggestion);
        fSatisfaction = findViewById(R.id.feedback_satisfaction);
        verysatisfied = findViewById(R.id.verysatisfied);
        satisfied = findViewById(R.id.satisfied);
        neutral = findViewById(R.id.neutral);
        unsatisfied = findViewById(R.id.unsatisfied);
        veryunsatisfied = findViewById(R.id.veryunsatisfied);
        fbutton = findViewById(R.id.feedback_btn);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());

        Intent i = getIntent();
        String str = i.getStringExtra("requestId");
        String str1 = i.getStringExtra("userId");
        String str2 = i.getStringExtra("mechId");
        int reqid = Integer.parseInt(str);
        int userid = Integer.parseInt(str1);
        int mechid = Integer.parseInt(str2);

        DatabaseHelper DB = new DatabaseHelper(GiveFeedback.this);

        List<FeedbackModel> all = DB.viewUserFeedback(reqid);
        if (all.isEmpty()) {
            fbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHelper DB = new DatabaseHelper(GiveFeedback.this);
                    float frate=fRating.getRating();
                    String fsu=fSuggestion.getText().toString();
                    String fsa=((RadioButton)findViewById(fSatisfaction.getCheckedRadioButtonId())).getText().toString();

                    float allrating = DB.viewMechanicRating(mechid);
                    float avgrating = (float) ((allrating+frate)/2.0);
                    DecimalFormat value = new DecimalFormat("#.#");
                    float f1 = Float.parseFloat(value.format(avgrating));
                    boolean success1 = DB.updateMechanicRating(mechid,f1);


                    if(success1==true){
                        Toast.makeText(GiveFeedback.this, "You Rated :  "+frate, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(GiveFeedback.this, "Unable to Rate..."  , Toast.LENGTH_LONG).show();
                    }


                    if(TextUtils.isEmpty(fsu) || TextUtils.isEmpty(fsa))
                        Toast.makeText(GiveFeedback.this,"All fields Required..",Toast.LENGTH_SHORT).show();
                    else{
                        FeedbackModel feedbackModel = new FeedbackModel(-1,userid,mechid,reqid,frate,fsa,fsu,currentDateandTime);
                        boolean success = DB.giveFeedback(feedbackModel);
                        if(success==true){
                            Toast.makeText(GiveFeedback.this, "Thank you for valuable feedback...!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(GiveFeedback.this,ViewUserRequest.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(GiveFeedback.this, "Unable to give feedback...!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }
        else {
            int fid = all.get(0).getFeedbackId();
            String sugg = all.get(0).getFeedbackSuggestion();
            float rate = all.get(0).getFeedbackMechanicRating();
            String sati = all.get(0).getFeedbackSatisfaction();

            if((sugg==null))
            {
                fSuggestion.setText("");
            }else{
                fSuggestion.setText(sugg);
                fSuggestion.setEnabled(false);
                fbutton.setClickable(false);
            }
            if((rate==0))
            {
                fRating.setRating(0);
            }else{
                fRating.setRating(rate);
                fRating.setEnabled(false);
                fbutton.setClickable(false);
            }
            if((sati==null)) {
                verysatisfied.setChecked(false);
                satisfied.setChecked(false);
                neutral.setChecked(false);
                unsatisfied.setChecked(false);
                veryunsatisfied.setChecked(false);

            }else{
                if(sati.equals("Very Satisfied")){
                    verysatisfied.setChecked(true);
                    verysatisfied.setEnabled(false);
                    satisfied.setEnabled(false);
                    neutral.setEnabled(false);
                    unsatisfied.setEnabled(false);
                    veryunsatisfied.setEnabled(false);
                }
                if(sati.equals("Satisfied")){
                    satisfied.setChecked(true);
                    verysatisfied.setEnabled(false);
                    satisfied.setEnabled(false);
                    neutral.setEnabled(false);
                    unsatisfied.setEnabled(false);
                    veryunsatisfied.setEnabled(false);
                }
                if(sati.equals("Neutral")){
                    neutral.setChecked(true);
                    verysatisfied.setEnabled(false);
                    satisfied.setEnabled(false);
                    neutral.setEnabled(false);
                    unsatisfied.setEnabled(false);
                    veryunsatisfied.setEnabled(false);
                }
                if(sati.equals("Unsatisfied")){
                    unsatisfied.setChecked(true);
                    verysatisfied.setEnabled(false);
                    satisfied.setEnabled(false);
                    neutral.setEnabled(false);
                    unsatisfied.setEnabled(false);
                    veryunsatisfied.setEnabled(false);
                }
                if(sati.equals("Very Unsatisfied")){
                    veryunsatisfied.setChecked(true);
                    verysatisfied.setEnabled(false);
                    satisfied.setEnabled(false);
                    neutral.setEnabled(false);
                    unsatisfied.setEnabled(false);
                    veryunsatisfied.setEnabled(false);
                }
            }

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
            Intent intent = new Intent(GiveFeedback.this, UserDashboard.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(GiveFeedback.this, Login.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}