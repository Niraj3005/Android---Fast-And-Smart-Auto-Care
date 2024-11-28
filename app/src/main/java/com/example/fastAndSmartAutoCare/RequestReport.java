package com.example.onroadremotevehicleassistantapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RequestReport extends AppCompatActivity {

    TextInputEditText startdate, enddate;
    TextView fullname,email;
    Button generate,download;

    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener1;
    DatabaseHelper dataHelper = new DatabaseHelper(RequestReport.this);


    SearchView searchRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_request_report);

        searchRequest = findViewById(R.id.search_request1);
        fullname = findViewById(R.id.adminname6);
        email = findViewById(R.id.adminemail6);
        generate = findViewById(R.id.generate_requestreport);
        download = findViewById(R.id.download_requestreport);
        startdate = findViewById(R.id.startdate1);
        enddate = findViewById(R.id.enddate1);
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout2);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String aemail = i.getStringExtra("email");
        fullname.setText(name);
        email.setText(aemail);
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RequestReport.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setTitle("Set Date");
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String fm=""+month;
                String fd=""+day;
                month = month + 1;
                if(month<10){
                    fm ="0"+month;
                }
                if (day<10){
                    fd="0"+day;
                }
                String date =  fd + "/" + fm + "/" + year;
                startdate.setText(date);
            }
        };

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RequestReport.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setTitle("Set Date");
                dialog.show();
            }
        });

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String fm=""+month;
                String fd=""+day;
                month = month + 1;
                if(month<10){
                    fm ="0"+month;
                }
                if (day<10){
                    fd="0"+day;
                }
                String date =  fd + "/" + fm + "/" + year;
                enddate.setText(date);

            }
        };




        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date1 = startdate.getText().toString();
                String date2 = enddate.getText().toString();

                // Add header row
                TableRow rowHeader = new TableRow(RequestReport.this);
                rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
                rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                tableLayout.setPadding(20, 20, 20, 30);
                String[] headerText = {"Request Id", "Date", "MechanicName", "UserName", "Vehicle No.", "Request Type", "Location", "Status"};
                for (String c : headerText) {
                    TextView tv = new TextView(RequestReport.this);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(18);
                    tv.setPadding(5, 5, 5, 5);
                    tv.setText(c);
                    rowHeader.addView(tv);
                }
                tableLayout.addView(rowHeader);

                // Get data from sqlite database and add them to the table
                // Open the database for reading
                SQLiteDatabase db = dataHelper.getReadableDatabase();
                // Start the transaction.
                db.beginTransaction();

                try {
                    Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.REQUEST_TABLE + " WHERE " + DatabaseHelper.REQUEST_DATE + " BETWEEN ? AND ? ",new String[] {(date1),(date2)});
                    if (cursor.getCount() > 0) {
                        while (cursor.moveToNext()) {
                            int requestid=cursor.getInt(0);
                            int requestmechid=cursor.getInt(1);
                            int requestuserid=cursor.getInt(2);
                            String requestvno=cursor.getString(3);
                            String requestvtype=cursor.getString(4);
                            String requestsnm=cursor.getString(5);
                            String requestsch=cursor.getString(6);
                            String requestlocation=cursor.getString(7);
                            String requestdesc=cursor.getString(8);
                            String requestDate=cursor.getString(9);
                            int Status=cursor.getInt(10);

                            String uname = dataHelper.getUserName(requestuserid);
                            String mname = dataHelper.getMechName(requestmechid);

                            String requestStatus;
                            if (Status == 1)
                            {
                                requestStatus = "Completed";
                            }else {
                                requestStatus = "Pending";
                            }


                            // dara rows
                            TableRow row = new TableRow(RequestReport.this);
                            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT));
                            String[] colText = {requestid + "", requestDate, mname, uname, requestvno, requestvtype, requestlocation, requestStatus };
                            for (String text : colText) {
                                TextView tv = new TextView(RequestReport.this);
                                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                        TableRow.LayoutParams.WRAP_CONTENT));
                                tv.setGravity(Gravity.CENTER);
                                tv.setTextSize(16);
                                tv.setPadding(5, 5, 5, 5);
                                tv.setText(text);
                                row.addView(tv);
                            }
                            tableLayout.addView(row);

                        }

                    }
                    db.setTransactionSuccessful();

                } catch (SQLiteException e) {
                    e.printStackTrace();

                } finally {
                    db.endTransaction();
                    // End the transaction.
                    db.close();
                    // Close database
                }

            }
        });


        download.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                boolean success = exportDB();
                if (success == true) {
                    Toast.makeText(RequestReport.this, "Report Downloaded Successfully...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RequestReport.this, "Failed to Download Report...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean exportDB() {


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(new Date());
        String date1 = startdate.getText().toString();
        String date2 = enddate.getText().toString();

        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());

        File exportDir = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "RVA-RequestReport"+date+".csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.REQUEST_TABLE + " WHERE " + DatabaseHelper.REQUEST_DATE + " BETWEEN ? AND ? ",new String[] {(date1),(date2)});
            String[] headerText = {"Request Id", "Date", "MechanicName", "UserName", "Vehicle No.", "Request Type", "Location", "Status"};
            csvWrite.writeNext(headerText);
            while (cursor.moveToNext()) {
                int requestid=cursor.getInt(0);
                int requestmechid=cursor.getInt(1);
                int requestuserid=cursor.getInt(2);
                String requestvno=cursor.getString(3);
                String requestvtype=cursor.getString(4);
                String requestsnm=cursor.getString(5);
                String requestsch=cursor.getString(6);
                String requestlocation=cursor.getString(7);
                String requestdesc=cursor.getString(8);
                String requestDate=cursor.getString(9);
                int Status=cursor.getInt(10);

                String uname = dataHelper.getUserName(requestuserid);
                String mname = dataHelper.getMechName(requestmechid);

                String requestStatus;
                if (Status == 1)
                {
                    requestStatus = "Completed";
                }else {
                    requestStatus = "Pending";
                }
                String arrStr[] = {requestid + "", requestDate, mname, uname, requestvno, requestvtype, requestlocation, requestStatus};
                csvWrite.writeNext(arrStr);


            }
            csvWrite.close();
            cursor.close();
        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
        return true;

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
            Intent intent = new Intent(RequestReport.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(RequestReport.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }


}


